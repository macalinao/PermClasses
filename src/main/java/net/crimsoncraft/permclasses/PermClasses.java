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
import org.bukkit.plugin.java.JavaPlugin;

/**
 * PermClasses main class.
 */
public class PermClasses extends JavaPlugin {

    private ClassManager classManager;

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "PermClasses version " + getDescription().getVersion() + " disabling...");
        this.classManager = null;
        getLogger().log(Level.INFO, "Plugin disabled.");
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "PermClasses version " + getDescription().getVersion() + " loading...");
        this.classManager = new ClassManager(this);
        getLogger().log(Level.INFO, "Plugin enabled.");
    }

    /**
     * Gets the plugin's class manager.
     * 
     * @return The class manager of the plugin.
     */
    public ClassManager getClassManager() {
        return classManager;
    }

}
