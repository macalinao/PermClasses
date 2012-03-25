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
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Testing of Class Tiers.
 */
public class ClassTierTest {

    public ClassTierTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class ClassTier.
     */
    @Test
    public void testGetName() {
        System.out.println("Testing the getName method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = mock(ClassType.class);

        PermClass pcl = new PermClass(cm, "myclass", "MyClass", ct);
        List<PermClass> classes = Lists.newArrayList(pcl);

        ClassTier instance = new ClassTier(cm, "MyTier");

        String expected = "MyTier";
        String result = instance.getName();
        assertEquals(expected, result);
    }

    /**
     * Test of getClasses method, of class ClassTier.
     */
    @Test
    public void testGetClasses() {
        System.out.println("Testing the getClasses method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = mock(ClassType.class);

        PermClass pcl = new PermClass(cm, "myclass", "MyClass", ct);
        List<PermClass> classes = Lists.newArrayList(pcl);

        ClassTier instance = new ClassTier(cm, "MyTier");
        instance.addClass(pcl);

        List<PermClass> expected = classes;
        List<PermClass> result = instance.getClasses();
        assertEquals(expected, result);
    }

    /**
     * Test of addClass method, of class ClassTier.
     */
    @Test
    public void testAddClass() {
        System.out.println("Testing the addClass method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = mock(ClassType.class);

        PermClass myClass = new PermClass(cm, "myclass", "MyClass", ct);
        List<PermClass> classes = Lists.newArrayList(myClass);

        PermClass newClass = new PermClass(cm, "newclass", "NewClass", ct);

        ClassTier instance = new ClassTier(cm, "MyTier");

        instance.addClass(newClass);

        verify(cm).saveTier(instance);

        assertTrue(instance.getClasses().contains(myClass));
        assertTrue(instance.getClasses().contains(newClass));

        ClassTier expected = instance;
        ClassTier result = newClass.getTier();
        assertEquals(expected, result);
    }

}
