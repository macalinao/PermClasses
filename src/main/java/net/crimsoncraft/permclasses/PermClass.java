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
    private final ClassType type;

    /**
     * The tier of the {@link PermClass}.
     */
    private ClassTier tier;

    /**
     * Constructor.
     *
     * @param classManager The {@link ClassManager}.
     * @param id The id of the class.
     * @param name The name of the class.
     * @param type The type of the class.
     * @param tier The tier of the class.
     */
    public PermClass(ClassManager classManager, String id, String name, ClassType type, ClassTier tier) {
        this.classManager = classManager;
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
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
     * @param tier The tier to set.
     */
    public void setTier(ClassTier tier) {
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

}
