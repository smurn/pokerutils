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
import org.smurn.pokerutils.HandCategory;
import org.smurn.pokerutils.Rank;
import java.util.List;
import org.junit.Test;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link TwoPairLocator}.
 */
public class TwoPairLocatorTest {

    @Test
    public void noTwoPair() {
        List<Card> cards = Card.parse("As 7d 3c 2h Kd");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 7c Kh Kd");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TwoPair,
                cards,
                Arrays.asList(Rank.King, Rank.Seven, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As Ts Tc 2h Kd 9d 9s");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TwoPair,
                Card.parse("Tc Ts 9s 9d As"),
                Arrays.asList(Rank.Ten, Rank.Nine, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void manyCards() {
        List<Card> cards = Card.parse("2s 2h 3h 3s 5h 6h 7h 8h 9h Th");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TwoPair,
                Card.parse("2s 2h 3h 3s Th"),
                Arrays.asList(Rank.Three, Rank.Two, Rank.Ten));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidates() {
        List<Card> cards = Card.parse("As 7d 7c 9h 9d 4h 4d");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TwoPair,
                Card.parse("As 7d 7c 9h 9d"),
                Arrays.asList(Rank.Nine, Rank.Seven, Rank.Ace));

        assertEquals(expected, actual);
    }
    
    @Test
    public void ignoresFourOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h 7d");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.TwoPair,
                cards,
                Arrays.asList(Rank.Seven, Rank.Seven, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void ignoresThreeOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h 5d");

        TwoPairLocator locator = new TwoPairLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }
}
