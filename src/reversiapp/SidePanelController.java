package reversiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SidePanelController extends VBox implements Initializable {

    @FXML
    private Label playerOneScore, playerTwoScore, messageLabel;
    @FXML
    private Button quitButton;
    @FXML
    private ImageView playerOneToken, playerTwoToken, msgPlayer;
    private Image token1, token2, player;

    public SidePanelController(Image image1, Image image2) {
        token1 = image1;
        token2 = image2;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerOneToken.setImage(token1);
        playerTwoToken.setImage(token2);
    }

    public void setMessage(String text) {
        this.messageLabel.setText(text);
    }

    public void setPlayerOneScore(int score) {
        this.playerOneScore.setText(Integer.toString(score));
    }

    public void setPlayerTwoScore(int score) {
       this.playerTwoScore.setText(Integer.toString(score));
    }

    public void setMessageToken(char player) {
        if(player == 'X') {
            msgPlayer.setImage(token1);
        } else {
            msgPlayer.setImage(token2);
        }
    }


}
