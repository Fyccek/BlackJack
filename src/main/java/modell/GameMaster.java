package modell;

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

/**
 * The GameMaster class represent a higher entity in the poker game,
 * hoes duty is to watch over the rules, and decides who wins.
 *
 */
public class GameMaster {

    /**
     * Variable to contain the minimum bet.
     *
     * */
    private int minBet;

    /**
     * Dealer instance.
     * */
    private final Dealer dealer;

    /**
     * WinnerCalc instance.
     * */
    private final WinnerCalc winnerCalc;

    /**
     * Player instance.
     **/
    private final Player player;

    /**
     * Another Player instance.
     **/
    private final Player player2;

    /**
     * ArtInt instance.
     * */
    private final ArtInt ai;

    /**
     * Constructor of the GameMaster class.
     * */
    public GameMaster() {

    	this.minBet            = 200;
        this.winnerCalc        = new WinnerCalc();
        this.dealer            = new Dealer();
        this.player            = new Player();
        this.ai                = new ArtInt();
        this.player2           = new Player();
}

    /**
     * Method for decide who wins.
     *
     * @param player
     * @param ai
     *
     * @return 1 if the player wins, 2 if the ai.
     * */
    public int getWinner(Player player, ArtInt ai){return this.winnerCalc.calculateWinner(player.getHand(), ai.getHand());}

    /**
     * Method for calculate a hand's strength.
     *
     * @param hand
     * */
    public int getHandValue(String[] hand){return this.winnerCalc.calculateCardsValue(hand);}

    /**
     * Setter method for minBet variable.
     *
     * @param minbet
     * */
    public void setMinBet(int minbet) {this.minBet = minbet;}

    /**
     * Getter for minBet variable
     *
     * */
    public int getMinBet() {
        return this.minBet;
    }

    /**
     *Getter method for dealer variable.
     * */
    public Dealer getDealer() {return this.dealer;}

    /**
     * Getter method for ai variable.
     * */
    public ArtInt getAi() {return this.ai;}

    /**
     * Getter method for player variable.
     * */
    public Player getPlayer() {return this.player;}

    /**
     * Getter method for player2 variable.
     * */
    public Player getPlayer2() {return this.player2;}

    public WinnerCalc getWinnerCalc() { return this.winnerCalc; }
}
