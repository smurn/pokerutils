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
 * Unit tests for {@link Rank}.
 */
public class RankTest {

    /**
     * Tests if there are 13 ranks.
     */
    @Test
    public void numberOfRanks() {
        assertEquals("Wrong number of ranks.", 13, Rank.values().length);
    }

    /**
     * Tests that 'two' has the lowest rank value (and not the 'ace').
     */
    @Test
    public void lowestRankValue() {
        assertEquals("'two' does not have the right rank value.",
                0, Rank.Two.getRankValue());
    }

    /**
     * Tests that 'ace' has the highest rank value.
     */
    @Test
    public void highestRankValue() {
        assertEquals("'ace' does not have the right rank value.",
                12, Rank.Ace.getRankValue());
    }

    /**
     * Tests that 'ace' has the highest rank value.
     */
    @Test
    public void JackRankValue() {
        assertEquals("'jack' does not have the right rank value.",
                9, Rank.Jack.getRankValue());
    }

    /**
     * Tests lower case character parsing.
     */
    @Test
    public void parseLowerCase() {
        assertEquals(Rank.Ace, Rank.parse('a'));
    }

    /**
     * Tests lower case character parsing.
     */
    @Test
    public void parseUpperCase() {
        assertEquals(Rank.Ace, Rank.parse('A'));
    }

    /**
     * Tests number character parsing.
     */
    @Test
    public void parseNumber() {
        assertEquals(Rank.Seven, Rank.parse('7'));
    }

    /**
     * Tests exception throwing.
     */
    @Test(expected = IllegalArgumentException.class)
    public void parseFormatException() {
        Rank.parse('D');
    }
}
