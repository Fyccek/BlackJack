package modell;

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
