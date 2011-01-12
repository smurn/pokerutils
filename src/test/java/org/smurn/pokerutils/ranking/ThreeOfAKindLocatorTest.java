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
 * Unit tests for {@link ThreeOfAKindLocator}.
 */
public class ThreeOfAKindLocatorTest {

    @Test
    public void noThreeOfAKind() {
        List<Card> cards = Card.parse("As 7d 3c 2h Kd");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 7c 7h Kd");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TreeOfAKind,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace, Rank.King));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As Ts Tc 2h Kd Td 9s");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TreeOfAKind,
                Card.parse("As Kd Td Ts Tc"),
                Arrays.asList(Rank.Ten, Rank.Ace, Rank.King));

        assertEquals(expected, actual);
    }

    @Test
    public void manyCards() {
        List<Card> cards = Card.parse("2s 2h 2h 4h 5h 6h 7h 8h 9h Th");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TreeOfAKind,
                Card.parse("2s 2h Th 9h 2h"),
                Arrays.asList(Rank.Two, Rank.Ten, Rank.Nine));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidates() {
        List<Card> cards = Card.parse("2s 2h 2h 4h 7h 7s 7d Th");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TreeOfAKind,
                Card.parse("7h 7s 7d Th 4h"),
                Arrays.asList(Rank.Seven, Rank.Ten, Rank.Four));

        assertEquals(expected, actual);
    }

    @Test
    public void ignoresFourOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h 7d");

        ThreeOfAKindLocator locator = new ThreeOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TreeOfAKind,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace, Rank.Seven));

        assertEquals(expected, actual);
    }
}
