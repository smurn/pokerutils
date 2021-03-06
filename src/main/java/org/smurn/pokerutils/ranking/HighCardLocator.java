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
 * Locates the best high-card hand in a set of cards.
 */
public final class HighCardLocator implements HandLocator {

    /**
     * Returns the best high-card hand in a set of cards.
     * @param cards The available cards. Must not be null.
     * @return The best hand that can be made with the given cards. Never null.
     */
    @Override
    public Hand findBestHand(final Collection<Card> cards) {
        if (cards == null) {
            throw new NullArgumentException("cards");
        }
        if (cards.size() < 5) {
            throw new IllegalArgumentException("require at least 5 cards.");
        }

        List<Card> cardsCopy = new ArrayList<Card>(cards);
        Collections.sort(cardsCopy, new Comparator<Card>()   {

            @Override
            public int compare(final Card o1, final Card o2) {
                return o2.getRank().getRankValue()
                         - o1.getRank().getRankValue();
            }
        });

        List<Card> bestCards = cardsCopy.subList(0, 5);
        List<Rank> bestRanks = new ArrayList<Rank>();
        for (Card card : bestCards) {
            bestRanks.add(card.getRank());
        }

        return new Hand(HandCategory.HighCard, bestCards, bestRanks);
    }
}
