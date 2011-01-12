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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.NullArgumentException;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;
import org.smurn.pokerutils.HandCategory;
import org.smurn.pokerutils.Rank;

/**
 * Finds the best straight hand in a set of cards.
 */
public final class StraightLocator implements HandLocator {

    @Override
    public Hand findBestHand(final Collection<Card> cards) {
        if (cards == null) {
            throw new NullArgumentException("cards");
        }
        if (cards.size() < 5) {
            throw new IllegalArgumentException("require at least 5 cards.");
        }

        // sort cards by rank

        List<Card> cardsCopy = new ArrayList<Card>(cards);
        Collections.sort(cardsCopy, new Comparator<Card>() {

            @Override
            public int compare(final Card o1, final Card o2) {
                return o2.getRank().getRankValue()
                         - o1.getRank().getRankValue();
            }
        });

        // find the highest straight
        List<Card> bestCards = new ArrayList<Card>();
        int lastRank = cardsCopy.get(0).getRank().getRankValue();
        bestCards.add(cardsCopy.get(0));

        for (int i = 1; i < cardsCopy.size(); i++) {
            int rank = cardsCopy.get(i).getRank().getRankValue();
            if (lastRank != rank) { // else we ignore this card.
                if (lastRank != rank + 1) {
                    // the sequence has ended, we start a new one.
                    bestCards.clear();
                }
                bestCards.add(cardsCopy.get(i));
                lastRank = rank;

                if (bestCards.size() == 5) {
                    // we found our straight.
                    break;  // get out of the for loop.
                }
            }
        }

        if (bestCards.size() != 5) {
            return null;
        }

        List<Rank> bestRanks = new ArrayList<Rank>();
        bestRanks.add(bestCards.get(0).getRank());

        return new Hand(HandCategory.Straight, bestCards, bestRanks);
    }
}
