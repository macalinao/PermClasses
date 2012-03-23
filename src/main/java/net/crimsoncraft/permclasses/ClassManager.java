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
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Manages classes.
 */
public class ClassManager {

    private Map<String, ClassType> classTypes = new HashMap<String, ClassType>();

    private Map<String, PermClass> classes = new HashMap<String, PermClass>();

    private String classPrefix = "pcl_";

    private PermClasses plugin;

    private net.milkbowl.vault.permission.Permission permAPI;

    public ClassManager(PermClasses plugin) {
        this.plugin = plugin;
        setupPermissions();
    }

    /**
     * Sets up permissions for the plugin.
     */
    private void setupPermissions() {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permAPI = permissionProvider.getProvider();
        }

        if (permAPI == null) {
            plugin.getLogger().log(Level.SEVERE, "No permissions plugin detected! This is pointless! Disabling the plugin.");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Gets a permissions class from its name.
     * 
     * @param name The name of the PermClass.
     * @return The PermClass that corresponds to the name.
     */
    public PermClass getClassFromName(String name) {
        return classes.get(name);
    }

    /**
     * Adds a permissions class to this ClassManager. Names are not checked.
     * 
     * @param pcl The {@link PermClass} to add.
     */
    public void addClass(PermClass pcl) {
        classes.put(pcl.getName(), pcl);
    }

    public List<ClassType> getClassTypes() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gets the classes of a player.
     *
     * @param player The player to get the classes of.
     * @return The player's classes.
     */
    public Map<ClassType, PermClass> getClasses(String player) {
        String[] groups = permAPI.getPlayerGroups(Bukkit.getWorlds().get(0), player);
        Map<ClassType, PermClass> classMap = new HashMap<ClassType, PermClass>();
        for (String group : groups) {
            if (!group.startsWith("pcl_")) {
                continue;
            }

            for (ClassType type : getClassTypes()) {
                if (!group.startsWith("pcl_" + type.getId())) {
                    continue;
                }

                int index = type.getId().length() + classPrefix.length() - 1;
                String classId = group.substring(index);
            }
        }
        return classMap;
    }

}
