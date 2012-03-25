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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Tests of the ClassManager.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Bukkit.class})
public class ClassManagerTest {

    private PermClasses plugin;

    private ClassManager instance;

    private Permission permAPI;

    private World world;

    public ClassManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        plugin = mock(PermClasses.class);

        //Mock a config
        FileConfiguration mockConfig = mock(FileConfiguration.class);
        when(plugin.getConfig()).thenReturn(mockConfig);
        ConfigurationSection classesSection = mock(ConfigurationSection.class);
        when(mockConfig.getConfigurationSection("classes")).thenReturn(classesSection);

        Set<String> tiers = Sets.newHashSet("Tier 1", "Tier 2");
        when(classesSection.getKeys(false)).thenReturn(tiers);

        //Tier 1
        ConfigurationSection tier1Section = mock(ConfigurationSection.class);
        when(classesSection.getConfigurationSection("Tier 1")).thenReturn(tier1Section);
        when(mockConfig.getConfigurationSection("classes.Tier 1")).thenReturn(tier1Section);

        Set<String> keys1 = Sets.newHashSet("Combat", "Economic");
        when(tier1Section.getKeys(false)).thenReturn(keys1);

        List<String> combat1 = Lists.newArrayList("rettata", "Migaaa", "Afforess");
        List<String> economic1 = Lists.newArrayList("sadfac", "asdasd", "Duplicate");

        when(tier1Section.getStringList("Combat")).thenReturn(combat1);
        when(tier1Section.getStringList("Economic")).thenReturn(economic1); //case-i

        //Tier 2
        ConfigurationSection tier2Section = mock(ConfigurationSection.class);
        when(classesSection.getConfigurationSection("Tier 2")).thenReturn(tier2Section);
        when(mockConfig.getConfigurationSection("classes.Tier 2")).thenReturn(tier2Section);

        Set<String> keys2 = Sets.newHashSet("Combat", "ecoNOMIC", "Random");
        when(tier2Section.getKeys(false)).thenReturn(keys2);

        List<String> combat2 = Lists.newArrayList("Archer", "Uber", "Droid");
        List<String> economic2 = Lists.newArrayList("Mayor", "Loser", "DupLICate");
        List<String> random2 = Lists.newArrayList("Randy", "ran_do N", "R anasas");

        when(tier2Section.getStringList("Combat")).thenReturn(combat2);
        when(tier2Section.getStringList("ecoNOMIC")).thenReturn(economic2); //case-i
        when(tier2Section.getStringList("Random")).thenReturn(random2);
        //End config mock

        //Mock player groups
        world = mock(World.class);
        PowerMockito.mockStatic(Bukkit.class);
        List<World> worlds = Lists.newArrayList(world);
        when(Bukkit.getWorlds()).thenReturn(worlds);

        permAPI = mock(Permission.class);
        when(plugin.getPermAPI()).thenReturn(permAPI);
        String[] groups = {"pcl_randy", "pcl_droid", "pcl_duplicate"};
        when(permAPI.getPlayerGroups(world, "AlbireoX")).thenReturn(groups);
        //End mock

        instance = new ClassManager(plugin);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadAll method, of class ClassManager.
     */
    @Test
    public void testLoadAll_duplicate() {
        System.out.println("Testing the loadAll method.");

        instance.loadAll();

        //Test duplicate
        PermClass duplicate = instance.getClassFromName("duplicate");

        String expected = "DupLICate";
        String result = duplicate.getName();
        assertEquals(expected, result);

        expected = "duplicate";
        result = duplicate.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of createClass method, of class ClassManager.
     */
    @Test
    public void testCreateClass() {
        System.out.println("Testing the createClass method.");

        PermClass pcl = instance.createClass("MyClass");

        String expected = "MyClass";
        String result = pcl.getName();
        assertEquals(expected, result);

        expected = "myclass";
        result = pcl.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of getClassFromId method, of class ClassManager.
     */
    @Test
    public void testGetClassFromId() {
        System.out.println("Testing the getClassFromId method.");

        String id = "rettata";

        PermClass pcl = instance.getClassFromId(id);

        String expected = id;
        String result = pcl.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of getClassFromName method, of class ClassManager.
     */
    @Test
    public void testGetClassFromName() {
        System.out.println("Testing the getClassFromName method.");

        String name = "ran_DO_n";
        PermClass pcl = instance.getClassFromName(name);

        String expected = "ran-do-n";
        String result = pcl.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of getClassTypes method, of class ClassManager.
     */
    @Test
    public void testGetClassTypes() {
        System.out.println("Testing the getClassTypes method.");

        Set<ClassType> types = instance.getClassTypes();
        Set<String> ids = Sets.newHashSet();
        for (ClassType type : types) {
            ids.add(type.getId());
        }

        Set<String> expected = Sets.newHashSet("default", "economic", "combat", "random");
        Set<String> result = ids;
        assertEquals(expected, result);
    }

    /**
     * Test of setClass method, of class ClassManager.
     */
    @Test
    public void testSetClass() {
        System.out.println("Testing the setClass method.");

        String player = "AlbireoX";

        PermClass pcl = instance.getClassFromId("afforess");
        instance.setClass(player, pcl);

        verify(permAPI).playerAddGroup(world, player, "pcl_afforess");
        verify(permAPI).playerRemoveGroup(world, player, "pcl_droid");
    }

    /**
     * Test of resetClass method, of class ClassManager.
     */
    @Test
    public void testResetClass() {
        System.out.println("Testing the resetClass method.");

        String player = "AlbireoX";
        ClassType type = instance.getClassType("economic");
        PermClass example = instance.getClassFromId("afforess");
        instance.setClass(player, example);

        verify(permAPI).playerRemoveGroup(world, player, "pcl_droid");
        verify(permAPI).playerAddGroup(world, player, "pcl_afforess");

        instance.resetClass(player, type);

        verify(permAPI).playerRemoveGroup(world, player, "pcl_droid");
    }

    /**
     * Test of getClasses method, of class ClassManager.
     */
    @Test
    public void testGetClasses() {
        System.out.println("Testing the getClasses method.");

        String player = "AlbireoX";
        PermClass combat = instance.getClassFromName("droid");
        ClassType comb = instance.getClassType("combat");
        PermClass economic = instance.getClassFromName("duplicate");
        ClassType econ = economic.getType();
        PermClass random = instance.getClassFromName("randy");
        ClassType rand = random.getType();

        Map<ClassType, PermClass> expected = new HashMap<ClassType, PermClass>();
        expected.put(comb, combat);
        expected.put(econ, economic);
        expected.put(rand, random);

        Map<ClassType, PermClass> result = instance.getClasses(player);
        assertEquals(expected, result);
    }

    /**
     * Test of getGroupPrefix method, of class ClassManager.
     */
    @Test
    public void testGetGroupPrefix() {
        System.out.println("Testing the getGroupPrefix method.");

        String expected = "pcl_";
        String result = instance.getGroupPrefix();
        assertEquals(expected, result);
    }

}
