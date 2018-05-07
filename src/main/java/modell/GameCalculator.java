package modell;

/*-
 * #%L
 * BlackJack
 * %%
 * Copyright (C) 2018 Istvan Gurmai
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
import java.util.ArrayList;
import java.util.List;


public class GameCalculator {

    private int minBet;
    private final Dealer dealer;
    private final WinnerCalc winnerCalc;
    private final Player player;
    private final ArtInt ai;

    public void initArray(){
        List<Object> playerarray = new ArrayList<>();
        playerarray.add(0, this.player);
        playerarray.add(1, this.ai);
    }

    public GameCalculator() {

    	this.minBet            = 200;
        this.winnerCalc        = new WinnerCalc();
        this.dealer            = new Dealer();
        this.player            = new Player();
        this.ai                = new ArtInt();
}

    public int getWinner(Player player, ArtInt ai){return this.winnerCalc.calculateWinner(player.getHand(), ai.getHand());}

    public int getHandValue(String[] hand){return this.winnerCalc.calculateCardsValue(hand);}

    public int getMinBet() {
        return this.minBet;
    }

    public void setMinBet(int minbet) {this.minBet = minbet;}

    public Dealer getDealer() {return this.dealer;}

    public ArtInt getAi() {return this.ai;}

    public Player getPlayer() {return this.player;}
}
