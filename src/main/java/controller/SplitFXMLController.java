package controller;

import dao.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modell.GameMaster;
import modell.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.MainFXMLController.*;
import static java.lang.Integer.parseInt;

public class SplitFXMLController implements Initializable {

    @FXML
    private AnchorPane Split;

    @FXML
    private Button ConcedeButton;

    @FXML
    private Button HintButton;

    @FXML
    private Button PassButton;

    @FXML
    private Button HintButton02;

    @FXML
    private Button PassButton02;

    @FXML
    private Button reMatch;

    @FXML
    private Label aiCredit;

    @FXML
    private Label myCredit;

    @FXML
    private Label myScore;

    @FXML
    private Label aiScore;

    @FXML
    private Label myScore02;

    @FXML
    private Label Bets;

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
    private ImageView img31;

    @FXML
    private ImageView img32;

    @FXML
    private ImageView img33;

    @FXML
    private ImageView img34;

    @FXML
    private ImageView img35;

    @FXML
    private ImageView img36;

    @FXML
    private ImageView img37;

    @FXML
    private ImageView img38;

    private static final DBManager DB_MANAGER = DBManager.getDpInstance();

    GameMaster gameMaster = new GameMaster();

    int playerPosition;
    int player2Position;
    int aiPosition;

    public void initialize(URL location, ResourceBundle resources) {

        ConcedeButton.setVisible(false);
        reMatch.setVisible(false);
        HintButton.setDisable(true);
        PassButton.setDisable(true);

        myCredit.setText("" + (DB_MANAGER.findPlayersCredit(playerEntity.getId()) - betsValue / 2));
        aiCredit.setText("" + (aiCreditAmount - betsValue / 2));

        Bets.setText("" + betsValue * 2);

        InitializeHands();

        myScore.setText("" + myHandsValue);
        myScore02.setText("" + myHandsValue);
    }

