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
 * Unit tests for {@link PairLocator}.
 */
public class PairLocatorTest {

    @Test
    public void noPair() {
        List<Card> cards = Card.parse("As 7d 3c 2h Kd");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 7c 2h Kd");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace, Rank.King, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As 7d Tc 2h Kd Td 9s");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                Card.parse("As Kd Td 9s Tc"),
                Arrays.asList(Rank.Ten, Rank.Ace, Rank.King, Rank.Nine));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidates() {
        List<Card> cards = Card.parse("As 7d 7c 9h 9d");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                cards,
                Arrays.asList(Rank.Nine, Rank.Ace, Rank.Seven, Rank.Seven));

        assertEquals(expected, actual);
    }

    @Test
    public void manyCards() {
        List<Card> cards = Card.parse("2s 2h 3h 4h 5h 6h 7h 8h 9h Th");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                Card.parse("2s 2h Th 9h 8h"),
                Arrays.asList(Rank.Two, Rank.Ten, Rank.Nine, Rank.Eight));

        assertEquals(expected, actual);
    }

    @Test
    public void ignoresThreeOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h Kd");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace, Rank.King, Rank.Seven));

        assertEquals(expected, actual);
    }

    @Test
    public void allKickersAreHigher() {
        List<Card> cards = Card.parse("As 7d 7c Qh Kd");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace, Rank.King, Rank.Queen));

        assertEquals(expected, actual);
    }

    @Test
    public void allKickersAreLower() {
        List<Card> cards = Card.parse("2s 7d 7c 4h 5d");

        PairLocator locator = new PairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Pair,
                cards,
                Arrays.asList(Rank.Seven, Rank.Five, Rank.Four, Rank.Two));

        assertEquals(expected, actual);
    }
}
