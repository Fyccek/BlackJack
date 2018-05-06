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

import dao.DBManager;
import dao.PlayerEntity;
import dao.PlayerEntityDAO;
import dao.PlayerEntityDAOImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    public static PlayerEntity playerEntity = new PlayerEntity();

    public static String NEVEM;

    private static final DBManager DB_MANAGER = DBManager.getDpInstance();

    static PlayerEntityDAOImpl playerEntityDAO = PlayerEntityDAOImpl.getPlayerEntityDAOImpl();

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Button ExitButton;

    @FXML
    private Button PlayButton;

    @FXML
    private TextField myName;

    @FXML
    private Button submitButton;

    @FXML
    private Label nameLabel;

    private final Integer BASE_CREDIT = 5000;


    @FXML
    public void letsStart(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainFXml.fxml"));
        MainPane.getChildren().setAll(pane);
    }

    public void getOff(ActionEvent event) {
        Platform.exit();
    }

    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    public void submitButtonAction(ActionEvent actionEvent) {
        NEVEM = myName.getText();
        playerEntity = playerEntityDAO.findPlayerbyName(NEVEM);

        if(playerEntity == null) {

            playerEntity = new PlayerEntity();
            playerEntity.setmyName(NEVEM);
            playerEntity.setCredit(5000);
        }

        PlayButton.setDisable(false);
        submitButton.setVisible(false);
        myName.setVisible(false);
        nameLabel.setVisible(false);
        playerEntityDAO.save(playerEntity);

    }
}