    @FXML
    public void reMatchAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainFXml.fxml"));
        Split.getChildren().setAll(pane);
    }

    @FXML
    public void concedeButtonAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane =
                FXMLLoader.load(getClass().getClassLoader().
                        getResource("fxml/MainMenu.fxml"));

        Split.getChildren().setAll(pane);
    }

    @FXML
    public void PassButtonAction02(ActionEvent actionEvent) {
        HintButton02.setDisable(true);
        PassButton02.setDisable(true);
        HintButton.setDisable(false);
        PassButton.setDisable(false);
    }

    @FXML
    public void PassButtonAction(ActionEvent actionEvent) {

        HintAiHand();
        WinnerDeal();
    }

    @FXML
    public void hintButtonAction(ActionEvent actionEvent) {

        HintPlayerHand(this.gameMaster.getPlayer());
        if(parseInt(myScore.getText()) > 21) {

            int bet = parseInt(Bets.getText());
            aiCreditAmount += bet;

            aiCredit.setText(": " + aiCreditAmount);
            Bets.setText("");

            HintButton.setDisable(true);
            PassButton.setDisable(true);
            reMatch.setVisible(true);
            ConcedeButton.setDisable(false);
        }

        WinnerDeal();
    }

    @FXML
    public void hintButtonAction02(ActionEvent actionEvent) {

        HintPlayer2Hand(this.gameMaster.getPlayer2());
        if(parseInt(myScore02.getText()) > 21) {
            HintButton02.setDisable(true);
            PassButton02.setDisable(true);
            HintButton.setDisable(false);
            PassButton.setDisable(false);
        }
    }

    private void setPlayerCardIm(Player player, int position, ImageView imageView){
        if(player == this.gameMaster.getPlayer()) {
            Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getPlayer().getHand()[position] + ".png").toString());
            imageView.setImage(image1);
            myScore.setText("" + (this.gameMaster.getHandValue(this.gameMaster.getPlayer().getHand()) +  myHandsValue));
        } else {
            Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getPlayer2().getHand()[position] + ".png").toString());
            imageView.setImage(image1);
            myScore02.setText("" + (myHandsValue + this.gameMaster.getHandValue(this.gameMaster.getPlayer2().getHand())));
        }
    }

    private void HintPlayerHand(Player player) {
        while (true) {

            if (playerPosition == 0 ) {
                this.gameMaster.getDealer().dealToPlayer(player, playerPosition);

                setPlayerCardIm(player, playerPosition, img12);

                ++playerPosition;
                break;
            }

            if (playerPosition == 1) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img13);

                ++playerPosition;
                break;
            }

            if (playerPosition == 2 ) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img14);

                ++playerPosition;
                break;
            }

            if (playerPosition == 3) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img15);

                ++playerPosition;
                break;
            }

            if (playerPosition == 4) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img16);

                ++playerPosition;
                break;
            }

            if (playerPosition == 5) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img17);

                ++playerPosition;
                break;
            }

            if (playerPosition == 6) {
                this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayer(), playerPosition);

                setPlayerCardIm(player, playerPosition, img18);

                ++playerPosition;
                break;
            }
        }
    }

    private void setAiCardIm(int position, ImageView imageView){
        Image image1 = new Image(getClass().getClassLoader().getResource("pictures/" + this.gameMaster.getAi().getHand()[position] + ".png").toString());
        imageView.setImage(image1);
        aiScore.setText("" + this.gameMaster.getHandValue(this.gameMaster.getAi().getHand()));
    }

    private void HintAiHand() {

                if (aiPosition == 0 && parseInt(aiScore.getText()) < 17) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img03);
                    ++aiPosition;
                }

                if (aiPosition == 1 && parseInt(aiScore.getText()) < 17 ) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img04);
                    ++aiPosition;
                }

                if (aiPosition == 2 && parseInt(aiScore.getText()) < 17) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img05);
                    ++aiPosition;
                }

                if (aiPosition == 3 && parseInt(aiScore.getText()) < 17) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img06);
                    ++aiPosition;
                }

                if (aiPosition == 4 && parseInt(aiScore.getText()) < 17) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img07);
                    ++aiPosition;
                }

                if (aiPosition == 5 && parseInt(aiScore.getText()) < 17) {
                    this.gameMaster.getDealer().dealToAi(this.gameMaster.getAi(), aiPosition);

                    setAiCardIm(aiPosition, img08);
                }
    }

    private void HintPlayer2Hand(Player player) {
        while (true) {

            if (player2Position == 0 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img32);

                ++player2Position;
                break;
            }

            if (player2Position == 1 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img33);

                ++player2Position;
                break;
            }

            if (player2Position == 2 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img34);

                ++player2Position;
                break;
            }

            if (player2Position == 3 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img35);

                ++player2Position;
                break;
            }

            if (player2Position == 4 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img36);

                ++player2Position;
                break;
            }

            if (player2Position == 5 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img37);

                ++player2Position;
                break;
            }

            if (player2Position == 6 ) {
                this.gameMaster.getDealer().dealToPlayer(player, player2Position);

                setPlayerCardIm(player, player2Position, img38);

                ++player2Position;
                break;
            }
        }
    }

    private void WinnerDeal(){
        int bet = parseInt(Bets.getText());

        int myPont = parseInt(myScore.getText());
        int myPont2 = parseInt(myScore02.getText());
        int aiPont = parseInt(aiScore.getText());

        if (myPont > aiPont && myPont < 21) {
            playerEntity.setCredit(DB_MANAGER.findPlayersCredit(playerEntity.getId()) + bet);
            DB_MANAGER.save(playerEntity);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setContentText(":)");
            alert.showAndWait();
        } else {
            if (aiPont == myPont) {
                aiCreditAmount += bet;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RESULT");
                alert.setContentText(":(");
                alert.showAndWait();
            } else {
                aiCreditAmount += bet;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RESULT");
                alert.setContentText(":(");
                alert.showAndWait();
            }
        }
    }

    private void InitializeHands(){
        img01.setImage(imgStaticAi);
        img11.setImage(imgStaticPlayer1);
        img31.setImage(imgStaticPlayer2);
    }

    private void whoWon() {

        if (DB_MANAGER.findPlayersCredit(playerEntity.getId()) == 0) {
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
