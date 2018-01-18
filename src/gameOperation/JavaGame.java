package gameOperation;



public class JavaGame extends Game {
    private JavaLogic javaLogic;
    private JavaGraphics javaGraphics;

    public JavaGame(JavaGraphics jg, JavaLogic jl) {
        super(jg, jl);
        init(jg, jl);
    }

    public void init(JavaGraphics g, JavaLogic l) {
        javaLogic = l;
        javaGraphics = g;
    }

    @Override
    public void start() {
        HumanPlayer p1 = new HumanPlayer(javaLogic, javaGraphics, 'X');
        HumanPlayer p2 = new HumanPlayer(javaLogic, javaGraphics, 'O');
        javaLogic.startGame(p1, p2);
        javaLogic.playNextTurn();
    }
}
