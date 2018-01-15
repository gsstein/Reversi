package gameOperation;

import java.util.ArrayList;

abstract class Player implements PlayerInterface {
    char playerIdChar;

    public Player() {

    }

    public boolean equals(Object comparePlayer) {
        return (this.getPlayerIdChar() == ((Player)comparePlayer).getPlayerIdChar());
    }

    public char getPlayerIdChar() {
        return this.playerIdChar;
    }

    public boolean lessThan(Player comparePlayer) {
        return (this.getPlayerIdChar() < comparePlayer.getPlayerIdChar());
    }

    public void setPlayerId(char value) {
        this.playerIdChar = value;
    }

    public abstract void outOfPlays();

    public abstract ArrayList<Integer> makeMove();
}
