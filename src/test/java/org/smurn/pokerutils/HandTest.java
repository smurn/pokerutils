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

import java.util.Arrays;
import junitx.extensions.EqualsHashCodeTestCase;

/**
 * Unit tests for {@link Hand}.
 */
public class HandTest extends EqualsHashCodeTestCase{

    public HandTest(){
        super("HandTest");
    }
    
    @Override
    protected Object createInstance() throws Exception {
        return new Hand(HandCategory.Flush, 
                Arrays.asList(Card.C2, Card.C5, Card.CA, Card.CK, Card.CT), 
                Arrays.asList(Rank.King, Rank.Seven));
    }

    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new Hand(HandCategory.Flush, 
                Arrays.asList(Card.C9, Card.C5, Card.CA, Card.CK, Card.CT), 
                Arrays.asList(Rank.Ace, Rank.Seven));
    }
    
}
