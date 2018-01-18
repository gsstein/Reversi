package gameOperation;

import java.util.ArrayList;

public interface PlayerInterface {

    boolean equals(Object comparePlayer);

    char getPlayerIdChar();

    boolean lessThan(Player comparePlayer);

    void setPlayerId(char value);

    void outOfPlays();

    ArrayList<Integer> makeMove();

    int getScore();

    void setScore(int s);
}
