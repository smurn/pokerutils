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

/**
 * Exception indicating that a table passed to a method is not compatible
 * with the requirements of that method.
 */
public class IncompatibleTableException extends IllegalArgumentException {

    /**
     * Creates a new exception.
     * @param s Description of the problem.
     */
    public IncompatibleTableException(final String s) {
        super(s);
    }
}
