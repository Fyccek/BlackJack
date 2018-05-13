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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The GameMaster class represent a higher entity in the poker game,
 * hoes duty is to watch over the rules, and decides who wins.
 *
 */
public class GameMaster {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GameMaster.class);

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
     * Public constructor of the GameMaster class.
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
     * Method for decide who wins with {@link WinnerCalc#calculateWinner(String[], String[])}.
     *
     * @param player the player.
     * @param ai the ai.
     *
     * @return 1 if the player wins, 2 if the ai.
     *
     * */
    public int getWinner(Player player, ArtInt ai){return this.winnerCalc.calculateWinner(player.getHand(), ai.getHand());}

    /**
     * Method for calculate a hand's strength with {@link WinnerCalc#calculateCardsValue(String[])}.
     *
     * @param hand ai's or player's hand.
     *
     * @return Value of the hand
     * */
    public int getHandValue(String[] hand){return this.winnerCalc.calculateCardsValue(hand);}

    /**
     * Setter method for minBet variable.
     *
     * @param minbet we want to set.
     *
     * */
    public void setMinBet(int minbet) {
        this.minBet = minbet;
        LOGGER.info("Minimum bet has been set");
    }

    /**
     * Getter for minBet variable
     *
     * @return Value of minbet.
     * */
    public int getMinBet() {
        return this.minBet;
    }

    /**
     *Getter method for dealer object.
     *
     * @return The dealer object.
     * */
    public Dealer getDealer() {return this.dealer;}

    /**
     * Getter method for ai object.
     *
     * @return The ai object.
     * */
    public ArtInt getAi() {return this.ai;}

    /**
     * Getter method for player object.
     *
     * @return The player object.
     * */
    public Player getPlayer() {return this.player;}

    /**
     * Getter method for player2 variable.
     *
     * @return The player2 object.
     * */
    public Player getPlayer2() {return this.player2;}

    /**
     * Getter method for get a card's value.
     * Calculate with {@link WinnerCalc#calculateCardValue(String)}
     *
     * @param card the one we calculate the value.
     *
     * @return A card's value.
     * */
    public int getCardValue(String card){return this.winnerCalc.calculateCardValue(card);}
}
