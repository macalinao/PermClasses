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
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

/**
 * Manages classes.
 */
public class ClassManager {

    /**
     * The {@link ClassType}s registered on the server.
     */
    private Map<String, ClassType> classTypes = new HashMap<String, ClassType>();

    /**
     * The {@link PermClass}es registered on the server.
     */
    private Map<String, PermClass> classes = new HashMap<String, PermClass>();

    /**
     * The {@link PermClass}es mapped to their group names.
     */
    private Map<String, PermClass> classesToGroups = new HashMap<String, PermClass>();

    /**
     * The prefix used for classes as defined in the permissions plugin.
     */
    private String classPrefix = "pcl_";

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
        loadClasses();
    }

    /**
     * Loads all classes.
     */
    public final void loadClasses() {
        classTypes = new HashMap<String, ClassType>();
        classes = new HashMap<String, PermClass>();
        classesToGroups = new HashMap<String, PermClass>();

        Configuration config = plugin.getConfig();
        
        
//        String[] groups = plugin.getPermAPI().getGroups();
//
//        for (String group : groups) {
//            if (!group.startsWith(classPrefix)) {
//                continue;
//            }
//
//            String className = group.substring(classPrefix.length() - 1);
//
//            PermClass pcl = new PermClass(group, className, type);
//
//            classTypes.put(type.getId(), type);
//            classes.put(pcl.getId(), pcl);
//            classesToGroups.put(group, pcl);
//        }
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
     * Saves a tier to this ClassManager.
     * 
     * @param tier The tier to save.
     */
    public void saveTier(ClassTier tier) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
