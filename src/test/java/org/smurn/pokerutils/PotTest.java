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
 * Unit tests for {@link Pot}.
 */
public class PotTest extends SealableTest {

    /**
     * Tests that using the opy ctr produces an unsealed instance.
     */
    @Test
    public void unsealedAfterCopy() {
        Pot pot = new Pot();
        pot.seal();
        Pot copy = new Pot(pot);
        assertFalse(copy.isSealed());
    }

    /**
     * Copy preserves data.
     */
    @Test
    public void copyPreservesAmount() {
        Pot p1 = new Pot();
        p1.setAmount(1000);
        Pot p2 = new Pot(p1);
        assertEquals("copy doesn't preserve the amount.", 1000, p2.getAmount());
    }
    


    /**
     * Copy preserves data.
     */
    @Test
    public void copyPerservesSeats() {
        Pot p1 = new Pot();
        p1.getSeatNumbers().add(10);
        p1.getSeatNumbers().add(20);
        Pot p2 = new Pot(p1);
        assertEquals("copy doesn't preserve the amount.", p1.getSeatNumbers(), p2.getSeatNumbers());
    }

    /**
     * Tests that the seat collection becomes immutable.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void immutableSeats() {
        Pot p1 = new Pot();
        p1.seal();
        p1.getSeatNumbers().add(10);
    }

    @Override
    protected Sealable createUnsealedInstance() {
        return new Pot();
    }
}
