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

import javax.xml.bind.annotation.*;

/**
 * The ArtInt class represents the enemy ai in the BlackJack card game.
 * */
public class ArtInt {

    /**
     * Logger instance for logging.
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtInt.class.getName());	
	/**
     * Variable to represents the ai's money.
     * */
	private int credit;

	/**
     * Variable to represents the ai's bet.
     * */
    private int bet;

    /**
     * Calculate the card's value.
     * */
	private final WinnerCalc winnerCalc;

    /**
     * Variable to representing the cards in hands of the players.
     *
     * */
	private String[] hand;

	/**
     * Constructor of the ArtInt class.
     *
     * */
	public ArtInt() {

		this.winnerCalc = new WinnerCalc();
		this.credit = 5000;
		this.hand = new String[9];
		for(int i = 0; i < this.hand.length; i++){
			this.hand[i] = new String();
		}
		this.bet = 0;

		LOGGER.info("An AI unit have been created!");
	}

	/**
     * Method to know if the turn is ai's turn.
     *
     * @return true, if it is.
     *
     * */
	public boolean AiTurn(){
		return true;
	}

	/**
     * Method to know if the ai's have to hint.
     *
     * @return true, if have to.
     * */
	public boolean AiHint() { return winnerCalc.calculateCardsValue(hand) < 17; }

	public void setCard(String card, int i){
		this.hand[i] = card;
	}

	/**
     * Setter method for credit variable.
     *
     * @param credit
     * */
	public void setCredit(int credit) {
		this.credit = credit;
		
		LOGGER.info("AI's credit have been set to: ", this.getCredit());
	}

	/**
     * Setter method for bet variable.
     *
     * @param bet
     * */
	public void setBet(int bet) {
		this.bet = bet;
		
		LOGGER.info("AI's bet have been modified to: ", this.getBet());
	}

	/**
     * Setter method for hand variable.
     *
     * @param hand
     * */
	public void setHand(String[] hand) {
		this.hand = hand;
		
		LOGGER.info("AI's hand have been set to: ", (Object)this.getHand());
	}

    /**
     * Method for change the bet.
     *
     * @param plus , amount of changing.
     *
     * */
	public void plusBet(int plus){
	    this.bet += bet;
    }

    /**
     * Method for change the credit.
     *
     * @param plus , amount of changing.
     *
     * */
    public void plusCredit(int plus){
        this.credit += plus;

        LOGGER.info("An AI's credit have been set by: ", plus);
        LOGGER.info("A AI's credit have been set to: ", this.getCredit());
    }

    /**
     * Return a card from the ai's hand.
     *
     * @param i, the position.
     **/
    public String getHandCard(int i){
        LOGGER.info("The " + i + ". card have been get.");
        return this.hand[i];
    }

    /**
     * Getter method for bet variable.
     *
     *@return bet
     * */
	public int getBet() {
		return bet;
	}


    /**
     * Getter method for credit variable.
     *
     *@return credit
     *
     * */
	public int getCredit() {
		return credit;
	}

    /**
     * Getter method for hand variable.
     *
     *@return hand
     * */
	public String[] getHand() {
		return hand;
	}
}
