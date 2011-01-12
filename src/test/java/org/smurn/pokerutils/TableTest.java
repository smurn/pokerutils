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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Table}.
 */
public class TableTest {

    /**
     * Tests that using the opy ctr produces an unsealed instance.
     */
    @Test
    public void unsealedAfterCopy() {
        Table table = new Table(5);
        table.seal();
        Table copy = new Table(table);
        assertFalse(copy.isSealed());
    }

    /**
     * Tests that the seats of a table copy are unsealed.
     */
    @Test
    public void seatUnsealedAfterCopy() {
        Table table = new Table(5);
        table.seal();
        Table copy = new Table(table);
        assertFalse("Seat is sealed.", copy.getSeats().get(0).isSealed());
    }

    /**
     * Tests that the pots of a table copy are unsealed.
     */
    @Test
    public void potUnsealedAfterCopy() {
        Table table = new Table(5);
        table.getPots().add(new Pot());
        table.seal();
        Table copy = new Table(table);
        assertFalse("Pot is sealed.", copy.getPots().get(0).isSealed());
    }

    /**
     * Tests that the seats are also sealed when sealing the table.
     */
    @Test
    public void deepSealingSeats() {
        Table table = new Table(5);
        table.seal();
        assertTrue("Seat not sealed.", table.getSeats().get(0).isSealed());
    }

    /**
     * Tests that the pots are also sealed when sealing the table.
     */
    @Test
    public void deepSealingPots() {
        Table table = new Table(5);
        table.getPots().add(new Pot());
        table.seal();
        assertTrue("Pot not sealed.", table.getPots().get(0).isSealed());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesSeats() {
        Table table = new Table(5);
        table.getSeats().get(2).setStake(170);
        table.seal();
        Table copy = new Table(table);
        assertEquals("seats not preserved", 170,
                copy.getSeats().get(2).getStake());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesPots() {
        Table table = new Table(5);
        table.getPots().add(new Pot());
        table.getPots().get(0).setAmount(44);
        table.seal();
        Table copy = new Table(table);
        assertEquals("pots not preserved", 44,
                copy.getPots().get(0).getAmount());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesActive() {
        Table table = new Table(5);
        table.setActingSeatNr(1);
        table.seal();
        Table copy = new Table(table);
        assertEquals("active seat not preserved", 1,
                copy.getActingSeatNr());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesDealer() {
        Table table = new Table(5);
        table.setDealerSeatNr(4);
        table.seal();
        Table copy = new Table(table);
        assertEquals("dealer seat not preserved", 4,
                copy.getDealerSeatNr());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesSmallBlind() {
        Table table = new Table(5);
        table.setSmallBlindSeatNr(2);
        table.seal();
        Table copy = new Table(table);
        assertEquals("sb seat not preserved", 2,
                copy.getSmallBlindSeatNr());
    }

    /**
     * Tests that the copy constructor preserves the data.
     */
    @Test
    public void copyPreservesBigBlind() {
        Table table = new Table(5);
        table.setBigBlindSeatNr(2);
        table.seal();
        Table copy = new Table(table);
        assertEquals("bb seat not preserved", 2,
                copy.getBigBlindSeatNr());
    }
    
    /**
     * Tests the {@link Table#getSeat(int)} method.
     */
    @Test
    public void getSeat(){
        Table table = new Table(5);
        assertSame(table.getSeats().get(3), table.getSeat(3));
    }
}
