package controller;

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

import dao.PlayerEntity;
import dao.DBManager;
import dao.PlayerEntityDAOImpl;
import javafx.scene.control.Alert;
import modell.GameMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.security.spec.PSSParameterSpec;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;

public class MainFXMLController implements Initializable {

    private GameMaster gameMaster = new GameMaster();

    private int playerPosition = 0;
    private int aiPosition = 0;
    private boolean endofgame = false;
    public PlayerEntityDAOImpl playerEntityDAO = PlayerEntityDAOImpl.getPlayerEntityDAOImpl();

    @FXML
    private Button ConcedeButton;

    @FXML
    private AnchorPane Desk;

    @FXML
    private AnchorPane lowerDesk;

    @FXML
    private Button HintButton;

    @FXML
    private Button StartButton;

    @FXML
    private Button PassButton;

    @FXML
    private ImageView img01;

    @FXML
    private ImageView img02;

    @FXML
    private ImageView img03;

    @FXML
    private ImageView img04;

    @FXML
    private ImageView img05;

    @FXML
    private ImageView img06;

    @FXML
    private ImageView img07;

    @FXML
    private ImageView img08;

    @FXML
    private ImageView img11;

    @FXML
    private ImageView img12;

    @FXML
    private ImageView img13;

    @FXML
    private ImageView img14;

    @FXML
    private ImageView img15;

    @FXML
    private ImageView img16;

    @FXML
    private ImageView img17;

    @FXML
    private ImageView img18;

    @FXML
    private Label myScore;

    @FXML
    private Label aiScore;

    @FXML
    private Label myCredit;

    @FXML
    private Label Bets;

    @FXML
    private Label aiCredit;

    @FXML
    private Label myName;

    @FXML
    private ImageView Credit01;

    @FXML
    private ImageView Credit02;

    @FXML
    private TextField bettingField;

    @FXML
    private Button BetButton;

    @FXML
    private Button reMatch;

    @FXML
    private Button splitButton;
    
    private final Integer BASE_CREDIT = 5000;

    public static PlayerEntity playerEntity = MainMenuController.playerEntityDAO.findPlayerbyName(MainMenuController.NEVEM);

    public static int aiCreditAmount = 5000;
    public static Image imgStaticAi;
    public static Image imgStaticPlayer1;
    public static Image imgStaticPlayer2;
    public static int myHandsValue;
    public static int betsValue;
    public static int aiHandsValue;
    public static String aiFirstCard;

    public void initialize(URL location, ResourceBundle resources) {

        HintButton.setDisable(true);
        ConcedeButton.setDisable(false);
        PassButton.setDisable(true);
        StartButton.setDisable(true);
        reMatch.setVisible(false);
        splitButton.setVisible(false);

        System.out.println(playerEntity.getMyname());


        try {
            if (((playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) == null
                    || playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) == 0 || aiCreditAmount == 0))) {

                playerEntity.setCredit(BASE_CREDIT);
                aiCreditAmount = BASE_CREDIT;
            } else {
                playerEntity.setCredit(playerEntityDAO.findPlayersCredit(playerEntity.getMyname()));
                this.gameMaster.getAi().setCredit(aiCreditAmount);
            }

            playerEntityDAO.save(playerEntity);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Image img =
                new Image(getClass().getClassLoader()
                        .getResource("pictures/zseton.png").toString());

        Credit01.setImage(img);
        Credit02.setImage(img);

        myCredit.setText("" + playerEntityDAO.findPlayersCredit(playerEntity.getMyname()));
        aiCredit.setText("" + aiCreditAmount);

