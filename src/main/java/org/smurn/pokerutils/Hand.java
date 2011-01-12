/*
 * Copyright 2011 Stefan C. Mueller.
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
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.NullArgumentException;

/**
 * Repesents a set of five poker cards.
 * Instances of this class are immutable.
 */
public final class Hand {

    /** Category of this hand. */
    private final HandCategory category;
    /** Cards this hand consists of (always 5). */
    private final Set<Card> cards;
    /** Ranks to compare hands of the same category. */
    private final List<Rank> ranks;
    /** Number of cards a hand has. */
    private static final int CARDS_IN_HAND = 5;

    /**
     * Creates a hand.
     * Note that this constructor does not guarantee that the
     * hand is valid or even possible.
     * @param category Category of this hand. Must not be null.
     * @param cards Cards that form this hand. Must not be null, size must be 5.
     * @param ranks Ranks for the loxiographic ordering
     * (see {@link #getRanks()}. Must not be null.
     */
    public Hand(final HandCategory category, final Collection<Card> cards,
            final List<Rank> ranks) {
        if (category == null) {
            throw new NullArgumentException("category");
        }
        if (cards == null) {
            throw new NullArgumentException("cards");
        }
        if (ranks == null) {
            throw new NullArgumentException("ranks");
        }
        if (cards.size() != CARDS_IN_HAND) {
            throw new IllegalArgumentException("cards must be of size 5, "
                    + "but is " + cards.size() + ".");
        }

        this.category = category;
        this.cards = Collections.unmodifiableSet(EnumSet.copyOf(cards));
        this.ranks = Collections.unmodifiableList(new ArrayList<Rank>(ranks));
    }

    /**
     * Gets the cards that from this hand.
     * @return Immutable set of the 5 cards that form this hand. Never null.
     */
    public Set<Card> getCards() {
        return cards;
    }

    /**
     * Gets the category of this hand.
     * @return Category of this hand. Never null.
     */
    public HandCategory getCategory() {
        return category;
    }

    /**
     * Gets the ranks relevant for comparing hands of the same category.
     * @return Immutable list of ranks sorted by descending influence. When
     * comparing two hands with equal hand category, the loxiographic order of
     * this list defines the outcome. Never null.
     */
    public List<Rank> getRanks() {
        return ranks;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hand other = (Hand) obj;
        if (this.category != other.category) {
            return false;
        }
        if (this.cards != other.cards
                && ( this.cards == null || !this.cards.equals(other.cards) )) {
            return false;
        }
        if (this.ranks != other.ranks
                && ( this.ranks == null || !this.ranks.equals(other.ranks) )) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + ( this.category != null
                ? this.category.hashCode() : 0 );
        hash = 89 * hash + ( this.cards != null ? this.cards.hashCode() : 0 );
        hash = 89 * hash + ( this.ranks != null ? this.ranks.hashCode() : 0 );
        return hash;
    }

    @Override
    public String toString() {
        return "Hand{" + "category=" + category + ", cards=" + cards
                + ", ranks=" + ranks + '}';
    }
}
