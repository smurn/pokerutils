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
 * One of the four suits a poker card can have.
 */
public enum Suit {

    /** Spades. */
    Spades,
    /** Heards. */
    Hearts,
    /** Diamonds. */
    Diamonds,
    /** Clubs. */
    Clubs;

    /**
     * Returns the suit indicated by the given charater.
     * @param c The character describing the suit. Must be one of 'S', 'H', 'D'
     * and 'C' or their lower-case equivalent.
     * @return Suit indicated by the given character.
     * @throws IllegalArgumentException If the character is not in the valid
     * range.
     */
    public static Suit parse(final char c) {
        switch (c) {
            case 's':
            case 'S':
                return Suit.Spades;
            case 'h':
            case 'H':
                return Suit.Hearts;
            case 'd':
            case 'D':
                return Suit.Diamonds;
            case 'c':
            case 'C':
                return Suit.Clubs;
            default:
                throw new IllegalArgumentException("unexpected character '"
                        + c + "'");
        }
    }
}
