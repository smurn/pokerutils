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

/**
 * Rank of a poker card.
 * There are 13 different ranks, 'Two' to 'Ace' (the ace is considered
 * the highest card here, even if with some rules it may also represent the
 * lowest).
 */
public enum Rank {

    /** Rank of two (Rank value 0). */
    Two,
    /** Rank of three (Rank value 1). */
    Three,
    /** Rank of four (Rank value 2). */
    Four,
    /** Rank of five (Rank value 3). */
    Five,
    /** Rank of six (Rank value 4). */
    Six,
    /** Rank of seven (Rank value 5). */
    Seven,
    /** Rank of eight (Rank value 6). */
    Eight,
    /** Rank of nine (Rank value 7). */
    Nine,
    /** Rank of ten (Rank value 8). */
    Ten,
    /** Rank of jack (Rank value 9). */
    Jack,
    /** Rank of queen (Rank value 10). */
    Queen,
    /** Rank of king (Rank value 11). */
    King,
    /** Rank of ace (Rank value 12). */
    Ace;

    /**
     * Gets the rank value of this card.
     * @return Rank value of this card, staring with 0. The value of 'Two' is 0,
     * 'Ten' is 8 and Ace is 12.
     */
    public int getRankValue() {
        return this.ordinal();
    }

    /**
     * Returns the rank indicated by the given charater.
     * @param c The character describing the rank. Must be between '2' and '9'
     *   or one of 'T', 'J', 'Q' , 'K' and 'A'.
     * @return Rank indicated by the given character.
     * @throws IllegalArgumentException If the character is not in the valid
     * range.
     */
    public static Rank parse(final char c) {
        if (c >= '2' && c <= '9') {
            int ordinal = c - '2';
            return Rank.values()[ordinal];
        } else {
            switch (c) {
                case 't':
                case 'T':
                    return Rank.Ten;
                case 'j':
                case 'J':
                    return Rank.Jack;
                case 'q':
                case 'Q':
                    return Rank.Queen;
                case 'k':
                case 'K':
                    return Rank.King;
                case 'a':
                case 'A':
                    return Rank.Ace;
                default:
                    throw new IllegalArgumentException("unexpected character '"
                            + c + "'.");
            }
        }
    }
}
