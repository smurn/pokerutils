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
 * Unit tests for {@link FlushLocator}.
 */
public class FlushLocatorTest {

    @Test
    public void noFlush() {
        List<Card> cards = Card.parse("As 7d 7c 7h Kd");

        FlushLocator locator = new FlushLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("2d 5d 7d 9d Jd");

        FlushLocator locator = new FlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Flush,
                cards,
                Arrays.asList(Rank.Jack, Rank.Nine, Rank.Seven, Rank.Five, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("2d 2h 5d 7d Ac 9d Jd");

        FlushLocator locator = new FlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Flush,
                Card.parse("2d 5d 7d 9d Jd"),
                Arrays.asList(Rank.Jack, Rank.Nine, Rank.Seven, Rank.Five, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidates() {
        List<Card> cards = Card.parse("2d 5d 7d 9d Jd 2h 4h 5h Jh Ah");

        FlushLocator locator = new FlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Flush,
                Card.parse("2h 4h 5h Jh Ah"),
                Arrays.asList(Rank.Ace, Rank.Jack, Rank.Five, Rank.Four, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandidatesLast() {
        List<Card> cards = Card.parse("3h 4h 5h Jh Ah 2d 4d 5d Jd Ad 2s 4s 5s Js As");

        FlushLocator locator = new FlushLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.Flush,
                Card.parse("3h 4h 5h Jh Ah"),
                Arrays.asList(Rank.Ace, Rank.Jack, Rank.Five, Rank.Four, Rank.Three));

        assertEquals(expected, actual);
    }
}
