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
import java.util.Collections;
import java.util.List;

/**
 * State of a specific seat at a poker table.
 * Most state is not player but seat specific. This discrimination is
 * important since players may leave while they still have a bet at the table.
 */
public final class Seat implements Sealable {

    /** Player sitting at this seat. */
    private Player player;
    /** Chips owned by the player. */
    private int stake;
    /** Chips this seat has bet in the current betting round. */
    private int bet;
    /** Private cards of this player. */
    private List<Card> holeCards;
    /** Publically visible cards of this player. */
    private List<Card> visibleCards;
    /** If the private cards are visible. */
    private boolean cardsVisible;
    /** True if this object is immutable. */
    private boolean sealed;

    /**
     * Creates an empty seat, with no stake, bet or cards.
     */
    public Seat() {
        holeCards = new ArrayList<Card>();
        visibleCards = new ArrayList<Card>();
    }

    /**
     * Creates an unsealed deep-copy of a seat.
     * The player reference is not deep-copied.
     * @param seat The seat to copy. Must not be {@code null}.
     */
    public Seat(final Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("seat must not be null.");
        }
        this.player = seat.player;
        this.stake = seat.stake;
        this.bet = seat.bet;
        this.cardsVisible = seat.cardsVisible;
        this.holeCards = new ArrayList<Card>(seat.holeCards);
        this.visibleCards = new ArrayList<Card>(seat.visibleCards);
    }

    /**
     * Gets the amount of chips that were removed from the stake and
     * placed in front of this seat, but where not yet collected into a pot.
     * @return Number of chips in front of this seat. Non-negative.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the amount of chips that were removed from the stake and
     * placed in front of this seat, but where not yet collected into a pot.
     * @param bet Number of chips in front of this seat. Non-negative.
     */
    public void setBet(final int bet) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (bet < 0) {
            throw new IllegalArgumentException("bet must be non-negative.");
        }
        this.bet = bet;
    }

    /**
     * Gets the secret poket cards of that seat.
     * @return Poket cards of this seat. If the seat has no cards, the
     * list will be empty. Is never {@code null}. List is mutable, until
     * the seat is sealed.
     */
    public List<Card> getHoleCards() {
        return this.holeCards;
    }

    /**
     * Gets the visible cards of that seat.
     * @return Visible cards of this seat. If the seat has no cards, the
     * list will be empty. Is never {@code null}. List is mutable, until
     * the seat is sealed.
     */
    public List<Card> getVisibleCards() {
        return this.visibleCards;
    }

    /**
     * Gets the player sitting at this seat.
     * @return Player sitting at this seat. {@code null} if the seat is empty.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player sitting at this seat.
     * @param player Player sitting at this seat. {@code null} if the seat is
     * empty.
     */
    public void setPlayer(final Player player) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        this.player = player;
    }

    /**
     * Chips at this seat that are not participating in the current hand.
     * @return Number of chips at this seat. Is non-negative.
     */
    public int getStake() {
        return stake;
    }

    /**
     * Chips at this seat that are not participating in the current hand.
     * @param stake Number of chips at this seat. Must be non-negative.
     */
    public void setStake(final int stake) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (bet < 0) {
            throw new IllegalArgumentException("stake must be non-negative.");
        }
        this.stake = stake;
    }

    /**
     * Gets if the player's private cards are visible.
     * @return True if the cards are visible.
     */
    public boolean isCardsVisible() {
        return cardsVisible;
    }

    /**
     * Sets if the player's private cards are visible.
     * @param cardsVisible True if the cards are visible.
     */
    public void setCardsVisible(final boolean cardsVisible) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        this.cardsVisible = cardsVisible;
    }

    @Override
    public void seal() {
        this.sealed = true;
        this.holeCards = Collections.unmodifiableList(
                new ArrayList<Card>(this.holeCards));
        this.visibleCards = Collections.unmodifiableList(
                new ArrayList<Card>(this.visibleCards));
    }

    @Override
    public boolean isSealed() {
        return this.sealed;
    }
}
