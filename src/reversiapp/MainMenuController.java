package reversiapp;

import gameOperation.JavaGame;
import gameOperation.JavaGraphics;
import gameOperation.JavaLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by dan on 1/17/18.
 */
public class MainMenuController implements Initializable {

    @FXML
    HBox root;
    @FXML
    Button quitButton;
    @FXML
    Button settingsButton;
    @FXML
    Button playGameButton;


    @Override
    /**
     * set the root and set button behavior
     */
    public void initialize(URL location, ResourceBundle resources) {
        root.setId("main_menu");
        setButtonBehaviour();
    }

    @FXML
    /**
     * open the settings scene
     */
    private void openSettingsMenu() {
        Stage window = new Stage();
        try {
            HBox set = (HBox)FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
            Scene settings = new Scene(set,800,800);

            //Main Menu Buttons
            window.setTitle("Settings");
            window.setScene(settings);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * sets button behavior
     */
    private void setButtonBehaviour() {
        playGameButton.setOnAction(e->
            startGame());

        quitButton.setOnAction(e->
            System.exit(0));
    }

    /**
     * creates the scene
     */
    private void startGame() {
        Stage window = (Stage) root.getScene().getWindow();
        try {
            GameWindowController root = new GameWindowController();
            createGame(root);
            Scene game = new Scene(root,800,600);

            //Main Menu Buttons
            window.setTitle("Welcome to Reversi");
            window.setScene(game);
            window.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initiates game
     * @param gwc
     */
    public void createGame(GameWindowController gwc) {
        JavaGraphics graphics = new JavaGraphics();
        graphics.getGameWindow(gwc);
        int size;
        try {
            size = getSizeFromFile(new File("settings.txt"));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        JavaLogic logic = new JavaLogic(graphics, size, size);
        JavaGame game = new JavaGame(graphics, logic);
        gwc.setLogic(logic);
        game.start();
    }

    /**
     * Gets board size from file
     * @param fileName
     * @return
     * @throws IOException
     */
    private int getSizeFromFile(File fileName) throws IOException {
        String stringBuffer = "";
        try {
            if (fileName.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                stringBuffer = (bufferedReader.readLine());
                bufferedReader.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(stringBuffer);
    }
}
