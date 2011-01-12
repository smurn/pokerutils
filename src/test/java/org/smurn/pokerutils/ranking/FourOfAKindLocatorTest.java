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
 * Unit tests for {@link FourOfAKindLocator}.
 */
public class FourOfAKindLocatorTest {

    @Test
    public void noFourOfAKind() {
        List<Card> cards = Card.parse("As 7d 7c 7h Kd");

        FourOfAKindLocator locator = new FourOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        assertNull(actual);
    }

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 7c 7h 7s");

        FourOfAKindLocator locator = new FourOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FourOfAKind,
                cards,
                Arrays.asList(Rank.Seven, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As Ts Tc Th Kd Td 9s");

        FourOfAKindLocator locator = new FourOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FourOfAKind,
                Card.parse("As Th Td Ts Tc"),
                Arrays.asList(Rank.Ten, Rank.Ace));

        assertEquals(expected, actual);
    }

    @Test
    public void multipleCandiates() {
        List<Card> cards = Card.parse("As Ts Tc Th Kd Td 9s Ad Ah Ac");

        FourOfAKindLocator locator = new FourOfAKindLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.FourOfAKind,
                Card.parse("As Ad Ah Ac Kd"),
                Arrays.asList(Rank.Ace, Rank.King));

        assertEquals(expected, actual);
    }
}
