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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.NullArgumentException;

/**
 * Represents a regular poker card.
 */
public enum Card {

    /** Deuce of spades. */
    S2(Suit.Spades, Rank.Two),
    /** Three of spaces. */
    S3(Suit.Spades, Rank.Three),
    /** Four of spaces. */
    S4(Suit.Spades, Rank.Four),
    /** Five of spaces. */
    S5(Suit.Spades, Rank.Five),
    /** Six of spaces. */
    S6(Suit.Spades, Rank.Six),
    /** Seven of spaces. */
    S7(Suit.Spades, Rank.Seven),
    /** Eight of spaces. */
    S8(Suit.Spades, Rank.Eight),
    /** Nine of spaces. */
    S9(Suit.Spades, Rank.Nine),
    /** Ten of spaces. */
    ST(Suit.Spades, Rank.Ten),
    /** Jack of spaces. */
    SJ(Suit.Spades, Rank.Jack),
    /** Queen of spaces. */
    SQ(Suit.Spades, Rank.Queen),
    /** King of spaces. */
    SK(Suit.Spades, Rank.King),
    /** Ace of spaces. */
    SA(Suit.Spades, Rank.Ace),
    /** Deuce of heards. */
    H2(Suit.Hearts, Rank.Two),
    /** Three of heards. */
    H3(Suit.Hearts, Rank.Three),
    /** Four of heards. */
    H4(Suit.Hearts, Rank.Four),
    /** Five of heards. */
    H5(Suit.Hearts, Rank.Five),
    /** Six of heards. */
    H6(Suit.Hearts, Rank.Six),
    /** Seven of heards. */
    H7(Suit.Hearts, Rank.Seven),
    /** Eight of heards. */
    H8(Suit.Hearts, Rank.Eight),
    /** Nine of heards. */
    H9(Suit.Hearts, Rank.Nine),
    /** Ten of heards. */
    HT(Suit.Hearts, Rank.Ten),
    /** Jack of heards. */
    HJ(Suit.Hearts, Rank.Jack),
    /** Queen of heards. */
    HQ(Suit.Hearts, Rank.Queen),
    /** King of heards. */
    HK(Suit.Hearts, Rank.King),
    /** Ace of heards. */
    HA(Suit.Hearts, Rank.Ace),
    /** Deuce of diamonds. */
    D2(Suit.Diamonds, Rank.Two),
    /** Three of diamonds. */
    D3(Suit.Diamonds, Rank.Three),
    /** Four of diamonds. */
    D4(Suit.Diamonds, Rank.Four),
    /** Five of diamonds. */
    D5(Suit.Diamonds, Rank.Five),
    /** Six of diamonds. */
    D6(Suit.Diamonds, Rank.Six),
    /** Seven of diamonds. */
    D7(Suit.Diamonds, Rank.Seven),
    /** Eight of diamonds. */
    D8(Suit.Diamonds, Rank.Eight),
    /** Nine of diamonds. */
    D9(Suit.Diamonds, Rank.Nine),
    /** Ten of diamonds. */
    DT(Suit.Diamonds, Rank.Ten),
    /** Jack of diamonds. */
    DJ(Suit.Diamonds, Rank.Jack),
    /** Queen of diamonds. */
    DQ(Suit.Diamonds, Rank.Queen),
    /** King of diamonds. */
    DK(Suit.Diamonds, Rank.King),
    /** Ace of diamonds. */
    DA(Suit.Diamonds, Rank.Ace),
    /** Deuce of clubs. */
    C2(Suit.Clubs, Rank.Two),
    /** Three of clubs. */
    C3(Suit.Clubs, Rank.Three),
    /** Four of clubs. */
    C4(Suit.Clubs, Rank.Four),
    /** Five of clubs. */
    C5(Suit.Clubs, Rank.Five),
    /** Six of clubs. */
    C6(Suit.Clubs, Rank.Six),
    /** Seven of clubs. */
    C7(Suit.Clubs, Rank.Seven),
    /** Eight of clubs. */
    C8(Suit.Clubs, Rank.Eight),
    /** Nine of clubs. */
    C9(Suit.Clubs, Rank.Nine),
    /** Ten of clubs. */
    CT(Suit.Clubs, Rank.Ten),
    /** Jack of clubs. */
    CJ(Suit.Clubs, Rank.Jack),
    /** Queen of clubs. */
    CQ(Suit.Clubs, Rank.Queen),
    /** King of clubs. */
    CK(Suit.Clubs, Rank.King),
    /** Ace of clubs. */
    CA(Suit.Clubs, Rank.Ace);
    /**
     * Suit of this card.
     */
    private final Suit suit;
    /**
     * Rank of this card.
     */
    private final Rank rank;

    /**
     * Creates an instance.
     * @param suit Suit of the card.
     * @param rank Rank of the card.
     */
    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the suit of this card.
     * @return The suit of this card.
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Gets the rank of this card.
     * @return The rank of this card.
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Returns the card with the given suit and rank.
     * @param suit Suit of the requested card. Must not be null.
     * @param rank Rank of the requested card. Must not be null.
     * @return The requested card.
     */
    public static Card getCard(final Suit suit, final Rank rank) {
        for (Card card : Card.values()) {
            if (card.suit == suit && card.rank == rank) {
                return card;
            }
        }
        throw new AssertionError("Card enum misses the card with suit "
                + suit + " and rank " + rank + ".");
    }

    /**
     * Parses a string into a list of cards.
     * @param cardsString String describing cards in the format "7c Ad 9h".
     * Must not be null.
     * @return List of cards, same order as in the string.
     * @throws IllegalArgumentException If the format is wrong.
     */
    public static List<Card> parse(final String cardsString)
            throws IllegalArgumentException {
        if (cardsString == null) {
            throw new NullArgumentException("cardsString");
        }

        ArrayList<Card> cards = new ArrayList<Card>();
        if (cardsString.isEmpty()) {
            return cards;
        }

        for (String cardStr : cardsString.split(" ")) {

            if (cardStr.length() != 2) {
                throw new IllegalArgumentException("Each card must be "
                        + "described by two characters. Found '"
                        + cardStr + "'.");
            }
            Rank rank = Rank.parse(cardStr.charAt(0));
            Suit suit = Suit.parse(cardStr.charAt(1));
            Card card = Card.getCard(suit, rank);
            cards.add(card);
        }

        return cards;
    }
}
