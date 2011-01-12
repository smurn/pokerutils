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
 * Finds the best full-house hand in a set of cards.
 */
public final class FullHouseLocator implements HandLocator {

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

        // find the highest three-of-a-kind
        int startThreeOfAKind = -1;
        for (int i = 2; i < cardsCopy.size(); i++) {
            if (cardsCopy.get(i - 2).getRank() == cardsCopy.get(i - 1).getRank()
                    && cardsCopy.get(i - 1).getRank()
                     == cardsCopy.get(i).getRank()) {
                if (startThreeOfAKind < 0) {
                    startThreeOfAKind = i - 2;
                }
            }
        }

        if (startThreeOfAKind < 0) {
            return null;
        }

        // find the highest pair
        int startPair = -1;
        for (int i = 0; i < cardsCopy.size() - 1; i++) {
            if (i == startThreeOfAKind) {
                i += 3;
            }
            if (i + 1 == startThreeOfAKind) {
                i += 4;
            }
            if (i < cardsCopy.size() - 1) {
                if (cardsCopy.get(i).getRank()
                         == cardsCopy.get(i + 1).getRank()) {
                    if (startPair < 0) {
                        startPair = i;
                    }
                }
            }
        }

        if (startPair < 0) {
            return null;
        }


        List<Card> bestCards = new ArrayList<Card>();
        bestCards.add(cardsCopy.get(startThreeOfAKind)); // card of ToK
        bestCards.add(cardsCopy.get(startThreeOfAKind + 1)); // card of ToK
        bestCards.add(cardsCopy.get(startThreeOfAKind + 2)); // card of ToK
        bestCards.add(cardsCopy.get(startPair)); // card of pair
        bestCards.add(cardsCopy.get(startPair + 1)); // card of pair

        List<Rank> bestRanks = new ArrayList<Rank>();
        bestRanks.add(bestCards.get(0).getRank()); // rank of the ToK
        bestRanks.add(bestCards.get(3).getRank()); // rank of the pair


        return new Hand(HandCategory.FullHouse, bestCards, bestRanks);
    }
}
