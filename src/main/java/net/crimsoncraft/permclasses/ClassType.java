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
 * Represents a type of class.
 */
public class ClassType {

    /**
     * The id of the ClassType.
     */
    private final String id;

    /**
     * The name of the ClassType.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param name The name of the ClassType.
     */
    public ClassType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of this ClassType.
     *
     * @return The ID of the ClassType.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of this class type.
     *
     * @return The name of this ClassType.
     */
    public String getName() {
        return name;
    }

}
