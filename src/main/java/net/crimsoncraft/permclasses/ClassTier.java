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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a tier, or group, of classes.
 */
public class ClassTier {

    /**
     * The ClassManager.
     */
    private final ClassManager classManager;

    /**
     * The id of the ClassTier.
     */
    private final String id;

    /**
     * The name of the ClassTier.
     */
    private String name;

    /**
     * The PermClasses within the tier.
     */
    private Set<PermClass> classes = new HashSet<PermClass>();

    /**
     * Constructor.
     *
     * @param cm The ClassManager.
     * @param name The name of the tier.
     * @param classes The classes within the tier.
     */
    public ClassTier(ClassManager cm, String id, String name) {
        this.classManager = cm;
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the id of this ClassTier.
     *
     * @return The id of the ClassTier.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the tier.
     *
     * @return The name of the tier.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of all PermClasses in this tier.
     *
     * @return The PermClasses in the tier.
     */
    public List<PermClass> getClasses() {
        return new ArrayList<PermClass>(classes);
    }

    /**
     * Adds a class to this class tier.
     *
     * @param pcl The class to add to the tier.
     */
    public void addClass(PermClass pcl) {
        classes.add(pcl);
        pcl.setTier(this);
        classManager.saveTier(this);
    }
    
    /**
     * Returns true if this tier contains the given class.
     * 
     * @param pcl The {@link PermClass} to check.
     * @return True if the tier contains the class.
     */
    public boolean hasClass(PermClass pcl) {
        return classes.contains(pcl);
    }

}
