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
 * Unit tests for {@link FullHouseLocator}.
 */
public class FullHouseLocatorTest {

    @Test
    public void noFullHouse() {
        List<Card> cards = Card.parse("As 7d 7c 7h Kd");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 7c 7h Ad");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As Ts Tc 2h Kd Td 2s");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                Card.parse("2h 2s Td Ts Tc"),
                Arrays.asList(Rank.Ten, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void manyCards() {
        List<Card> cards = Card.parse("2s 2h 2h 4h 4s 6h 7h 8h 9h Th");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                Card.parse("2s 2h 2h 4h 4s"),
                Arrays.asList(Rank.Two, Rank.Four));

        assertEquals(expected, actual);
    }

    @Test
    public void twoThreeOfAKinds() {
        List<Card> cards = Card.parse("2h 2h 2h 4h 4s 4d Ad");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                Card.parse("2h 2h 4h 4s 4d"),
                Arrays.asList(Rank.Four, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidatesForThreeOfAKind() {
        List<Card> cards = Card.parse("2h 2h As Ac 2h 4h 4s Ad");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                Card.parse("As Ac Ad 4h 4s"),
                Arrays.asList(Rank.Ace, Rank.Four));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidatesForPair() {
        List<Card> cards = Card.parse("2h 2h As Ac 4h 4s Ad");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FullHouse,
                Card.parse("As Ac Ad 4h 4s"),
                Arrays.asList(Rank.Ace, Rank.Four));

        assertEquals(expected, actual);
    }

    @Test
    public void ignoresFourOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h 7d");

        FullHouseLocator locator = new FullHouseLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }
}
