package reversiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by dan on 1/18/18.
 */
public class SidePanelController extends VBox {

    @FXML
    private Label playerOneScore;
    @FXML
    private Label playerTwoScore;
    @FXML
    private Label message;
    @FXML
    private Button quitGame;

    public SidePanelController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SidePanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        quitGame.setOnAction(e->System.exit(0));

    }

    public void setMessage(String text) {
        this.message.setText(text);
    }

    public void setPlayerOneScore(int score) {
        this.playerOneScore.setText(Integer.toString(score));
    }

    public void setPlayerTwoScore(int score) {
       this.playerTwoScore.setText(Integer.toString(score));
    }

}
