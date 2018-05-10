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
 * Player class is representing a player in the game.
 *
 * */
public class Player {

    /**
     * Logger instance for logging.
     *
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

    /**
     * Variable to contain the credits of the players.
     *
     * */
    private int credit;

    /**
     * Variable to contains the bets of the players.
     *
     * */
    private int bet;

    /**
     * Variable to representing the cards in hands of the players.
     *
     * */
    private String[] hand;

    /**
     * Constructor of the Player class.
     *
     * */
    public Player(){

        this.credit = 5000;
        this.hand = new String[9];
        for(int i = 0; i < this.hand.length; i++){
            this.hand[i] = new String();
        }
        this.bet = 0;
        
        LOGGER.info("A player have been created!");
    }

    /**
     * Method for set a card in a player's hand in a position.
     *
     * @param card the setting card.
     * @param i the position for the card.
     *
     * */
    public void setCard(String card, int i){

        this.hand[i] = card;
        LOGGER.info("Player's setter for card.");
    }


    /**
     * Method for change the bet.
     *
     * @param plus , amount of changing.
     *
     * */
    public void PlusBet(int plus){
        this.bet += plus;

        LOGGER.info("Player's bet has been changed.");

    }

    /**
     * Method for add credit to a player.
     *
     * @param plus amount of adding to credit.
     *
     * */
    public void PlusCredit(int plus){
        this.credit += plus;
        
        LOGGER.info("Player's credit have been changed.");
    }

    /**
     * Setter method for credit variable.
     *
     * @param credit amount of setting.
     *
     * */
    public void setCredit(int credit) {
        this.credit = credit;
        
        LOGGER.info("Player's setter for credit");

    }

    /**
     * Setter method for hand variable.
     *
     * @param hand hand you want to set.
     * */
    public void setHand(String[] hand) {
        this.hand = hand;

        LOGGER.info("Player's setter for hand.");
    }

    /**
     * Setter method for bet variable.
     *
     * @param bet amount of setting.
     *
     * */
    public void setBet(int bet) {
        this.bet = bet;
        
        LOGGER.info("A player's bet have been set to: {}", this.getBet());
    }

    /**
     * Getter method for credit variable.
     *
     * @return amount of credit of a player.
     *
     * */
    public int getCredit() {
        return this.credit;
        
    }

    /**
     * Getter method for bet variable.
     *
     * @return amount of bet of a player.
     *
     * */
    public int getBet() {
        return this.bet;
    }

    /**
     * Getter method for hand variable.
     *
     * @return hand of a player.
     *
     * */
    public String[] getHand(){
        return this.hand;
    }

    /**
     * Return a card from the player's hand.
     *
     * @param i, the position.
     **/
    public String getHandCard(int i){
        return this.hand[i];
    }
}
