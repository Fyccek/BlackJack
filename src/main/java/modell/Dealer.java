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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Dealer class represents the Dealer in te BlakJack card game.
 */
public class Dealer {

    Player player = new Player();

    /**
    * Logger instance for logging.
    */
    private static final Logger LOGGER = LoggerFactory.getLogger(Dealer.class);
	/**
     * Cards list containing the cards.
     * */
    private final List<String> cards = new ArrayList<String>(52);

    /**
     * Deck list containing the cards for operations.
     * */
    private static List<String> deck = new ArrayList<String>(52);

    /**
     * A Random class instance.
     * */
    private final Random random = new Random();

    /**
     * Constructor of the Dealer class.
     *
     * <p>
     *     It creates the deck of cards.
     * </p>
     * */
    public Dealer(){
        List<String> seged = new ArrayList<String>();

        seged.add("2");
        seged.add("3");
        seged.add("4");
        seged.add("5");
        seged.add("6");
        seged.add("7");
        seged.add("8");
        seged.add("9");
        seged.add("10");
        seged.add("J");
        seged.add("Q");
        seged.add("K");
        seged.add("A");

        for (String aSeged : seged) {
            this.cards.add("clubs" + aSeged);
            this.cards.add("diamonds" + aSeged);
            this.cards.add("hearts" + aSeged);
            this.cards.add("spades" + aSeged);
        }

        deck = cards;

        LOGGER.info("A Dealer have been created!");
    }

    /**
     * Deal a card to a Player after shuffled the deck.
     *
     * @param player , a player for deal to.
     * @param position, the position of the dealt card.
     *
     * */
    public void dealToPlayer(Player player, int position) {
        Collections.shuffle(deck);
        player.setCard(Dealer.deck.remove(deck.size() - 1), position);
    }

    /**
     * Deal a card to an AI after shuffled the deck.
     *
     * @param ai , a player for deal to.
     * @param position, the position of the dealt card.
     *
     * */
    public void dealToAi(ArtInt ai, int position){
        Collections.shuffle(deck);
        ai.setCard(Dealer.deck.remove(deck.size() - 1), position);

        LOGGER.info("A card have been dealt to AI.");
    }

    /**
     * Shuffle the deck of cards.
     * */
    public void shuffle(){
        String seged;

        for(int kever = 0; kever < 52; kever++){
            seged = this.cards.get(kever);
            this.cards.set(kever, this.cards.get(kever = random.nextInt(52)));
            this.cards.set(kever, seged);
            
            LOGGER.info("The Dealer has shuffled the deck");

        }
    }
}
