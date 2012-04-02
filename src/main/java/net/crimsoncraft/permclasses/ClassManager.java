/*
 * This file is part of PermClasses.
 *
 * PermClasses is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PermClasses is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with PermClasses.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.crimsoncraft.permclasses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

        //Load classes
        ConfigurationSection classesSection = config.getConfigurationSection("classes");
        if (classesSection == null) {
            classesSection = config.createSection("classes");
        }

        for (String tierName : classesSection.getKeys(false)) {
            ClassTier tier = new ClassTier(this, tierName);
            classTiers.put(tier.getId(), tier);

            ConfigurationSection tierSection = classesSection.getConfigurationSection(tier.getName());

            for (String typeName : tierSection.getKeys(false)) {

                ConfigurationSection classSection = tierSection.getConfigurationSection(typeName);
                if (classSection == null) {
                    classSection = tierSection.createSection(typeName);
                }

                Set<String> classNames = classSection.getKeys(false);

                ClassType type = getClassType(PermClasses.formatNameToId(typeName));
                if (type == null) {
                    type = new ClassType(this, typeName);
                    classTypes.put(type.getId(), type);
                }

                for (String className : classNames) {
                    tier.createClass(type, className,
                            classSection.getStringList("bind"),
                            classSection.getStringList("unbind")).getPermission();
                }
            }
        }
    }

    /**
     * Creates a class.
     *
     * @param name The name of the {@link PermClass}.
     * @param bindCmds A list of commands to be executed when the class is
     * bound.
     * @param unbindCmds A list of commands to be executed when the class is
     * unbound.
     * @return The {@link PermClass} created.
     */
    public PermClass createClass(String name, List<String> bindCmds, List<String> unbindCmds) {
        String pid = PermClasses.formatNameToId(name);
        PermClass pcl = new PermClass(this, name, bindCmds, unbindCmds);
        classes.put(pcl.getId(), pcl);
        classesToGroups.put(pcl.getGroup(), pcl);
        return pcl;
    }

    /**
     * Sets up all defaults and initializes maps.
     */
    private void setupDefaults() {
        classes = new HashMap<String, PermClass>();
        classesToGroups = new HashMap<String, PermClass>();
        classTypes = new HashMap<String, ClassType>();
        classTiers = new HashMap<String, ClassTier>();

        ClassType defaultType = new ClassType(this, "Default");
        ClassTier defaultTier = new ClassTier(this, "Default");

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
    public Set<ClassType> getClassTypes() {
        return new HashSet<ClassType>(classTypes.values());
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
     * Resets a player's PermClass.
     *
     * @param player The player to reset the {@link PermClass} of.
     * @param type The {@link ClassType} to reset.
     */
    public void resetClass(String player, ClassType type) {
        PermClass current = getClasses(player).get(type);
        if (current == null) {
            return;
        }
        for (World world : Bukkit.getWorlds()) {
            plugin.getPermAPI().playerRemoveGroup(world, player, current.getGroup());
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
        if (groups != null) {
            for (String group : groups) {
                PermClass pcl = classesToGroups.get(group);
                if (pcl == null) {
                    continue;
                }
                classMap.put(pcl.getType(), pcl);
            }
        }
        return classMap;
    }

    /**
     * Gets the class of a player.
     *
     * @param player The player to get the class of.
     * @param type The type of class to get.
     * @return The class corresponding with the type.
     */
    public PermClass getPlayerClass(String player, ClassType type) {
        return getClasses(player).get(type);
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
     * Gets a {@link ClassType} from its name.
     *
     * @param name The name of the {@link ClassType}.
     * @return The {@link ClassType} corresponding with the name, or null if
     * there is none.
     */
    public ClassType getClassTypeFromName(String name) {
        return getClassType(PermClasses.formatNameToId(name));
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
     * Saves a tier to this ClassManager.
     *
     * @param tier The tier to save.
     */
    public void saveTier(ClassTier tier) {
        String tierPath = "classes." + tier.getName();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection(tierPath);
        if (section == null) {
            section = plugin.getConfig().createSection(tierPath);
        }

        for (ClassType type : getClassTypes()) {
            section.set(type.getName(), tier.getClasses(type));
        }
        plugin.saveConfig();
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
