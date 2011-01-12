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
package org.smurn.pokerutils.automata;

import org.smurn.pokerutils.Player;
import org.smurn.pokerutils.Seat;
import org.smurn.pokerutils.Table;

/**
 *
 * @author stefan
 */
public interface Automata {

    /**
     * Creates an empty table that is correctly initialized for use by
     * this automata.
     * @param seats Number of seats the created table. Must be non-negative.
     * @return A table with no players on it with the given number of seats. Never {@code null}.
     */
    Table createEmptyTable(int seats);
    
    Table dealCards(Table table);

    boolean canDealCards(Table table);

    Table moveButtons(Table table);

    boolean canMoveButtons(Table table);

    Table collectBets(Table table);

    boolean canCollectBets(Table table);

    Table movePotsToWinners(Table table);

    boolean canMovePotsToWinners(Table table);

    Table sitDown(Table table, Seat seat, Player player);

    boolean canSitDown(Table table, int seat);

    Table getUp(Table table, Seat seat);

    boolean canGetUp(Table table, int seat);

    Table fold(Table table, Seat seat);

    boolean canFold(Table table, int seat);

    Table call(Table table, Seat seat);

    boolean canCall(Table table, Seat seat);

    int callAmount(Table table);

    Table raise(Table table, Seat seat, int amount);

    boolean canRaise(Table table, Seat seat);

    int minimalRaise(Table table);

    int maximalRaise(Table table);
}
