package reversiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;


public class SidePanelController extends VBox {

    @FXML
    private Label playerOneScore, playerTwoScore, messageLabel;
    @FXML
    private ImageView playerOneToken, playerTwoToken;
    @FXML
    private Button quitButton;

    /**
     * Loads the fxml and sets the quit button on action to exit
     * @param token1
     * @param token2
     */
    public SidePanelController(Image token1, Image token2) {
        playerOneToken = new ImageView(token1);
        playerOneToken.setFitHeight(50);
        playerOneToken.setFitWidth(50);
        playerTwoToken = new ImageView(token2);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SidePanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        quitButton.setOnAction(e->System.exit(0));
    }

    /**
     * Sets the message lable to param text
     * @param text
     */
    public void setMessage(String text) {
        this.messageLabel.setText(text);
    }

    /**
     * Sets player one's score to param score
     * @param score
     */
    public void setPlayerOneScore(int score) {
        this.playerOneScore.setText(Integer.toString(score));
    }

    /**
     * Sets player two's score to param score
     * @param score
     */
    public void setPlayerTwoScore(int score) {
       this.playerTwoScore.setText(Integer.toString(score));
    }
}
