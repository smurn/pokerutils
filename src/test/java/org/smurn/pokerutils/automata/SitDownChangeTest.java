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
import org.smurn.pokerutils.Player;
import org.smurn.pokerutils.Table;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link SitDownChange}.
 */
public class SitDownChangeTest {

    /**
     * Test that the player is set on the right seat.
     */
    @Test
    public void setsPlayer() {
        Table before = new Table(10);
        before.seal();
        
        Player player = mock(Player.class);
        SitDownChange change = new SitDownChange(player, 4, 123);
        
        Table after = change.apply(before);
        
        assertSame("player not set", player, after.getSeat(4).getPlayer());
    }

    /**
     * Test that the stake is set on the right seat.
     */
    @Test
    public void setsStake() {
        Table before = new Table(10);
        before.seal();
        
        Player player = mock(Player.class);
        SitDownChange change = new SitDownChange(player, 4, 123);
        
        Table after = change.apply(before);
        
        assertSame("stake not set", 123, after.getSeat(4).getStake());
    }

    /**
     * Tests that one cannot sit down at an occupied seat.
     */
    @Test(expected = IncompatibleTableException.class)
    public void doesntSitOnLap() {
        Table before = new Table(10);
        Player playerA = mock(Player.class);
        before.getSeat(4).setPlayer(playerA);
        before.seal();
        
        Player playerB = mock(Player.class);
        SitDownChange change = new SitDownChange(playerB, 4, 123);
        
        change.apply(before);
    }

    /**
     * Tests that one cannot sit down at an occupied seat.
     */
    @Test(expected = IncompatibleTableException.class)
    public void detectsLeftoverChips() {
        Table before = new Table(10);
        before.getSeat(4).setStake(1);
        before.seal();
        
        Player playerB = mock(Player.class);
        SitDownChange change = new SitDownChange(playerB, 4, 123);
        change.apply(before);
    }
    
    /**
     * Tests that one cannot sit down at a seat that does not exist.
     */
    @Test(expected = IncompatibleTableException.class)
    public void unexistingSeat(){
        Table before = new Table(7);
        before.seal();
        
        Player player = mock(Player.class);
        SitDownChange change = new SitDownChange(player, 7, 123);
        
        change.apply(before);
    }
    
    /**
     * Tests that only sealed tables are accepted. 
     */
    @Test(expected=IllegalArgumentException.class)
    public void requiresSealed(){
        Table unsealed = new Table(10);
        
        Player player = mock(Player.class);
        SitDownChange change = new SitDownChange(player, 7, 123);
        
        change.apply(unsealed);
    }
    
    /**
     * The produced table must be sealed.
     */
    @Test
    public void producesSealed(){
        Table before = new Table(10);
        before.seal();
        
        Player player = mock(Player.class);
        SitDownChange change = new SitDownChange(player, 4, 123);
        
        Table after = change.apply(before);
        
        assertTrue("Returned table is not sealed.", after.isSealed());
    }
}
