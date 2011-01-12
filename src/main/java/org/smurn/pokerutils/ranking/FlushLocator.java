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
 * Finds the best flush hand in a set of cards.
 */
public final class FlushLocator implements HandLocator {

    @Override
    public Hand findBestHand(final Collection<Card> cards) {
        if (cards == null) {
            throw new NullArgumentException("cards");
        }
        if (cards.size() < 5) {
            throw new IllegalArgumentException("require at least 5 cards.");
        }

        // sort cards by suit and then by rank

        List<Card> cardsCopy = new ArrayList<Card>(cards);
        Collections.sort(cardsCopy, new Comparator<Card>()  {

            @Override
            public int compare(final Card o1, final Card o2) {
                if (o1.getSuit() != o2.getSuit()) {
                    return o1.getSuit().ordinal() - o2.getSuit().ordinal();
                } else {
                    return o2.getRank().getRankValue()
                            - o1.getRank().getRankValue();
                }
            }
        });

        // find all flushes within the cards, remember where they started.
        List<Integer> candidates = new ArrayList<Integer>();
        int lastStart = 0;
        for (int i = 1; i < cardsCopy.size(); i++) {
            if (cardsCopy.get(lastStart).getSuit()
                    == cardsCopy.get(i).getSuit()) {
                if (i - lastStart + 1 == 5) {
                    // we found a flush
                    candidates.add(lastStart);
                }
            } else {
                lastStart = i;
            }
        }

        if (candidates.isEmpty()) {
            return null;
        }

        // compare the candidates by rank from highest to lowest
        for (int offset = 0; offset < 5; offset++) {
            Rank bestRank = cardsCopy.get(candidates.get(0) + offset).getRank();
            for (int i = 1; i < candidates.size();) {
                Rank rank = cardsCopy.get(candidates.get(i) + offset).getRank();
                if (rank.getRankValue() > bestRank.getRankValue()) {
                    bestRank = rank;
                    for (int j = i - 1; j >= 0; j--) {
                        candidates.remove(j);
                    }
                    i = 1;
                } else if (rank.getRankValue() < bestRank.getRankValue()) {
                    candidates.remove(i);
                } else {
                    i++;
                }
            }
        }

        int start = candidates.get(0);

        List<Card> bestCards = new ArrayList<Card>();
        bestCards.addAll(cardsCopy.subList(start, start + 5));

        List<Rank> bestRanks = new ArrayList<Rank>();
        for (int i = 0; i < bestCards.size(); i++) {
            bestRanks.add(bestCards.get(i).getRank());
        }

        return new Hand(HandCategory.Flush, bestCards, bestRanks);
    }
}
