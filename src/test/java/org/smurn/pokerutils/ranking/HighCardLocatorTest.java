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
 * Unit tests for {@link HighCardLocator}.
 */
public class HighCardLocatorTest {

    @Test
    public void fiveCards() {
        List<Card> cards = Card.parse("As 7d 3c 2h Kd");

        HighCardLocator locator = new HighCardLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.HighCard,
                cards,
                Arrays.asList(Rank.Ace, Rank.King, Rank.Seven, Rank.Three, Rank.Two));

        assertEquals(expected, actual);
    }

    @Test
    public void sevenCards() {
        List<Card> cards = Card.parse("As 7d 3c 2h Kd Td 9s");

        HighCardLocator locator = new HighCardLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.HighCard,
                Card.parse("As Kd Td 9s 7d"),
                Arrays.asList(Rank.Ace, Rank.King, Rank.Ten, Rank.Nine, Rank.Seven));

        assertEquals(expected, actual);
    }
    
    @Test
    public void ignoresPair(){
        List<Card> cards = Card.parse("As Ad 3c 2h Kd");

        HighCardLocator locator = new HighCardLocator();
        Hand actual = locator.findBestHand(cards);

        Hand expected = new Hand(HandCategory.HighCard,
                cards,
                Arrays.asList(Rank.Ace, Rank.Ace, Rank.King, Rank.Three, Rank.Two));

        assertEquals(expected, actual);
    }
}
