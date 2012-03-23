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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Manages classes.
 */
public class ClassManager {

    private Map<String, PermClass> classes = new HashMap<String, PermClass>();
    private PermClasses plugin;
    private net.milkbowl.vault.permission.Permission permissionsHook;

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
            permissionsHook = permissionProvider.getProvider();
        }
        
        if (permissionsHook == null) {
            plugin.getLogger().log(Level.SEVERE, "No permissions plugin detected! This is pointless! Disabling the plugin.");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
