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
package org.smurn.pokerutils.ranking;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.smurn.pokerutils.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link StraightLocator}.
 */
public class StraightLocatorTest {

    @Test
    public void noStraight() {
        List<Card> cards = Card.parse("2s 3s 4s 5s 7h");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("2s 3s 4s 5s 6h");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Straight,
                cards,
                Arrays.asList(Rank.Six));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("3s Ad 4s 5s Kc 6h 7h ");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Straight,
                Card.parse("7h 3s 4s 5s 6h"),
                Arrays.asList(Rank.Seven));

        assertEquals(expected, actual);
    }

    @Test
    public void straightWithPair() {
        List<Card> cards = Card.parse("2s 3s 4s 4s 5s 6h");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Straight,
                Card.parse("2s 3s 4s 5s 6h"),
                Arrays.asList(Rank.Six));

        assertEquals(expected, actual);
    }

    @Test
    public void mutlipleCandiates() {
        List<Card> cards = Card.parse("7h 3s 4s 5s 6h 8h 9h Th Jh Qh");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Straight,
                Card.parse("8h 9h Th Jh Qh"),
                Arrays.asList(Rank.Queen));

        assertEquals(expected, actual);
    }

    @Test
    public void longStraight() {
        List<Card> cards = Card.parse("6h 7h 8h 9h Th Jh Qh");

        StraightLocator locator = new StraightLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Straight,
                Card.parse("8h 9h Th Jh Qh"),
                Arrays.asList(Rank.Queen));

        assertEquals(expected, actual);
    }
}
