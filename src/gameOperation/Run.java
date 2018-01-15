package gameOperation;

public class Run {

    public static void main(String[] args) {
        ConsoleGraphics cg = new ConsoleGraphics();
        GraphicInterface gi = (ConsoleGraphics) cg;
        Game myGame = new Game(gi);
        myGame.start();
    }
}
