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
package org.smurn.pokerutils.automata;

import org.junit.Test;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Player;
import org.smurn.pokerutils.Table;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link GetUpChange}.
 */
public class GetUpChangeTest {

    /**
     * Test that the player is cleared.
     */
    @Test
    public void setsPlayer() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 44);

        Table after = change.apply(before);

        assertNull("Player not set to null.", after.getSeat(2).getPlayer());
    }

    /**
     * Tests that the stake is emptied.
     */
    @Test
    public void setsStake() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 44);

        Table after = change.apply(before);

        assertEquals("Stake not set to 0.", 0, after.getSeat(2).getStake());
    }

    /**
     * Cannot get up from an empty seat.
     */
    @Test(expected = IncompatibleTableException.class)
    public void emptySeat() {
        Table before = new Table(10);
        before.seal();

        Player player = mock(Player.class);
        GetUpChange change = new GetUpChange(player, 2, 44);

        change.apply(before);
    }

    /**
     * Wrong player sitting at the seat.
     */
    @Test(expected = IncompatibleTableException.class)
    public void differentPlayer() {
        Table before = new Table(10);
        Player playerA = mock(Player.class);
        before.getSeat(2).setPlayer(playerA);
        before.getSeat(2).setStake(44);
        before.seal();

        Player playerB = mock(Player.class);
        GetUpChange change = new GetUpChange(playerB, 2, 44);

        change.apply(before);
    }

    /**
     * The stake is different on the table and in the change.
     */
    @Test(expected = IncompatibleTableException.class)
    public void stakeMismatch() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 3333);

        change.apply(before);
    }

    /**
     * Tests that one cannot sit down at a seat that does not exist.
     */
    @Test(expected = IncompatibleTableException.class)
    public void unexistingSeat() {
        Table before = new Table(7);
        Player player = mock(Player.class);
        before.seal();

        GetUpChange change = new GetUpChange(player, 7, 123);

        change.apply(before);
    }

    /**
     * Test that the seat cannot have hole cards.
     */
    @Test(expected = IncompatibleTableException.class)
    public void holeCards() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.getSeat(2).getHoleCards().add(Card.S2);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 44);

        change.apply(before);
    }

    /**
     * Test that the seat cannot have visible cards.
     */
    @Test(expected = IncompatibleTableException.class)
    public void visibleCards() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.getSeat(2).getVisibleCards().add(Card.S2);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 44);

        change.apply(before);
    }

    /**
     * Tests that only sealed tables are accepted. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void requiresSealed() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);

        GetUpChange change = new GetUpChange(player, 2, 44);

        change.apply(before);
    }

    /**
     * The produced table must be sealed.
     */
    @Test
    public void producesSealed() {
        Table before = new Table(10);
        Player player = mock(Player.class);
        before.getSeat(2).setPlayer(player);
        before.getSeat(2).setStake(44);
        before.seal();

        GetUpChange change = new GetUpChange(player, 2, 44);

        Table after = change.apply(before);

        assertTrue("Returned table is not sealed.", after.isSealed());
    }
}
