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

import java.util.logging.Level;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * PermClasses main class.
 */
public class PermClasses extends JavaPlugin {

    private ClassManager classManager;

    private PclCommand pclCommand;

    /**
     * The hook to the Permission API.
     */
    private net.milkbowl.vault.permission.Permission permAPI;

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "PermClasses version " + getDescription().getVersion() + " disabling...");
        this.classManager = null;
        this.pclCommand = null;
        getLogger().log(Level.INFO, "Plugin disabled.");
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "PermClasses version " + getDescription().getVersion() + " loading...");
        getConfig().options().copyDefaults(true);
        this.classManager = new ClassManager(this);
        this.pclCommand = new PclCommand(this);
        getCommand("pcl").setExecutor(pclCommand);
        setupPermissions();
        getLogger().log(Level.INFO, "Plugin enabled.");
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
            getLogger().log(Level.SEVERE, "No permissions plugin detected! This is pointless! Disabling the plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    /**
     * Reloads the plugin's configuration.
     */
    public void reload() {
        this.reloadConfig();
        this.classManager.loadAll();
    }

    /**
     * Gets the plugin's class manager.
     *
     * @return The class manager of the plugin.
     */
    public ClassManager getClassManager() {
        return classManager;
    }

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public PclCommand getPclCommand() {
        return pclCommand;
    }

    /**
     * Gets the Permission API.
     *
     * @return The Permission API.
     */
    public Permission getPermAPI() {
        return permAPI;
    }

    /**
     * Formats a name to an id. s
     *
     * @param name The name of the class.
     * @return The class id.
     */
    public static String formatNameToId(String name) {
        return name.toLowerCase().replace(' ', '-').replace('_', '-');
    }

}
