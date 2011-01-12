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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.NullArgumentException;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;
import org.smurn.pokerutils.HandCategory;
import org.smurn.pokerutils.Suit;

/**
 * Find the best straight flush in a set of cards.
 */
public final class StraightFlushLocator implements HandLocator {

    @Override
    public Hand findBestHand(final Collection<Card> cards) {
        if (cards == null) {
            throw new NullArgumentException("cards");
        }
        if (cards.size() < 5) {
            throw new IllegalArgumentException("require at least 5 cards.");
        }

        StraightLocator locator = new StraightLocator();
        Hand bestHand = null;

        for (Suit suit : Suit.values()) {
            List<Card> suitedCards = new ArrayList<Card>();
            for (Card card : cards) {
                if (card.getSuit() == suit) {
                    suitedCards.add(card);
                }
            }
            if (suitedCards.size() >= 5) {
                Hand hand = locator.findBestHand(suitedCards);
                if (hand != null && ( bestHand == null
                        || bestHand.getRanks().get(0).getRankValue()
                         < hand.getRanks().get(0).getRankValue() )) {
                    bestHand = hand;
                }
            }
        }

        if (bestHand == null) {
            return null;
        }
        return new Hand(HandCategory.StraightFlush, bestHand.getCards(),
                bestHand.getRanks());
    }
}