        myName.setText(playerEntity.getMyname());

    }

    @FXML
    public void concedeButtonAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane =
                FXMLLoader.load(getClass().getClassLoader().
                        getResource("fxml/MainMenu.fxml"));

        Desk.getChildren().setAll(pane);
    }

    @FXML
    public void splitButtonAction(ActionEvent actionEvent) throws IOException {
        myHandsValue = this.gameMaster.getCardValue(this.gameMaster.getPlayer().getHandCard(0));
        betsValue = parseInt(Bets.getText());
        aiHandsValue = this.gameMaster.getCardValue(this.gameMaster.getAi().getHandCard(0));
        aiFirstCard = this.gameMaster.getAi().getHandCard(0);
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Split.fxml"));
        Desk.getChildren().setAll(pane);
    }

    @FXML
    public void hintButtonAction(ActionEvent event) {
        HintPlayerHand();
        if(parseInt(myScore.getText()) > 21) {
            int bet = parseInt(Bets.getText());
            aiCreditAmount += bet;
            aiCredit.setText("" + aiCreditAmount);
            Bets.setText("");
            HintButton.setDisable(true);
            PassButton.setDisable(true);
            reMatch.setVisible(true);
            ConcedeButton.setDisable(false);

            if (aiCreditAmount == 0 || playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) == 0){whoWon();} else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RESULT");
                alert.setContentText(":(");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void letsPlay(ActionEvent event) {
        InitializePlayerHand();
        InitializeAiHand();

        StartButton.setVisible(false);
        HintButton.setDisable(false);
        ConcedeButton.setDisable(true);
        PassButton.setDisable(false);

        imgStaticAi = img01.getImage();

        imgStaticPlayer1 = img11.getImage();
        imgStaticPlayer2 = img12.getImage();

        if (this.gameMaster.getCardValue(this.gameMaster.getPlayer().getHandCard(0))
                == this.gameMaster.getCardValue(this.gameMaster.getPlayer().getHandCard(1))) {
            splitButton.setVisible(true);
        }

        if ((this.gameMaster.getPlayer().getHandCard(0).endsWith("A")
                && this.gameMaster.getPlayer().getHandCard(0).endsWith("K"))
                || (this.gameMaster.getPlayer().getHandCard(0).endsWith("K")
                && this.gameMaster.getPlayer().getHandCard(0).endsWith("A"))) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WAOOO");
            alert.setHeaderText("You got BlacJack!!");
            alert.setContentText("Instant win! :)");
            alert.showAndWait();
        }
    }

    @FXML
    public void PassButtonAction(ActionEvent event) {
        reMatch.setVisible(true);
        ConcedeButton.setDisable(false);
        HintButton.setDisable(true);
        PassButton.setDisable(true);
        endofgame = true;
        HintAiHand();
        aiScore.setText("" + this.gameMaster.getHandValue(this.gameMaster.getAi().getHand()));

        if(this.gameMaster.getWinner(this.gameMaster.getPlayer(), this.gameMaster.getAi()) == 1) {

            int bet = parseInt(Bets.getText());
            playerEntity.setCredit(playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) + bet);

            Bets.setText("");
            playerEntityDAO.save(playerEntity);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setContentText(":)");
            alert.showAndWait();

        } else {
            int betai = parseInt(Bets.getText());
            aiCreditAmount += betai ;
            Bets.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setContentText(":(");
            alert.showAndWait();
        }

        myCredit.setText("" + playerEntityDAO.findPlayersCredit(playerEntity.getMyname()));
        aiCredit.setText("" + aiCreditAmount);

        whoWon();
    }

    @FXML
    public void reMatchAction(ActionEvent event) throws IOException {
        if(parseInt(aiCredit.getText()) == 0 || parseInt(myCredit.getText()) == 0) {
            aiCreditAmount = 5000;
            playerEntity.setCredit(5000);
        }
        playerEntityDAO.save(playerEntity);
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainFXml.fxml"));
        Desk.getChildren().setAll(pane);
    }

    @FXML
    public void Betting(ActionEvent event) {
        StartButton.setDisable(false);
        BetButton.setVisible(false);
        bettingField.setVisible(false);

        int  bet = parseInt(bettingField.getText());

        if (bet > playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) || bet > aiCreditAmount) {
            StartButton.setDisable(true);
            BetButton.setVisible(true);
            bettingField.setVisible(true);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Too much bet!");
            alert.setContentText("Give less credit for betting!");
            alert.showAndWait();

        } else {

            if (bet < this.gameMaster.getMinBet()){

                StartButton.setDisable(true);
                BetButton.setVisible(true);
                bettingField.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Too less bet!");
                alert.setContentText("Give more credit for betting!");
                alert.showAndWait();
        }
        else {

            this.gameMaster.getPlayer().PlusBet(bet);
            playerEntity.setCredit(playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) - bet);
            playerEntityDAO.save(playerEntity);

            this.gameMaster.getAi().plusBet(bet);
            aiCreditAmount = aiCreditAmount - bet;
            Bets.setText("" + bet * 2);

            myCredit.setText("" + playerEntityDAO.findPlayersCredit(playerEntity.getMyname()));
            aiCredit.setText("" + aiCreditAmount);
        }
        }

    }

    private void HintPlayerHand() {
        while (true) {

            if (playerPosition == 2) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img13);

                ++playerPosition;
                break;
            }

            if (playerPosition == 3) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img14);

                ++playerPosition;
                break;
            }

            if (playerPosition == 4) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img15);

                ++playerPosition;
                break;
            }

            if (playerPosition == 5) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img16);

                ++playerPosition;
                break;
            }

            if (playerPosition == 6) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img17);

                ++playerPosition;
                break;
            }

            if (playerPosition == 7) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(playerPosition, img18);

                ++playerPosition;
                break;
            }
        }
    }

    private void HintAiHand() {

        if(endofgame){
            Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[0] + ".png").toString());
            img01.setImage(image1);

            Image image2 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[1] + ".png").toString());
            img02.setImage(image2);
        }

        if(this.gameMaster.getAi().AiTurn()){
            if(this.gameMaster.getAi().AiHint()){

                    if (aiPosition == 2) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                        img03.setImage(image1);
                        ++aiPosition;
                    }

                    if (aiPosition == 3 && this.gameMaster.getAi().AiHint()) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                        img04.setImage(image1);
                        ++aiPosition;
                    }

                    if (aiPosition == 4 && this.gameMaster.getAi().AiHint()) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                        img05.setImage(image1);
                        ++aiPosition;
                    }

                    if (aiPosition == 5 && this.gameMaster.getAi().AiHint()) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png");
                        img06.setImage(image1);
                        ++aiPosition;
                    }

                    if (aiPosition == 6 && this.gameMaster.getAi().AiHint()) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                        img07.setImage(image1);
                        ++aiPosition;
                    }

                    if (aiPosition == 7 && this.gameMaster.getAi().AiHint()) {
                        this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                        img08.setImage(image1);
                    }
                }
            }
        }

    private void InitializePlayerHand() {

        if (playerPosition == 0) {
            this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

            setPlayerCardIm(playerPosition, img11);

            ++playerPosition;
        }

        if (playerPosition == 1) {
            this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

            setPlayerCardIm(playerPosition, img12);

            ++playerPosition;
        }
    }


    private void InitializeAiHand() {

        if (aiPosition == 0) {
            this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

            if(!endofgame){
                Image image1 = new Image("pictures/backGround.png");
                img01.setImage(image1);
            }
            ++aiPosition;
        }

        if (aiPosition == 1) {
            this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

            if(!endofgame){
                Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[aiPosition] + ".png").toString());
                img02.setImage(image1);
            }
            ++aiPosition;
        }
    }

    private void setPlayerCardIm(int position, ImageView imageView){
        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getPlayer().getHand()[position] + ".png").toString());
        imageView.setImage(image1);
        myScore.setText("" + this.gameMaster.getHandValue(this.gameMaster.getPlayer().getHand()));
    }

    private void whoWon(){
        if (playerEntityDAO.findPlayersCredit(playerEntity.getMyname()) == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setHeaderText("You lost!");
            alert.setContentText("Better luck next time!:)");
            alert.showAndWait();
            HintButton.setDisable(true);
            ConcedeButton.setDisable(false);
            PassButton.setDisable(true);
            reMatch.setVisible(true);
        }

        if (aiCreditAmount == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setHeaderText("You WON!!!");
            alert.setContentText("Your friends are so proud of you!:)");
            alert.showAndWait();
            HintButton.setDisable(true);
            ConcedeButton.setDisable(false);
            PassButton.setDisable(true);
            reMatch.setVisible(true);
        }
    }
}


