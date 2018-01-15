

public class Cell implements Comparable {
    int xCord, yCord;
    char value;

    public Cell(int x, int y, char v) {
        xCord = x;
        yCord = y;
        value = v;
    }

    public char getValue() {
        return this.value;
    }

    public void setValue(char v) {
        this.value = v;
    }

    public boolean isSameValue(char c) {
        return (c == this.value);
    }


    public void printCell() {
        System.out.print("(" + this.xCord + "," + this.yCord + ")");
    }


    public int getXCord() {
        return this.xCord;
    }


    public int getYCord() {
        return this.yCord;
    }

    public boolean equals(Object compareCell) {
        if(this.xCord == ((Cell)compareCell).getXCord() && this.yCord == ((Cell)compareCell).getYCord()) {
            return true;
        }
        return false;
    }

    public boolean lessThan(Cell compareCell) {
        //If this.x is less, or x is equal and this.y is less
        if(this.xCord < ((Cell)compareCell).getXCord() ||
                (this.xCord == ((Cell)compareCell).getXCord() && this.yCord < ((Cell)compareCell).getYCord())) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object compareCell) {
        if(this.xCord == ((Cell)compareCell).getXCord() && this.yCord == ((Cell)compareCell).getYCord()) {
            return 0;
        }

        //If this.x is less, or x is equal and this.y is less
        if(this.xCord < ((Cell)compareCell).getXCord() ||
                (this.xCord == ((Cell)compareCell).getXCord() && this.yCord < ((Cell)compareCell).getYCord())) {
            return -1;
        }
        return 1;
    }
}
