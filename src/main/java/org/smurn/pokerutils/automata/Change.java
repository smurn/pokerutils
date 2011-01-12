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

import org.smurn.pokerutils.Table;

/**
 * Describes one or more modifications of a table.
 */
public interface Change {

    /**
     * Applies this change to a table.
     * @param table Table to apply this change to. Must not be {@code null}.
     * @return Table with the change applied. Is never {@code null}.
     * @throws IncompatibleTableException If this change cannot be applied
     * to the given table.
     */
    Table apply(Table table);

}
