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

import java.util.Map;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.spout.api.ChatColor;

/**
 * The main PermissionsClasses command.
 */
public class PclCommand implements CommandExecutor {

    private final PermClasses plugin;

    public PclCommand(PermClasses plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.parseCommand(sender, cmd, label, args);
        return true;
    }

    public void parseCommand(CommandSender sender, Command cmd, String label, String[] args) {
    }

    /**
     * Performs the /pcl set command.
     *
     * <p>/pcl set &lt;player&gt; &lt;className&gt;</p>
     *
     * <p>Sets a player’s class to the specified class name.</p>
     *
     * @param sender The sender of the command.
     * @param player The player to set the class of.
     * @param className The name of the class to set.
     */
    public void doSet(CommandSender sender, String player, String className) {
        if (!sender.hasPermission("pcl.admin.set")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return;
        }

        PermClass pcl = plugin.getClassManager().getClassFromName(className);
        if (pcl == null) {
            sender.sendMessage(ChatColor.RED + "The specified class '" + className + "' does not exist.");
            return;
        }

        plugin.getClassManager().setClass(player, pcl);
        sender.sendMessage(ChatColor.YELLOW + "The class of the player '" + player + "' was successfully set to '" + pcl.getName() + "'.");
    }

}
