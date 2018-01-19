package reversiapp;


import gameOperation.Board;
import gameOperation.Cell;
import gameOperation.JavaLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class GameWindowController extends BorderPane {

    private BoardController gameBoard;
    private SidePanelController sidePanel;
    private JavaLogic javaLogic;
    private Image firstPlayerToken, secondPlayerToken;
    private int rowsAndColumnSize;

    /**
     * Loads fxml and initiates side panel controller and board controller
     */
    public GameWindowController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        try {
            getSettingsFromFile(new File("settings.txt"));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        gameBoard = new BoardController(firstPlayerToken, secondPlayerToken, rowsAndColumnSize);
        sidePanel = new SidePanelController(firstPlayerToken, secondPlayerToken);
        gameBoard.setPrefWidth(600);
        gameBoard.setPrefHeight(600);
        this.setRight(sidePanel); //Sets the VBox to the right
        this.setCenter(gameBoard); //Sets the gridPane to the center

        handleResize();
    }

    /**
     * To allow smooth resizing
     */
    private void handleResize() {
        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            gameBoard.setPrefWidth(boardNewWidth);
            gameBoard.draw();
        });

        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            gameBoard.setPrefHeight(newValue.doubleValue());
            gameBoard.draw();
        });

        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            sidePanel.setPrefWidth(boardNewWidth);
        });

        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            sidePanel.setPrefHeight(newValue.doubleValue());
        });
    }

    /**
     * Reads the settings file and assigns member setting variables
     * Sets default settings if the file is empty
     * @param fileName
     * @throws IOException
     */
    private void getSettingsFromFile(File fileName) throws IOException {
        ArrayList<String> stringBuffer = new ArrayList<String>();
        try {
            if (fileName.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.add(line);
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (stringBuffer.isEmpty()) { //default values
            rowsAndColumnSize = 8;
            firstPlayerToken =  new Image(getClass().getResource("images/blackPiece.png").toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource("images/whitePiece.png").toExternalForm());
            return;
        }
        //From the settings file
        rowsAndColumnSize = Integer.parseInt(stringBuffer.get(0));
        int playerOrder = Integer.parseInt(stringBuffer.get(1));
        if (playerOrder == 1) { //Regular order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(2)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(3)).toExternalForm());
        } else { //Inverse order
            firstPlayerToken =  new Image(getClass().getResource(stringBuffer.get(3)).toExternalForm());
            secondPlayerToken =  new Image(getClass().getResource(stringBuffer.get(2)).toExternalForm());
        }
    }

    /**
     * Receives the game JavaLogic and passes it to the BoardControl object
     * @param logic
     */
    public void setLogic(JavaLogic logic) {
        gameBoard.setLogic(logic);
        javaLogic = logic;
    }


    /**
     * Tells board control to display the board
     * Updates moves
     * @param board
     * @param availablePositions
     */
    public void displayBoard(Board board, ArrayList<Cell> availablePositions) {
        gameBoard.displayBoard(board, availablePositions);
        updateScores();
    }

    public void displayPlayer(char player) {
        sidePanel.setMessageToken(player);
    }

    /**
     * Updates record of player scores
     */
    public void updateScores() {
        setPlayerOneScore(javaLogic.getPlayerOneScore());
        setPlayerTwoScore(javaLogic.getPlayerTwoScore());
    }

    /**
     * Makes side panel update its message
     * @param msg
     */
    public void showMessage(String msg) {
        sidePanel.setMessage(msg);
    }

    /**
     * Makes side panel update player one's score
     * @param score
     */
    public void setPlayerOneScore(int score) {
        sidePanel.setPlayerOneScore(score);
    }

    /**
     * Makes side panel update player two's score
     * @param score
     */
    public void setPlayerTwoScore(int score) {
        sidePanel.setPlayerTwoScore(score);
    }
}