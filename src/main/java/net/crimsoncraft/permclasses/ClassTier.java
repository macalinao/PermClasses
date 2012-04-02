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

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

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
    private Map<ClassType, Set<PermClass>> classes = new HashMap<ClassType, Set<PermClass>>();

    /**
     * The Permission of this tier.
     */
    private Permission permission;

    /**
     * Constructor.
     *
     * @param cm The ClassManager.
     * @param name The name of the tier.
     */
    public ClassTier(ClassManager cm, String name) {
        this.classManager = cm;
        this.id = PermClasses.formatNameToId(name);
        this.name = name;
    }

    /**
     * Gets the Permission corresponding with this ClassTier.
     *
     * @return The Permission corresponding with the ClassTier.
     */
    public Permission getPermission() {
        String permName = "pcl.use." + getId();
        if (permission == null || !permission.getName().equalsIgnoreCase(permName)) {
            permission = new Permission(permName);
            Bukkit.getPluginManager().addPermission(permission);
        }

        return permission;
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
     * Gets all classes that correspond with the given {@link ClassType}.
     *
     * <p><strong> Internal use only. See {@link ClassTier#getClasses(net.crimsoncraft.permclasses.ClassType)}.
     * </strong></p>
     *
     * @param type The {@link ClassType} to get.
     * @return A {@link Set} of {@link {PermClass}es of the {@link ClassType}.
     */
    private Set<PermClass> getClassesInternal(ClassType type) {
        Set<PermClass> typeClasses = classes.get(type);
        if (typeClasses == null) {
            typeClasses = new HashSet<PermClass>();
            classes.put(type, typeClasses);
        }
        return typeClasses;
    }

    /**
     * Gets all classes that correspond with the given {@link ClassType}.
     *
     * @param type The {@link ClassType} to get.
     * @return A {@link Set} of {@link {PermClass}es of the {@link ClassType}.
     */
    public Set<PermClass> getClasses(ClassType type) {
        return new HashSet<PermClass>(getClassesInternal(type));
    }

    /**
     * Gets a list of all PermClasses in this tier.
     *
     * @return The PermClasses in the tier.
     */
    public Set<PermClass> getClasses() {
        Set<PermClass> fullSet = new HashSet<PermClass>();
        for (ClassType type : classManager.getClassTypes()) {
            fullSet.addAll(getClassesInternal(type));
        }
        return fullSet;
    }

    /**
     * Adds a class to this class tier.
     *
     * @param type The {@link ClassType} to add.
     * @param className The name of the {@link PermClass} to create.
     * @param bindCmds A list of commands to be executed when the class is
     * bound.
     * @param unbindCmds A list of commands to be executed when the class is
     * unbound.
     * @return The created {@link PermClass}.
     */
    public PermClass createClass(ClassType type, String className, List<String> bindCmds, List<String> unbindCmds) {
        PermClass pcl = classManager.createClass(className, bindCmds, unbindCmds);
        type.addClass(pcl);
        addClass(pcl);
        return pcl;
    }

    /**
     * Adds a class to this class tier.
     *
     * @param pcl The class to add to the tier.
     */
    public void addClass(PermClass pcl) {
        getClassesInternal(pcl.getType()).add(pcl);
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
        return getClasses().contains(pcl);
    }

    @Override
    public String toString() {
        return "ClassTier{" + "id=" + id + ", name=" + name + '}';
    }

}
