package reversiapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by gavriella on 1/16/18.
 */

public class SettingsMenuController extends HBox implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private RadioButton x4, x6, x8, x10, x12, x14, x16, x18, x20;
    @FXML
    private RadioButton black_white, red_blue, star_wars, purple_yellow, pink_blue, science1, science2;
    @FXML
    private ToggleGroup colorToggleGroup, boardSizeToggleGroup, playerToggleGroup;
    @FXML
    private RadioButton inverseOrder, standardOrder;
    @FXML
    private Button saveButton;

    //Settings - default values
    private int playerOrder = 1;
    private Pair<String,String> playerTokens = new Pair<String,String>("images/blackPiece.png","images/whitePiece.png");
    private int boardSize = 8;
    private HashMap<String, Pair<String,String>> pairToToken = new HashMap<>();

    private void setSizeButtons() {
        x4.setUserData(4);
        x6.setUserData(6);
        x8.setUserData(8);
        x10.setUserData(10);
        x12.setUserData(12);
        x14.setUserData(14);
        x16.setUserData(16);
        x18.setUserData(18);
        x20.setUserData(20);
        boardSizeToggleGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> obsValue, Toggle oldToggle, Toggle newToggle) {
                if (boardSizeToggleGroup.getSelectedToggle() != null) {
                    boardSize = (int) boardSizeToggleGroup.getSelectedToggle().getUserData();
                }
            }
        });
    }

    private void  setTokenButtons() {
        black_white.setUserData("black and white");
        pairToToken.put((String)black_white.getUserData(), new Pair<String,String>("images/blackPiece.png", "images/whitePiece.png"));
        red_blue.setUserData("red and blue");
        pairToToken.put((String)red_blue.getUserData(), new Pair<String,String>("images/redPiece.png", "images/bluePiece.png"));
        star_wars.setUserData("starwars");
        pairToToken.put((String)star_wars.getUserData(), new Pair<String,String>("images/psych_dvader.png", "images/psych_strooper.png"));
        purple_yellow.setUserData("purple and yelow");
        pairToToken.put((String)purple_yellow.getUserData(), new Pair<String,String>("images/purplePiece.png", "images/yellowPiece.png"));
        pink_blue.setUserData("pink and blue");
        pairToToken.put((String)pink_blue.getUserData(), new Pair<String,String>("images/pinkPiece.png", "images/darkBluePiece.png"));
        science1.setUserData("science1");
        pairToToken.put((String)science1.getUserData(), new Pair<String,String>("images/einstein.png", "images/currie.png"));
        science2.setUserData("science2");
        pairToToken.put((String)science2.getUserData(), new Pair<String,String>("images/ada.png", "images/darwin.png"));

        colorToggleGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> obsValue, Toggle oldToggle, Toggle newToggle) {
                if (colorToggleGroup.getSelectedToggle() != null) {
                    playerTokens = pairToToken.get(
                            colorToggleGroup.getSelectedToggle().getUserData().toString());
                }
            }
        });
    }
    private void  setPlayerOrderButtons() {
        standardOrder.setUserData(1);
        inverseOrder.setUserData(2);
        playerToggleGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> obsValue, Toggle oldToggle, Toggle newToggle) {
                if (playerToggleGroup.getSelectedToggle() != null) {
                    playerOrder = (int) playerToggleGroup.getSelectedToggle().getUserData();
                }
            }
        });
    }

    public void writeSettings(File fileName) {
        try {
            OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(fileName));
            BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
            bufferedWriter.write(Integer.toString(this.boardSize));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(this.playerOrder));
            bufferedWriter.newLine();
            bufferedWriter.write(playerTokens.getKey());
            bufferedWriter.newLine();
            bufferedWriter.write(playerTokens.getValue());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set buttons values
        setSizeButtons();
        setTokenButtons();
        setPlayerOrderButtons();
        saveButton.setOnAction(e-> {
            this.writeSettings(new File("settings.txt"));
            Stage thisStage = (Stage)this.getScene().getWindow();
            thisStage.close();
        });

    }
}
