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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Sealable} implementations.
 */
public abstract class SealableTest {

    private Sealable sealable;

    /**
     * Creates an unsealed instance of the implementation under stest.
     * @return Unsealed instance.
     */
    protected abstract Sealable createUnsealedInstance();

    /**
     * Gets the instance to be tested.
     */
    @Before
    public final void SetupSealableTest() {
        sealable = createUnsealedInstance();
    }

    /**
     * Tests that the instance is unsealed in the beginning.
     */
    @Test
    public final void initiallyUnsealed() {
        assertFalse("A Sealable should not be sealed after creation.",
                sealable.isSealed());
    }

    /**
     * Test that {@link Sealable#isSealed()} goes to true after sealing.
     */
    @Test
    public final void sealingFlag() {
        sealable.seal();
        assertTrue("A Sealable should be sealed after seal().",
                sealable.isSealed());
    }

    /**
     * Tests that sealing mutliple times does not cause trouble.
     */
    @Test
    public final void multiSealing() {
        sealable.seal();
        sealable.seal();
        assertTrue("A Sealable should be sealed after seal().",
                sealable.isSealed());
    }
}
