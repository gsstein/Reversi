package gameOperation;

import java.util.Scanner;


public class Game {
    private GraphicInterface graphicProvider;
    private StandardLogic standardLogic;
    private Scanner scanner = new Scanner(System.in);

    public Game(GraphicInterface gi) {
        graphicProvider = gi;
    }

    public Game(GraphicInterface gi, StandardLogic sl) {
        graphicProvider = gi;
        standardLogic = sl;
    }

    public void start() {
        boolean validInput = false;
        boolean gameLoop = true;
        this.graphicProvider.displayMessage("Welcome to Reversi!\n");
        while(gameLoop) {
            //Prints the greetings for the menu
            this.graphicProvider.displayMessage("(1)Play against a friend\n");
            this.graphicProvider.displayMessage("(2)Quit\n");
            int option = scanner.nextInt();

            if(option == 1) {
                StandardLogic sl = new StandardLogic(graphicProvider);
                GameLogicInterface gl = sl;
                HumanPlayer p1 = new HumanPlayer(gl, graphicProvider, 'X');
                Player hp1 = p1;
                HumanPlayer p2 = new HumanPlayer(gl, graphicProvider, 'O');
                Player hp2 = p2;
                this.playOneMatch(gl, hp1, hp2);
            } else if(option == 2) {
                gameLoop = false;
            } else {
                this.graphicProvider.displayMessage("Invalid option\n");
            }
        }
    }

    public void playOneMatch(GameLogicInterface gl, Player p1, Player p2) {
        gl.startGame(p1, p2);
        do {
            gl.playNextTurn();
        } while(!gl.gameEnded());
        gl.endGame();
    }

    public void setGraphicProvider(GraphicInterface graphicProvider) {
        this.graphicProvider = graphicProvider;
    }
}
