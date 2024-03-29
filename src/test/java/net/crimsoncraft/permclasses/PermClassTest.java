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
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Testing of PermClass.
 */
public class PermClassTest {

    public PermClassTest() {
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
     * Test of getId method, of class PermClass.
     */
    @Test
    public void testGetId() {
        System.out.println("Testing the getId method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        String expected = "permclass";
        String result = instance.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of getName method, of class PermClass.
     */
    @Test
    public void testGetName() {
        System.out.println("Testing the getName method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        String expected = "PermClass";
        String result = instance.getName();
        assertEquals(expected, result);
    }

    /**
     * Test of getType method, of class PermClass.
     */
    @Test
    public void testGetType() {
        System.out.println("Testing the getType method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        ClassType type = mock(ClassType.class);
        instance.setType(type);

        ClassType expected = type;
        ClassType result = instance.getType();
        assertEquals(expected, result);
    }

    /**
     * Test of setType method, of class PermClass.
     */
    @Test
    public void testSetType() {
        System.out.println("Testing the setType method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        ClassType type = mock(ClassType.class);
        instance.setType(type);

        ClassType expected = type;
        ClassType result = instance.getType();
        assertEquals(expected, result);
    }

    /**
     * Test of getTier method, of class PermClass.
     */
    @Test
    public void testGetTier() {
        System.out.println("Testing the getTier method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        ClassTier tier = mock(ClassTier.class);
        instance.setTier(tier);

        ClassTier expected = tier;
        ClassTier result = instance.getTier();
        assertEquals(expected, result);
    }

    /**
     * Test of setTier method, of class PermClass.
     */
    @Test
    public void testSetTier() {
        System.out.println("Testing the setTier method.");

        ClassManager cm = mock(ClassManager.class);
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        ClassTier tier = mock(ClassTier.class);
        instance.setTier(tier);

        ClassTier expected = tier;
        ClassTier result = instance.getTier();
        assertEquals(expected, result);
    }

    /**
     * Test of getGroup method, of class PermClass.
     */
    @Test
    public void testGetGroup() {
        System.out.println("Testing the getGroup method.");

        ClassManager cm = mock(ClassManager.class);
        when(cm.getGroupPrefix()).thenReturn("pcl_");
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass", bindCmds, unbindCmds);

        String expected = "pcl_permclass";
        String result = instance.getGroup();
        assertEquals(expected, result);
    }

    /**
     * Test of getGroup method, of class PermClass.
     */
    @Test
    public void testGetGroup_spaces() {
        System.out.println("Testing the getGroup method with spaces.");

        ClassManager cm = mock(ClassManager.class);
        when(cm.getGroupPrefix()).thenReturn("pcl_");
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass With Spaces", bindCmds, unbindCmds);

        String expected = "pcl_permclass-with-spaces";
        String result = instance.getGroup();
        assertEquals(expected, result);
    }

    /**
     * Test of getGroup method, of class PermClass.
     */
    @Test
    public void testGetGroup_underscores() {
        System.out.println("Testing the getGroup method with underscores.");

        ClassManager cm = mock(ClassManager.class);
        when(cm.getGroupPrefix()).thenReturn("pcl_");
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass_under_scores", bindCmds, unbindCmds);

        String expected = "pcl_permclass-under-scores";
        String result = instance.getGroup();
        assertEquals(expected, result);
    }

    /**
     * Test of getGroup method, of class PermClass.
     */
    @Test
    public void testGetGroup_mixed() {
        System.out.println("Testing the getGroup method with spaces and underscores.");

        ClassManager cm = mock(ClassManager.class);
        when(cm.getGroupPrefix()).thenReturn("pcl_");
        List<String> bindCmds = Lists.newArrayList("/hero bind");
        List<String> unbindCmds = Lists.newArrayList("/my cmd", "/other cmd", "console cmd");
        PermClass instance = new PermClass(cm, "PermClass with spaces_and under_s_c_rASdasda_", bindCmds, unbindCmds);

        String expected = "pcl_permclass-with-spaces-and-under-s-c-rasdasda-";
        String result = instance.getGroup();
        assertEquals(expected, result);
    }

}
