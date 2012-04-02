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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
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
        ClassType ct = new ClassType(cm, "MyType");
        Set<ClassType> types = Sets.newHashSet(ct);
        when(cm.getClassTypes()).thenReturn(types);

        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass pcl = new PermClass(cm, "MyClass", bindCmds, unbindCmds);
        ct.addClass(pcl);
        Set<PermClass> classes = Sets.newHashSet(pcl);

        ClassTier instance = new ClassTier(cm, "MyTier");
        instance.addClass(pcl);

        Set<PermClass> expected = classes;
        Set<PermClass> result = instance.getClasses();
        assertEquals(expected, result);
    }

    @Test
    public void testGetClasses_classType() {
        System.out.println("Testing the getClasses method with a classType.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = new ClassType(cm, "MyType");
        Set<ClassType> types = Sets.newHashSet(ct);
        when(cm.getClassTypes()).thenReturn(types);

        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass pcl = new PermClass(cm, "MyClass", bindCmds, unbindCmds);
        ct.addClass(pcl);
        Set<PermClass> classes = Sets.newHashSet(pcl);

        ClassTier instance = new ClassTier(cm, "MyTier");
        instance.addClass(pcl);

        Set<PermClass> expected = classes;
        Set<PermClass> result = instance.getClasses(ct);
        assertEquals(expected, result);
    }

    public void testCreateClass() {
        System.out.println("Testing the createClass method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = mock(ClassType.class);

        ClassTier instance = new ClassTier(cm, "My Id");
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass created = instance.createClass(ct, "Explorer", bindCmds, unbindCmds);

        verify(ct).addClass(created);

        ClassTier expected = instance;
        ClassTier result = created.getTier();
        assertEquals(expected, result);
    }

    /**
     * Test of addClass method, of class ClassTier.
     */
    @Test
    public void testAddClass() {
        System.out.println("Testing the addClass method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType ct = new ClassType(cm, "MyType");
        Set<ClassType> types = Sets.newHashSet(ct);
        when(cm.getClassTypes()).thenReturn(types);

        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass myClass = new PermClass(cm, "MyClass", bindCmds, unbindCmds);
        ct.addClass(myClass);
        List<String> bindCmds2 = Lists.newArrayList("/hero bind2");
        List<String> unbindCmds2 = Lists.newArrayList("/my cmd2", "/other cmd2", "console cmd2");
        PermClass newClass = new PermClass(cm, "NewClass", bindCmds2, unbindCmds2);
        ct.addClass(newClass);

        ClassTier instance = new ClassTier(cm, "MyTier");
        instance.addClass(myClass);

        assertTrue(instance.getClasses().contains(myClass));

        instance.addClass(newClass);
        verify(cm, times(2)).saveTier(instance);

        assertTrue(instance.getClasses().contains(myClass));
        assertTrue(instance.getClasses().contains(newClass));

        ClassTier expected = instance;
        ClassTier result = newClass.getTier();
        assertEquals(expected, result);
    }

}
