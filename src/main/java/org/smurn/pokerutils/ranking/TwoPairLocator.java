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
 * Finds the best two-pair hand in a set of cards.
 */
public final class TwoPairLocator implements HandLocator {

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

        // find the highest pair

        int firstPair = -1;
        int secondPair = -1;
        for (int i = 0; i < cardsCopy.size() - 1; i++) {
            if (cardsCopy.get(i).getRank() == cardsCopy.get(i + 1).getRank()) {
                // found a pair
                if (firstPair < 0) {
                    firstPair = i;
                } else if (secondPair < 0) {
                    secondPair = i;
                }
                i++; // the next card cannot be the beginning of a pair since
                // it is used for this pair.
            }
        }

        if (secondPair < 0) {
            return null; // no two-pair
        }

        // we found the highest two-pair, construct hand.

        List<Card> bestCards = new ArrayList<Card>();
        bestCards.add(cardsCopy.get(firstPair)); // card of high pair
        bestCards.add(cardsCopy.get(firstPair + 1)); // card of high pair
        bestCards.add(cardsCopy.get(secondPair)); // card of low pair
        bestCards.add(cardsCopy.get(secondPair + 1)); // card of low pair
        // kickers higher than high pair
        bestCards.addAll(cardsCopy.subList(0, firstPair));
        // kickers between pairs
        bestCards.addAll(cardsCopy.subList(firstPair + 1, secondPair));
        // kickers after low pair
        bestCards.addAll(cardsCopy.subList(secondPair + 1, cardsCopy.size()));
        bestCards = bestCards.subList(0, 5);

        List<Rank> bestRanks = new ArrayList<Rank>();
        bestRanks.add(bestCards.get(0).getRank()); // rank of the high pair
        bestRanks.add(bestCards.get(2).getRank()); // rank of the low pair
        bestRanks.add(bestCards.get(4).getRank()); // rank of the kicker

        return new Hand(HandCategory.TwoPair, bestCards, bestRanks);
    }
}
