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
 * Represents a permission class.
 */
public class PermClass {

    private String name;

    private ClassType type;

    public PermClass(String name, ClassType type) {
        this.name = name;
        this.type = type;
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

}
