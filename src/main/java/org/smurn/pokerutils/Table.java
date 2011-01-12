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
 * Poker table state.
 * Stores all information available about a poker table.
 */
public final class Table implements Sealable {

    /** All seats of this table. */
    private final List<Seat> seats;
    /** All pots on this table. */
    private List<Pot> pots;
    /** Seat that has the dealer button. */
    private int dealerSeatNr;
    /** Seat that has the small blind button. */
    private int smallBlindSeatNr;
    /** Seat that has the big blind button. */
    private int bigBlindSeatNr;
    /** Seat that has to act next. */
    private int actingSeatNr;
    /** True if this object is immutable. */
    private boolean sealed;

    /**
     * Creates an instance.
     * @param numberOfSeats Number of seats the new table will have. Must be
     * larger than 0.
     */
    public Table(final int numberOfSeats) {
        List<Seat> seatsTmp = new ArrayList<Seat>(numberOfSeats);
        for (int i = 0; i < numberOfSeats; i++) {
            seatsTmp.add(new Seat());
        }
        this.seats = Collections.unmodifiableList(seatsTmp);
        this.pots = new ArrayList<Pot>();
    }

    /**
     * Creates a un-sealed deep copy of a table.
     * The created table will have the same information stored, but won't be
     * locked.
     * @param table Table to copy. Must not be {@code null}.
     */
    public Table(final Table table) {
        if (table == null) {
            throw new IllegalArgumentException("table must not be null.");
        }

        // copy the seats
        List<Seat> seatsCopy = new ArrayList<Seat>(table.seats.size());
        for (Seat seat : table.getSeats()) {
            seatsCopy.add(new Seat(seat));
        }
        this.seats = Collections.unmodifiableList(seatsCopy);

        // copy the pots
        this.pots = new ArrayList<Pot>();
        for (Pot pot : table.pots) {
            this.pots.add(new Pot(pot));
        }

        this.actingSeatNr = table.actingSeatNr;
        this.bigBlindSeatNr = table.bigBlindSeatNr;
        this.smallBlindSeatNr = table.smallBlindSeatNr;
        this.dealerSeatNr = table.dealerSeatNr;
    }

    /**
     * Gets the number of seats at this table.
     * @return Number of seats at this table. Is non-negative.
     */
    public int getSeatCount() {
        return this.seats.size();
    }

    /**
     * Gets the seat number who's turn it is to act.
     * If it is no seat's time to act {@code -1} is returned. This is
     * typically the case if the next action must be performed by the dealer.
     * @return The number of the seat who is to act next or {@code -1} if none.
     */
    public int getActingSeatNr() {
        return actingSeatNr;
    }

    /**
     * Sets the seat number who's turn it is to act.
     * If it is no seat's time to act, pass {@code -1}. This is typically
     * the case if the next action must be performed by the dealer.
     * @param actingSeatNr Number of the seat who has to act next.
     */
    public void setActingSeatNr(final int actingSeatNr) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (actingSeatNr < 0 || actingSeatNr >= seats.size()) {
            throw new IllegalArgumentException("No such seat number at this "
                    + "table.");
        }
        this.actingSeatNr = actingSeatNr;
    }

    /**
     * Gets the seat number on which the dealer button is positioned.
     * @return The seat number on which the button is positioned (valid index
     * into {@link #getSeats()}), or -1 if the button is not on the table.
     */
    public int getDealerSeatNr() {
        return dealerSeatNr;
    }

    /**
     * Sets the seat number on which the dealer button is positioned.
     * @param dealerSeatNr The seat number where to place the button (valid
     * index into {@link #getSeats()}) or {@code -1} to remove the button is not
     * on the table.
     */
    public void setDealerSeatNr(final int dealerSeatNr) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (dealerSeatNr != -1) {
            if (dealerSeatNr < 0 || dealerSeatNr >= seats.size()) {
                throw new IllegalArgumentException("No such seat number at "
                        + "this table.");
            }
        }
        this.dealerSeatNr = dealerSeatNr;
    }

    /**
     * Gets the seat number on which the big-blind button is positioned.
     * @return The seat number on which the button is positioned (valid index
     * into {@link #getSeats()}), or -1 if the button is not on the table.
     */
    public int getBigBlindSeatNr() {
        return bigBlindSeatNr;
    }

    /**
     * Sets the seat number on which the big-blind button is positioned.
     * @param bigBlindSeatNr The seat number where to place the button (valid
     * index into {@link #getSeats()}) or {@code -1} to remove the button is not
     * on the table.
     */
    public void setBigBlindSeatNr(final int bigBlindSeatNr) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (bigBlindSeatNr < 0 || bigBlindSeatNr >= seats.size()) {
            throw new IllegalArgumentException("No such seat number at this "
                    + "table.");
        }
        this.bigBlindSeatNr = bigBlindSeatNr;
    }

    /**
     * Gets the seat number on which the small-blind button is positioned.
     * @return The seat number on which the button is positioned (valid index
     * into {@link #getSeats()}), or -1 if the button is not on the table.
     */
    public int getSmallBlindSeatNr() {
        return smallBlindSeatNr;
    }

    /**
     * Sets the seat number on which the small-blind button is positioned.
     * @param smallBlindSeatNr The seat number where to place the button (valid
     * index into {@link #getSeats()}) or {@code -1} to remove the button is not
     * on the table.
     */
    public void setSmallBlindSeatNr(final int smallBlindSeatNr) {
        if (sealed) {
            throw new UnsupportedOperationException("table is sealed");
        }
        if (smallBlindSeatNr < 0 || smallBlindSeatNr >= seats.size()) {
            throw new IllegalArgumentException("No such seat number at this "
                    + "table.");
        }
        this.smallBlindSeatNr = smallBlindSeatNr;
    }

    /**
     * Gets the list of pots on the table.
     * A table may have zero or more pots. Multiple pots (side-pots) are
     * ordered by decreasing number of participating players. This implies
     * that bets are always added to the last pot (potentially adding
     * a new one).
     * @return List of pots. Never {@code null}, but potentally empty. List
     * is mutable.
     */
    public List<Pot> getPots() {
        return pots;
    }

    /**
     * Gets the list of all seats on the table.
     * This includes seats which are not currently occupied.
     * @return List of all seats at the table. Never {@code null}, but
     * potentally empty.
     * List is immutable.
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * Gets the seat with a given seat number.
     * This is equivalent to {@code getSeats().get(seatNr)}.
     * @param seatNr Number of the seat to receive.
     * @return The seat at this position.
     */
    public Seat getSeat(final int seatNr) {
        if (seatNr < 0 || seatNr >= seats.size()) {
            throw new IllegalArgumentException("No seat with this number.");
        }
        return seats.get(seatNr);
    }

    @Override
    public void seal() {
        if (!this.sealed) {
            this.sealed = true;
            for (Seat seat : this.seats) {
                seat.seal();
            }
            for (Pot pot : this.pots) {
                pot.seal();
            }
            this.pots = Collections.unmodifiableList(new ArrayList<Pot>(pots));
        }
    }

    @Override
    public boolean isSealed() {
        return this.sealed;
    }
}
