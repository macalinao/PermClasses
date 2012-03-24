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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author ianschool
 */
public class PermClassesTest {

    public PermClassesTest() {
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

    @Test
    public void testFormatNameToId() {
        System.out.println("Testing the formatNameToId method.");

        String name = "This_is MY clAss_iD";

        String expected = "this-is-my-class-id";
        String result = PermClasses.formatNameToId(name);

        assertEquals(expected, result);
    }

}
