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

import java.util.Collection;
import org.smurn.pokerutils.Card;
import org.smurn.pokerutils.Hand;

/**
 * Finds the best hand in a set of cards.
 * The exact definition of 'best' depends on the implementation.
 */
public interface HandLocator {

    /**
     * Finds the best in a set of cards.
     * @param cards The available cards. Must be at least 5.
     * @return The best hand that can be made with the given cards or null
     * if no hand was found.
     */
    Hand findBestHand(Collection<Card> cards);
}
