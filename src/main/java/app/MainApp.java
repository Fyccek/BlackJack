package app;

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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import dao.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainApp extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class.getName());

    private static final DBManager DB_MANAGER = DBManager.getDpInstance();

    @Override
    public void start(final Stage stage) throws IOException {

        FXMLLoader fxmlLoader
                = new FXMLLoader(getClass().getClassLoader()
                        .getResource("fxml/MainMenu.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("BlackJack");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(final String... args) {

        try {
            DB_MANAGER.connectDB();

            launch(args);
        } catch (Exception e) {
            LOGGER.error("Connection error..");
        } finally {
            DB_MANAGER.disconnectDB();

        }
    }
}


