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
 * A pot of chips.
 * A table may hold several pots (side pots). For each pot
 * there is a number of players that can potentially win this pot.
 */
public final class Pot implements Sealable {

    /** Number of chips in this pot. */
    private int amount;
    /** Seat numbers that participate in this pot. */
    private List<Integer> seats;
    /** True if this object is sealed. */
    private boolean sealed;

    /**
     * Creates an empty pot with no chips and no seats.
     */
    public Pot() {
        this.seats = new ArrayList<Integer>();
    }

    /**
     * Creates a un-sealed deep copy of a pot.
     * @param pot Pot to make a copy of.
     */
    public Pot(final Pot pot) {
        this.amount = pot.amount;
        this.seats = new ArrayList<Integer>(pot.seats);
    }

    /**
     * Gets the amount of chips in this pot.
     * @return Number of chips in this pot. Is non-negative.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets the amount of chips in this pot.
     * @param amount Number of chips in this pot. Must be non-negative.
     */
    public void setAmount(final int amount) {
        if (sealed) {
            throw new UnsupportedOperationException("pot is sealed");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("bet must be non-negative.");
        }
        this.amount = amount;
    }

    /**
     * Gets the seats that put money into this pot.
     * @return The seats that put money into this pot. Is never {@code null}.
     * List is mutable.
     */
    public List<Integer> getSeatNumbers() {
        return seats;
    }

    @Override
    public void seal() {
        if (!this.sealed) {
            this.sealed = true;
            this.seats = Collections.unmodifiableList(
                    new ArrayList<Integer>(seats));
        }
    }

    @Override
    public boolean isSealed() {
        return this.sealed;
    }
}
