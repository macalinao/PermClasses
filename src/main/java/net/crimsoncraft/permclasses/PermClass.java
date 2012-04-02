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

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Represents an immutable permission class.
 */
public class PermClass {

    /**
     * The {@link ClassManager}.
     */
    private final ClassManager classManager;

    /**
     * The id of the {@link PermClass}.
     */
    private final String id;

    /**
     * The name of the {@link PermClass}.
     */
    private final String name;

    /**
     * The type of the {@link PermClass}.
     */
    private ClassType type;

    /**
     * The tier of the {@link PermClass}.
     */
    private ClassTier tier;

    /**
     * The commands that will be executed when the class is bound.
     */
    private List<String> bindCmds;

    /**
     * The commands that will be executed when the class is unbound.
     */
    private List<String> unbindCmds;

    /**
     * The permission that corresponds with this PermClass.
     */
    private Permission permission;

    /**
     * Constructor.
     *
     * @param classManager The {@link ClassManager}.
     * @param id The id of the class.
     * @param name The name of the class.
     * @param type The type of the class.
     */
    public PermClass(ClassManager classManager, String name, List<String> bindCmds, List<String> unbindCmds) {
        this.classManager = classManager;
        this.id = PermClasses.formatNameToId(name);
        this.name = name;
        this.bindCmds = bindCmds;
        this.unbindCmds = unbindCmds;
    }

    /**
     * Gets the Permission corresponding with this PermClass.
     * 
     * @return The Permission of the PermClass.
     */
    public Permission getPermission() {
        ClassTier tier = getTier();
        String tierId = (tier == null) ? "default" : tier.getId();
        String permName = "pcl.use." + tierId + "." + getId();

        if (this.permission == null) {
            permission = new Permission(permName);
            permission.addParent(tier.getPermission(), true);
            Bukkit.getPluginManager().addPermission(permission);
        }

        return permission;
    }

    /**
     * Gets the id of this PermClass.
     *
     * @return This PermClass's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of this PermClass.
     *
     * @return The name of this PermClass.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of PermClass this is.
     *
     * @return The type of this PermClass.
     */
    public ClassType getType() {
        return type;
    }

    /**
     * Sets the type of the class.
     *
     * <p><strong>Internal use only. See {@link ClassType#addClass(net.crimsoncraft.permclasses.PermClass)}.
     * </strong></p>
     *
     * @param type The {@link ClassType} to set.
     */
    void setType(ClassType type) {
        this.type = type;
    }

    /**
     * Gets the tier of the class.
     *
     * @return The tier of the class.
     */
    public ClassTier getTier() {
        return tier;
    }

    /**
     * Sets the tier of the class.
     *
     * <p><strong> Internal use only. See {@link ClassTier#addClass(net.crimsoncraft.permclasses.PermClass)}.
     * </strong></p>
     *
     * @param tier The tier to set.
     */
    void setTier(ClassTier tier) {
        this.tier = tier;
    }

    /**
     * Gets the group name of this {@link PermClass}.
     *
     * @return The group name of the {@link PermClass}.
     */
    public String getGroup() {
        return new StringBuilder(classManager.getGroupPrefix()).append(getId()).toString();
    }

    /**
     * Binds this PermClass to the specified player.
     *
     * @param player The player to bind this class to.
     */
    public void bind(Player player) {
        this.classManager.setClass(player.getName(), this);
        for (String cmd : bindCmds) {
            if (cmd.startsWith("/")) {
                player.chat(cmd);
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }
    }

    /**
     * Unbinds this PermClass from the specified player.
     *
     * @param player The player to unbind this class from.
     */
    public void unbind(Player player) {
        this.classManager.resetClass(player.getName(), getType());
        for (String cmd : unbindCmds) {
            if (cmd.startsWith("/")) {
                player.chat(cmd);
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }
    }

    @Override
    public String toString() {
        return "PermClass{" + "id=" + id + ", name=" + name + ", type=" + type + ", tier=" + tier + '}';
    }

}
