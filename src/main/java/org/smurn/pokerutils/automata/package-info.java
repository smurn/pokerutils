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

/**
 * Automata to run a poker game.
 * <p>
 * A poker game is modelled as a finite state automata. Each state
 * is represented by a {@link org.smurn.pokerutils.Table}. The {@link Automata}
 * provided in this package controll the transitions from one Table state
 * to the next.
 * </p><p>
 * Various usecases need detailed information about the changes to the table
 * during game. For example for logging or for animation within the GUI. Simply
 * knowning the previous and the next table state would be insufficient.
 * The changes could be extracted by comparing the table state before and after
 * a certain action was performed. But, besides not beeing very elegant,
 * the reasons for the changes (semantic) cannot be extracted this way.
 * </p><p>
 * For the above reasons there is a set of {@link Change} classes. Those
 * classes represent a particular modification that can be applied to a table.
 * This is similar in consept to 'diff' files used in the unix world. A poker
 * game session is described by a sequence of such changes, applied one by one
 * to an inital table (usually an empty one). Each change stores all the
 * required information to apply it to a table. It also has a semantic meaning
 * that allows to write meaning full log messages or activate the right kind of
 * animation.
 * </p><p>
 * The change sequence it self does guarantee that the game follows a certain
 * set of poker rules. This is the task of the {@link Automata} implementations.
 * They produce the changes based on the players actions and the poker rules
 * that the automata follows.
 * </p>
 */
package org.smurn.pokerutils.automata;