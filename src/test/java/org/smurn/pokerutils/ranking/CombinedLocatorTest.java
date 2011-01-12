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

package org.smurn.pokerutils.ranking;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.smurn.pokerutils.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link CombinedLocator}.
 */
public class CombinedLocatorTest {

    @Test
    public void emptySequence(){
        CombinedLocator locator = new CombinedLocator(new ArrayList<HandLocator>());
        Hand hand = locator.findBestHand(Card.parse("Ah Ad Kh Kd 8c"));
        assertNull(hand);
    }
    
    @Test
    public void singleLocator(){
        List<Card> cards = Card.parse("Ah Ad Kh Kd 8c");
        Hand expected = new Hand(HandCategory.Straight, cards, Arrays.asList(Rank.Ace));
        
        HandLocator mock = mock(HandLocator.class);
        when(mock.findBestHand(eq(cards))).thenReturn(expected);
        
        CombinedLocator locator = new CombinedLocator(Arrays.asList(mock));
        Hand actual = locator.findBestHand(cards);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void twoLocatorsFirstWins(){
        List<Card> cards = Card.parse("Ah Ad Kh Kd 8c");
        Hand handFirst = new Hand(HandCategory.StraightFlush, cards, Arrays.asList(Rank.Ace));
        Hand handSecond = new Hand(HandCategory.Straight, cards, Arrays.asList(Rank.Ace));
        
        HandLocator mockFirst = mock(HandLocator.class);
        when(mockFirst.findBestHand(eq(cards))).thenReturn(handFirst);
        
        HandLocator mockSecond = mock(HandLocator.class);
        when(mockSecond.findBestHand(eq(cards))).thenReturn(handSecond);
        
        CombinedLocator locator = new CombinedLocator(Arrays.asList(mockFirst, mockSecond));
        Hand actual = locator.findBestHand(cards);
        
        assertEquals(handFirst, actual);
    }
    
    @Test
    public void twoLocatorsSecondWins(){
        List<Card> cards = Card.parse("Ah Ad Kh Kd 8c");
        Hand handFirst = null;
        Hand handSecond = new Hand(HandCategory.Straight, cards, Arrays.asList(Rank.Ace));
        
        HandLocator mockFirst = mock(HandLocator.class);
        when(mockFirst.findBestHand(eq(cards))).thenReturn(handFirst);
        
        HandLocator mockSecond = mock(HandLocator.class);
        when(mockFirst.findBestHand(eq(cards))).thenReturn(handSecond);
        
        CombinedLocator locator = new CombinedLocator(Arrays.asList(mockFirst, mockSecond));
        Hand actual = locator.findBestHand(cards);
        
        assertEquals(handSecond, actual);
    }
    
    @Test
    public void twoLocatorsFirstWinsWithWeakHand(){
        List<Card> cards = Card.parse("Ah Ad Kh Kd 8c");
        Hand handFirst = new Hand(HandCategory.HighCard, cards, Arrays.asList(Rank.Ace));
        Hand handSecond = new Hand(HandCategory.Straight, cards, Arrays.asList(Rank.Ace));
        
        HandLocator mockFirst = mock(HandLocator.class);
        when(mockFirst.findBestHand(eq(cards))).thenReturn(handFirst);
        
        HandLocator mockSecond = mock(HandLocator.class);
        when(mockFirst.findBestHand(eq(cards))).thenReturn(handSecond);
        
        CombinedLocator locator = new CombinedLocator(Arrays.asList(mockFirst, mockSecond));
        Hand actual = locator.findBestHand(cards);
        
        assertEquals(handSecond, actual);
    }
}
