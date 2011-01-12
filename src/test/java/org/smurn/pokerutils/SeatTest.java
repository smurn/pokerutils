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

import java.util.Arrays;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Pot}.
 */
public class SeatTest extends SealableTest {

    /**
     * Tests that using the opy ctr produces an unsealed instance.
     */
    @Test
    public void unsealedAfterCopy() {
        Seat seat = new Seat();
        seat.seal();
        Seat copy = new Seat(seat);
        assertFalse(copy.isSealed());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesStake() {
        Seat seat = new Seat();
        seat.setStake(99);
        Seat copy = new Seat(seat);
        assertEquals("copy doesn't preserve the stake.", 99, copy.getStake());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesBet() {
        Seat seat = new Seat();
        seat.setBet(10);
        Seat copy = new Seat(seat);
        assertEquals("copy doesn't preserve the bet.", 10, copy.getBet());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesVisibility() {
        Seat seat = new Seat();
        seat.setCardsVisible(true);
        Seat copy = new Seat(seat);
        assertTrue("copy doesn't preserve visiblity.", copy.isCardsVisible());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesHoleCards() {
        Seat seat = new Seat();
        seat.getHoleCards().add(Card.SA);
        Seat copy = new Seat(seat);
        assertEquals("copy doesn't preserve the cards.", Arrays.asList(Card.SA),
                copy.getHoleCards());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesVisibleCards() {
        Seat seat = new Seat();
        seat.getVisibleCards().add(Card.SA);
        Seat copy = new Seat(seat);
        assertEquals("copy doesn't preserve the cards.", Arrays.asList(Card.SA),
                copy.getVisibleCards());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesPlayer() {
        Player mock = mock(Player.class);

        Seat seat = new Seat();
        seat.setPlayer(mock);
        Seat copy = new Seat(seat);
        assertSame("copy doesn't preserve the player.", mock,
                copy.getPlayer());
    }

    @Override
    protected Sealable createUnsealedInstance() {
        return new Seat();
    }
}
