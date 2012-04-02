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
    private final List<String> bindCmds;

    /**
     * The commands that will be executed when the class is unbound.
     */
    private final List<String> unbindCmds;

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

    public void bind(Player player) {
        for (String cmd : bindCmds) {
            if (cmd.startsWith("/")) {
                player.chat(cmd);
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }
    }

    public void unbind(Player player) {
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
