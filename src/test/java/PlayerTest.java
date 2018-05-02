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
import modell.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * Test class for Player class.
 **/
public class PlayerTest {

    private Player player;

    /**
     * Sets up the test.
     **/
    @Before
    public void setUp() {
        player = new Player();
    }

    /**
     * Test method for Player class's getters/setters.
     **/
    @Test
    public void gettersTest(){
        player.setCredit(1000);
        assertEquals(1000, player.getCredit());
        player.setBet(10000);
        assertEquals(10000, player.getBet());
        player.setCard("cica", 0);
        assertEquals("cica", player.getHandCard(0));
        player.setHand(new String[]{"cica", "kutya", "kacsa", "ló", "kecske", "szamár", "béka", "egér", "macsek"});
        assertArrayEquals(new String[]{"cica", "kutya", "kacsa", "ló", "kecske", "szamár", "béka", "egér", "macsek"}, player.getHand());
        player.setCard("nudli", 0);
        assertEquals("nudli", player.getHandCard(0));
    }
}
