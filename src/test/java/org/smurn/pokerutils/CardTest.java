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

import java.util.List;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Unit tests for {@link Card}.
 */
public class CardTest {

    /**
     * Tests if there are 52 cards in a poker set.
     */
    @Test
    public void numberOfCards() {
        assertEquals("Wrong number of cards.", 52, Card.values().length);
    }

    /**
     * Tests that no two cards have the same suit and rank.
     */
    @Test
    public void allDifferent() {
        Card[] cards = Card.values();
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                assertFalse("Found equal cards (orginal " + i + " and " + j + ")",
                        cards[i].getSuit() == cards[j].getSuit()
                        && cards[i].getRank() == cards[j].getRank());
            }
        }
    }

    /**
     * Tests with a simple card.
     */
    @Test
    public void getCard(){
        Card actual = Card.getCard(Suit.Spades, Rank.Ace);
        assertThat(actual, is(Card.SA));
    }
    
    /**
     * Parsing of empty string.
     */
    @Test
    public void parseNoCards() {
        List<Card> cards = Card.parse("");
        assertThat(cards.size(), is(0));
    }

    /**
     * Parsing of a single card.
     */
    @Test
    public void parseSingleCard() {
        List<Card> cards = Card.parse("7d");
        assertThat(cards, equalTo(Arrays.asList(Card.D7)));
    }

    /**
     * Parsing of multiple cards.
     */
    @Test
    public void parseMultipleCards() {
        List<Card> cards = Card.parse("As 2c");
        assertThat(cards, equalTo(Arrays.asList(Card.SA, Card.C2)));
    }

    /**
     * Parsing wrong format throws exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void parseFormatException() {
        Card.parse("As,2c");
    }
}
