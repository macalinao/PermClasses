/*
 * This file is part of PermClasses.
 *
 * PermClasses is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PermClasses is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with PermClasses.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.crimsoncraft.permclasses;

import java.util.HashSet;
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
    private Set<PermClass> classes = new HashSet<PermClass>();

    /**
     * Constructor.
     *
     * @param classManager The {@link ClassManager}.
     * @param name The name of the ClassType.
     */
    public ClassType(ClassManager classManager, String name) {
        this.classManager = classManager;
        this.id = PermClasses.formatNameToId(name);
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

    @Override
    public String toString() {
        return "ClassType{id=" + id + ", name=" + name + '}';
    }

}
