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

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ClassType tests.
 */
public class ClassTypeTest {

    public ClassTypeTest() {
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
     * Test of getId method, of class ClassType.
     */
    @Test
    public void testGetId() {
        System.out.println("Testing the getId method.");

        ClassManager cm = mock(ClassManager.class);

        ClassType instance = new ClassType(cm, "The Id");

        String expected = "the-id";
        String result = instance.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of getName method, of class ClassType.
     */
    @Test
    public void testGetName() {
        System.out.println("Testing the getName method.");

        ClassManager cm = mock(ClassManager.class);

        ClassType instance = new ClassType(cm, "The Id");

        String expected = "The Id";
        String result = instance.getName();
        assertEquals(expected, result);
    }

    /**
     * Test of addClass method, of class ClassType.
     */
    @Test
    public void testAddClass() {
        System.out.println("Testing the addClass method.");

        ClassManager cm = mock(ClassManager.class);
        ClassType instance = new ClassType(cm, "The Id");
        PermClass test = mock(PermClass.class);

        instance.addClass(test);

        verify(test).setType(instance);
    }

}
