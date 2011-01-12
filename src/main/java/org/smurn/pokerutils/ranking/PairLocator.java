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
 * Finds the best pair hand.
 */
public final class PairLocator implements HandLocator {

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
        Collections.sort(cardsCopy, new Comparator<Card>()       {

            @Override
            public int compare(final Card o1, final Card o2) {
                return o2.getRank().getRankValue()
                         - o1.getRank().getRankValue();
            }
        });

        // find the highest pair

        int firstCardOfPair = -1;
        for (int i = 1; i < cardsCopy.size(); i++) {
            if (cardsCopy.get(i - 1).getRank() == cardsCopy.get(i).getRank()) {
                if (firstCardOfPair < 0) {
                    firstCardOfPair = i - 1;
                }
            }
        }

        if (firstCardOfPair < 0) {
            return null; // no pair
        }
        // we found the highest pair, construct hand.

        List<Card> bestCards = new ArrayList<Card>();
        bestCards.add(cardsCopy.get(firstCardOfPair));         // card of pair
        bestCards.add(cardsCopy.get(firstCardOfPair + 1));     // card of pair
        // kickers higher than pair
        bestCards.addAll(cardsCopy.subList(0, firstCardOfPair));
        // kickers lower than pair
        bestCards.addAll(cardsCopy.subList(firstCardOfPair + 2,
                cardsCopy.size()));
        bestCards = bestCards.subList(0, 5);

        List<Rank> bestRanks = new ArrayList<Rank>();
        bestRanks.add(bestCards.get(0).getRank()); // rank of the pair
        for (int i = 2; i < bestCards.size(); i++) {
            bestRanks.add(bestCards.get(i).getRank());
        }

        return new Hand(HandCategory.Pair, bestCards, bestRanks);
    }
}
