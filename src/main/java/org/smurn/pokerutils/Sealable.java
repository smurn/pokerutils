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
package org.smurn.pokerutils;

/**
 * Interface for classes that follow the sealable pattern.
 * <p>
 * The sealable pattern is a relaxed form of immutability. For truly
 * immutable classes all values must be passed via the constructor. For
 * classes with a certain complexitiy and even more so for data structures
 * this is leads to a inconviniently large number of constructor parameters.
 * </p><p>
 * A sealable object has two modes: unsealed and sealed. After creation it
 * is unsealed. While unsealed
 * the object can be modified. Once sealed any attempt to modify the object
 * results in a {@link UnsupportedOperationException}. An object is sealed
 * by calling {@link #seal()}. Once sealed the object cannot be unsealed.
 * </p><p>
 * After the creation the unsealed object can be initialized via setters
 * and a like. Before the object is put to use it must be sealed since
 * the unsealed state is for initialization use only.
 * </p><p>
 * Public methods returning
 * an instance must ensure that it is sealed. Public methods that take
 * an instance as a parameter
 * should throw a {@link IllegalArgumentException} if the given instance
 * is not sealed. Any derivation of this must be explicitly documented.
 * </p>
 */
public interface Sealable {

    /**
     * Makes this object immutable.
     * Further attemts to modify this object will throw a
     * {@link UnsupportedOperationException}.
     * If this method is called more than once, further calls are ignored.
     */
    void seal();

    /**
     * Checks if this instance is sealed.
     * @return Returns {@code true} if sealed, {@code false} otherwise.
     */
    boolean isSealed();
}
