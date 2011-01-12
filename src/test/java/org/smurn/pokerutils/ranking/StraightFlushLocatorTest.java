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
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;
import org.smurn.pokerutils.HandCategory;
import org.smurn.pokerutils.Rank;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link StraightFlushLocator}.
 */
public class StraightFlushLocatorTest {

    @Test
    public void noStaightFlush() {
        List<Card> cards = Card.parse("2s 3s 4s 5s 6h");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("2s 3s 4s 5s 6s");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.StraightFlush,
                cards,
                Arrays.asList(Rank.Six));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("3s Ad 4s 5s Kc 6s 7s ");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.StraightFlush,
                Card.parse("7s 3s 4s 5s 6s"),
                Arrays.asList(Rank.Seven));

        assertEquals(expected, actual);
    }

    @Test
    public void mutlipleCandiates() {
        List<Card> cards = Card.parse("7s 3s 4s 5s 6s 8h 9h Th Jh Qh");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.StraightFlush,
                Card.parse("8h 9h Th Jh Qh"),
                Arrays.asList(Rank.Queen));

        assertEquals(expected, actual);
    }

    @Test
    public void longStraight() {
        List<Card> cards = Card.parse("6h 7h 8h 9h Th Jh Qh");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.StraightFlush,
                Card.parse("8h 9h Th Jh Qh"),
                Arrays.asList(Rank.Queen));

        assertEquals(expected, actual);
    }

    @Test
    public void interleaved() {
        List<Card> cards = Card.parse("Ah Ad Kh Kd Qh Qd Jh Jd Th 9d");

        StraightFlushLocator locator = new StraightFlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.StraightFlush,
                Card.parse("Ah Kh Qh Jh Th"),
                Arrays.asList(Rank.Ace));

        assertEquals(expected, actual);
    }
}
