package reversiapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by gavriella on 1/16/18.
 */

public class MenuController implements Initializable {
    @FXML
    RadioButton x4, x6, x8, x10, x12, x14, x16, x18, x20;
    @FXML
    RadioButton white_black, red_blue, green_purple;
    @FXML
    ToggleGroup colorToggleGroup, boardSizeToggleGroup;

    HashMap<String, Color> colorMap;



    int boardSize, firstPlayer;
    Color playerOneColor, playerTwoColor;


    public MenuController() {
        boardSize = 8;
        firstPlayer = 1;
        playerOneColor = Color.WHITE;
        playerTwoColor = Color.BLACK;

        colorMap.put("red", Color.RED);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("white", Color.WHITE);
        colorMap.put("black", Color.BLACK);
        colorMap.put("green", Color.GREEN);
        colorMap.put("purple", Color.PURPLE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void play() {

    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    @FXML
    public void assignColors(ActionEvent event) {
        String[] colors = event.toString().split("_");
        playerOneColor = colorMap.get(colors[0]);
        playerTwoColor = colorMap.get(colors[1]);
    }

    @FXML
    public void switchOrder() {
        if(firstPlayer == 1) {
            firstPlayer = 2;
        } else {
            firstPlayer = 1;
        }
    }

    @FXML
    public void setBoardSize(ActionEvent event) {
        String[] s = event.toString().split("x");
        boardSize = Integer.parseInt(s[1]);
    }
}
