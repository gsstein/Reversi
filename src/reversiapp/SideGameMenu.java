package reversiapp;

import gameOperation.Counter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Created by dan on 1/13/18.
 */
public class SideGameMenu extends VBox  {
    //Members
    @FXML
    private Label scoreTitle;
    private Label blackScore;
    private Label whiteScore;
    private Label message;
    private Button quitButton;
    private Button restartButton;
    private Counter scoreKeeper;

    SideGameMenu(Counter counter) {
        scoreKeeper = counter;
        this.getChildren().addAll(scoreTitle, blackScore, whiteScore, message, quitButton, restartButton);
    }

    public void setMessagesDisplay(String message) {
        //Updates the displayed information
        this.message.setText(this.message.getText() + message);
    }
    public void setMessagesDisplay(char message) {
        //Updates the displayed information
        this.message.setText(this.message.getText() + message);
    }

    public void draw() {
        this.updateScores();
        this.getChildren().add(restartButton);

    }

    private void updateScores() {
        int black = scoreKeeper.getBlackScore();
        int white = scoreKeeper.getWhiteScore();
        this.blackScore.setText("Black: " + Integer.toString(black));
        this.whiteScore.setText("White: " + Integer.toString(white));
    }
}
