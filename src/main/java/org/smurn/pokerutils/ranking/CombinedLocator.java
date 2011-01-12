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
import java.util.List;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;

/**
 * A hand locator that makes use of a sequence of other locators.
 */
public final class CombinedLocator implements HandLocator {

    /** Locators used. */
    private final List<HandLocator> locators;

    /**
     * Creates a CombinedLocator instance.
     * @param sequence Hand locators to use ordered by priority, the earlier
     * locator wins. If empty, this locator will always return null. Must not
     * be null.
     */
    public CombinedLocator(final List<HandLocator> sequence) {
        this.locators = Collections.unmodifiableList(
                new ArrayList<HandLocator>(sequence));
    }

    /**
     * Finds the best hand using a sequence of hand locators.
     * The first locator in the sequence (given to the constructor) returning
     * a non-null hand determines the hand returned by this locator.
     * @param cards The cards for with the best hand is located.
     * @return  The best hand, or null if none was found.
     */
    @Override
    public Hand findBestHand(final Collection<Card> cards) {
        for (HandLocator locator : locators) {
            Hand hand = locator.findBestHand(cards);
            if (hand != null) {
                return hand;
            }
        }
        return null;
    }
}
