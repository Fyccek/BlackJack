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
 * WinnerCalc class for calculate the winner of the game.
 *
 * */
public class WinnerCalc {

    /**
     * Temporary variable to help to calculate the winner.
     *
     * */
    private int seged = 0;

    /**
     * Variable to calculate amount of the cards in hands.
     *
     * */
    private int sum = 0;

    /**
     * Method for get the winner of the game.
     *
     * @param hand1 first player's hand.
     * @param hand2 second player's hand.
     *
     * @return 1 or 2 depends on which hand is more powerful.
     *
     * */
	  public int calculateWinner(String[] hand1, String[] hand2){

	        calculateCardsValue(hand1);
	        calculateCardsValue(hand1);

	        if(calculateCardsValue(hand1) > calculateCardsValue(hand2)) {
	            if(calculateCardsValue(hand1) > 21){
	                return 2;
	            } else{ return 1;}
	        } else {
	            if (calculateCardsValue(hand1) < calculateCardsValue(hand2)){
	                if(calculateCardsValue(hand2) > 21){
	                    return 1;
	                } else {return 2;}
	            }
	        }
	        if(calculateCardsValue(hand1) == 21 && calculateCardsValue(hand2) == 21){
	            return -1;
	        }
	        return 0;
	    }

	    /**
         * Method for get back a hand's value.
         *
         * @param hand, a hand of a player.
         *
         * @return value of a hand.
         *
         * */
	    public int calculateCardsValue(String[] hand) {

	        sum = 0;

	        for(int i = 0; i < hand.length; i++){
                if (hand[i].endsWith("2")) {
                    seged = 2;
                } else {
                    if (hand[i].endsWith("3")) {
                        seged = 3;
                    } else {
                        if (hand[i].endsWith("4")) {
                            seged = 4;
                        }else {
                            if (hand[i].endsWith("5")) {
                                seged = 5;
                            } else {
                                if (hand[i].endsWith("6")) {
                                    seged = 6;
                                } else {
                                    if (hand[i].endsWith("7")) {
                                        seged = 7;
                                    } else {
                                        if (hand[i].endsWith("8")) {
                                            seged = 8;
                                        } else {
                                            if (hand[i].endsWith("9")) {
                                                seged = 9;
                                            } else {
                                                if (hand[i].endsWith("10")) {
                                                    seged = 10;
                                                } else {
                                                    if (hand[i].endsWith("J")) {
                                                        seged = 10;
                                                    } else {
                                                        if (hand[i].endsWith("Q")) {
                                                            seged = 10;
                                                        } else {
                                                            if (hand[i].endsWith("K")) {
                                                                seged = 10;
                                                            } else {
                                                                if (hand[i].endsWith("A")) {
                                                                    if (seged <= 10) {
                                                                        seged = 11;
                                                                    } else {
                                                                        seged = 1;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } sum += seged; seged = 0;
                for (int j = 0; j < 9; j++) {
                    if (sum > 21 && hand[j].endsWith("A")) {
                        sum -= 10;
                    }
                }
	        }
	        return sum;
		}

    /**
     * Method for get back a card's value.
     *
     * @param card, a card.
     *
     * @return value of a card.
     *
     * */
    public int calculateCardValue(String card) {

            if (card.endsWith("2")) {
                seged = 2;
            } else {
                if (card.endsWith("3")) {
                    seged = 3;
                } else {
                    if (card.endsWith("4")) {
                        seged = 4;
                    }else {
                        if (card.endsWith("5")) {
                            seged = 5;
                        } else {
                            if (card.endsWith("6")) {
                                seged = 6;
                            } else {
                                if (card.endsWith("7")) {
                                    seged = 7;
                                } else {
                                    if (card.endsWith("8")) {
                                        seged = 8;
                                    } else {
                                        if (card.endsWith("9")) {
                                            seged = 9;
                                        } else {
                                            if (card.endsWith("10")) {
                                                seged = 10;
                                            } else {
                                                if (card.endsWith("J")) {
                                                    seged = 10;
                                                } else {
                                                    if (card.endsWith("Q")) {
                                                        seged = 10;
                                                    } else {
                                                        if (card.endsWith("K")) {
                                                            seged = 10;
                                                        } else {
                                                            if (card.endsWith("A")) {
                                                                    seged = 11;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return seged;
    }
}
