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

import java.util.Set;

/**
 * Represents a type of class.
 */
public class ClassType {

    /**
     * The {@link ClassManager}.
     */
    private final ClassManager classManager;

    /**
     * The id of the ClassType.
     */
    private final String id;

    /**
     * The name of the ClassType.
     */
    private String name;

    /**
     * The classes this ClassType contains.
     */
    private Set<PermClass> classes;

    /**
     * Constructor.
     *
     * @param classManager The {@link ClassManager}.
     * @param id The id of the {@link ClassType}.
     * @param name The name of the ClassType.
     */
    public ClassType(ClassManager classManager, String id, String name) {
        this.classManager = classManager;
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

    /**
     * Adds a class to this {@link ClassType}.
     *
     * @param pcl The {@link PermClass} to add.
     */
    public void addClass(PermClass pcl) {
        classes.add(pcl);
        pcl.setType(this);
    }

}
