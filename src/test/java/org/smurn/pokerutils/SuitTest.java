/*
 * Copyright 2011 stefan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.smurn.pokerutils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Suit}.
 */
public class SuitTest {

    /**
     * Tests that the number of different suits is correct.
     */
    @Test
    public void numberOfSuits() {
        assertEquals("Wrong number of suits", 4, Suit.values().length);
    }

    /**
     * Tests lower case character parsing.
     */
    @Test
    public void parseLowerCase() {
        assertEquals(Suit.Diamonds, Suit.parse('d'));
    }

    /**
     * Tests lower case character parsing.
     */
    @Test
    public void parseUpperCase() {
        assertEquals(Suit.Hearts, Suit.parse('H'));
    }

    /**
     * Tests exception throwing.
     */
    @Test(expected = IllegalArgumentException.class)
    public void parseFormatException() {
        Suit.parse('K');
    }
}
