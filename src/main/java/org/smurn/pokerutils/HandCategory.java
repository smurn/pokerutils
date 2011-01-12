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
 * Poker hand category.
 * The categories are sorted such that enum values with a higher ordinal
 * number win. The royal flush is not explicitly included since it
 * is only a special case of a straight flush.
 */
public enum HandCategory {

    /** Highest card. */
    HighCard,
    /** One pair. */
    Pair,
    /** Two pair. */
    TwoPair,
    /** Three of a kind. */
    TreeOfAKind,
    /** Straight. */
    Straight,
    /** Flush. */
    Flush,
    /** Full house. */
    FullHouse,
    /** Four of a kind. */
    FourOfAKind,
    /** Staight flush. */
    StraightFlush
}
