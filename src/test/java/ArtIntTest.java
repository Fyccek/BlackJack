/*-
 * #%L
 * BlackJack
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
import modell.ArtInt;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test class for ArtInt class.
 **/
public class ArtIntTest {

    ArtInt ai;

    /**
     *Set up the test.
     * */
    @Before
    public void setUp(){
        ai = new ArtInt();
    }

    /**
     * Test method for ArtInt class's getters/setters.
     **/
    @Test
    public void gettersTest(){
        ai.setBet(10);
        assertEquals(10, ai.getBet());
        ai.setCredit(4000);
        assertEquals(4000, ai.getCredit());
        ai.setHand(new String[]{"cica", "kutya", "kacsa", "ló", "kecske", "szamár", "béka", "egér", "macsek"});
        assertArrayEquals(new String[]{"cica", "kutya", "kacsa", "ló", "kecske", "szamár", "béka", "egér", "macsek"}, ai.getHand());
        ai.setCard("nudli", 0);
        assertEquals("nudli", ai.getHandCard(0));
    }
}
