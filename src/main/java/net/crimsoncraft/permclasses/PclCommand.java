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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Please specify an action: /pcl (set|rm|reset|reload).");
            return;
        }

        String action = args[0];

        if (action.equalsIgnoreCase("set")) {
        }
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

        Player plr = Bukkit.getPlayer(player);
        if (plr == null) {
            sender.sendMessage(ChatColor.RED + "The player you specified is not online or does not exist.");
            return;
        }
        pcl.bind(plr);

        sender.sendMessage(ChatColor.YELLOW + "The class of the player '" + player + "' was successfully set to '" + pcl.getName() + "'.");
    }

    /**
     * Performs the /pcl rm command.
     *
     * <p>/pcl set &lt;player&gt; &lt;classType&gt;</p>
     *
     * <p>Removes a player’s class.</p>
     *
     * @param sender The sender of the command.
     * @param player The player to set the class of.
     * @param classType The type of class to remove.
     */
    public void doRm(CommandSender sender, String player, String classType) {
        if (!sender.hasPermission("pcl.admin.rm")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return;
        }

        ClassType type = plugin.getClassManager().getClassTypeFromName(classType);
        if (type == null) {
            sender.sendMessage(ChatColor.RED + "The specified class '" + classType + "' does not exist.");
            return;
        }

        Player plr = Bukkit.getPlayer(player);
        if (plr == null) {
            sender.sendMessage(ChatColor.RED + "The player you specified is not online.");
            return;
        }

        PermClass current = plugin.getClassManager().getPlayerClass(plr.getName(), type);
        if (current == null) {
            sender.sendMessage(ChatColor.RED + "The player does not have a class associated with the specified class type.");
            return;
        }

        current.unbind(plr);
        sender.sendMessage(ChatColor.YELLOW + "The class of type '" + type.getName() + "' of the player '" + player + "' was successfully removed.");
    }

    /**
     * Performs the /pcl reset command.
     *
     * @param sender
     * @param player
     */
    public void doReset(CommandSender sender, String player) {
        if (!sender.hasPermission("pcl.admin.rm")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return;
        }

        Player plr = Bukkit.getPlayer(player);
        if (plr == null) {
            sender.sendMessage(ChatColor.RED + "The player you specified is not online.");
            return;
        }

        for (ClassType type : plugin.getClassManager().getClassTypes()) {
            PermClass current = plugin.getClassManager().getPlayerClass(plr.getName(), type);
            if (current != null) {
                current.unbind(plr);
            }
        }

        sender.sendMessage(ChatColor.YELLOW + "The classes of the player " + plr.getName() + " have been reset seccessfully.");
    }

    /**
     * Performs the /pcl reload command.
     *
     * @param sender
     */
    public void doReload(CommandSender sender) {
        if (!sender.hasPermission("pcl.admin.reload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW + "Reloading PermClasses...");
        plugin.reload();
        sender.sendMessage(ChatColor.YELLOW + "PermClasses reloaded.");
    }

}
