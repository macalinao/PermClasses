/*
 * This file is part of PermClasses.
 * 
 * PermClasses is released under the Voxton License version 1.
 *
 * PermClasses is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition to this, you must also specify that this product includes 
 * software developed by Voxton.net and may not remove any code
 * referencing Voxton.net directly or indirectly.
 * 
 * PermClasses is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and the Voxton license along with PermClasses. 
 * If not, see <http://voxton.net/voxton-license-v1.txt>.
 */
package net.crimsoncraft.permclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Manages classes.
 */
public class ClassManager {

    /**
     * The {@link PermClass}es registered on the server.
     */
    private Map<String, PermClass> classes;

    /**
     * The {@link PermClass}es mapped to their group names.
     */
    private Map<String, PermClass> classesToGroups;

    /**
     * The {@link ClassType}s registered on the server.
     */
    private Map<String, ClassType> classTypes;

    /**
     * The {@link ClassTier}s on the server.
     */
    private Map<String, ClassTier> classTiers;

    /**
     * The prefix used for classes as defined in the permissions plugin.
     */
    private String groupPrefix = "pcl_";

    /**
     * The hook to the {@link PermClasses} main class.
     */
    private final PermClasses plugin;

    /**
     * Constructor.
     *
     * @param plugin The {@link PermClasses} main class.
     */
    public ClassManager(PermClasses plugin) {
        this.plugin = plugin;
        loadAll();
    }

    /**
     * Loads everything.
     */
    public final void loadAll() {
        setupDefaults();

        Configuration config = plugin.getConfig();

        //Load types
        ConfigurationSection typeSection = config.getConfigurationSection("types");
        if (typeSection == null) {
            typeSection = config.createSection("types");
        }

        for (String id : typeSection.getKeys(false)) {
            String name = typeSection.getString(id);
            ClassType type = new ClassType(this, id, name);
            classTypes.put(type.getId(), type);
        }

        //Load tiers
        ConfigurationSection tierSection = config.getConfigurationSection("tiers");
        if (tierSection == null) {
            tierSection = config.createSection("tiers");
        }

        for (String id : tierSection.getKeys(false)) {
            String name = tierSection.getString(id);
            ClassTier tier = new ClassTier(this, id, name);
            classTiers.put(tier.getId(), tier);
        }

        //Load classes
        ConfigurationSection classSection = config.getConfigurationSection("classes");
        if (classSection == null) {
            classSection = config.createSection("classes");
        }

        for (String id : classSection.getKeys(false)) {
            ConfigurationSection section = classSection.getConfigurationSection(id);

            String name = section.getString("name", id);

            String typeString = section.getString("type", "default");
            String tierString = section.getString("tier", "default");

            ClassType type = getClassType(typeString);
            ClassTier tier = getClassTier(tierString);

            if (type == null) {
                plugin.getLogger().log(Level.WARNING, "Unknown class type '" + typeString + "' specified; defaulting to default...");
                type = getClassType("default");
            }

            if (tier == null) {
                plugin.getLogger().log(Level.WARNING, "Unknown class tier '" + tierString + "' specified; defaulting to default...");
                tier = getClassTier("default");
            }

            //Insert the class
            PermClass pcl = new PermClass(this, id, name);
            type.addClass(pcl);
            tier.addClass(pcl);

            classes.put(pcl.getId(), pcl);
            classesToGroups.put(pcl.getGroup(), pcl);
        }
    }

    /**
     * Sets up all defaults and initializes maps.
     */
    private void setupDefaults() {
        classes = new HashMap<String, PermClass>();
        classesToGroups = new HashMap<String, PermClass>();
        classTypes = new HashMap<String, ClassType>();
        classTiers = new HashMap<String, ClassTier>();

        ClassType defaultType = new ClassType(this, "default", "Default");
        ClassTier defaultTier = new ClassTier(this, "default", "Default");

        classTypes.put(defaultType.getId(), defaultType);
        classTiers.put(defaultTier.getId(), defaultTier);
    }

    /**
     * Gets a permissions class from its id.
     *
     * @param id The name of the PermClass.
     * @return The PermClass that corresponds to the id.
     */
    public PermClass getClassFromId(String id) {
        return classes.get(id);
    }

    /**
     * Gets a PermClass from its name.
     *
     * @param name The name of the PermClass.
     * @return The PermClass associated with the name.
     */
    public PermClass getClassFromName(String name) {
        return getClassFromId(PermClasses.formatNameToId(name));
    }

    /**
     * Gets a list of all {@link ClassType}s on the server.
     *
     * @return A list of all {@link ClassType}s on the server.
     */
    public List<ClassType> getClassTypes() {
        return new ArrayList<ClassType>(classTypes.values());
    }

    /**
     * Sets a player's PermClass.
     *
     * @param player The player to set the PermClass of.
     * @param pcl The PermClass to set.
     */
    public void setClass(String player, PermClass pcl) {
        PermClass toRemove = getClasses(player).get(pcl.getType());
        if (toRemove != null) {
            for (World world : Bukkit.getWorlds()) {
                plugin.getPermAPI().playerRemoveGroup(world, player, toRemove.getGroup());
            }
        }

        for (World world : Bukkit.getWorlds()) {
            plugin.getPermAPI().playerAddGroup(world, player, pcl.getGroup());
        }
    }

    /**
     * Gets the classes of a player.
     *
     * @param player The player to get the classes of.
     * @return The player's classes.
     */
    public Map<ClassType, PermClass> getClasses(String player) {
        String[] groups = plugin.getPermAPI().getPlayerGroups(Bukkit.getWorlds().get(0), player);
        Map<ClassType, PermClass> classMap = new HashMap<ClassType, PermClass>();
        for (String group : groups) {
            PermClass pcl = classesToGroups.get(group);
            if (pcl == null) {
                continue;
            }
            classMap.put(pcl.getType(), pcl);
        }
        return classMap;
    }

    /**
     * Gets a {@link ClassType} from its id.
     *
     * @param id The id of the {@link ClassType}.
     * @return The {@link ClassType} corresponding with the id, or null if there
     * is none.
     */
    public ClassType getClassType(String id) {
        return classTypes.get(id);
    }

    /**
     * Gets a {@link ClassTier} from its id.
     *
     * @param id The id of the {@link ClassTier}.
     * @return The {@link ClassTier} corresponding with the id, or null if there
     * is none.
     */
    public ClassTier getClassTier(String id) {
        return classTiers.get(id);
    }

    /**
     * Saves a type to this {@link ClassManager}.
     *
     * @param type The {@link ClassType} to save.
     */
    public void saveType(ClassType type) {
    }

    /**
     * Saves a tier to this ClassManager.
     *
     * @param tier The tier to save.
     */
    public void saveTier(ClassTier tier) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gets the group prefix of {@link PermClass}es.
     *
     * @return The group prefix to use.
     */
    public String getGroupPrefix() {
        return groupPrefix;
    }

}
