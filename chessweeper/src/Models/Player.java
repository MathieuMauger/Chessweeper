package Models;

import java.lang.reflect.Array;

public class Player {

    String name;
    int[] position;
    boolean isAlive;

    //corresponding number on the board
    String number;

    public String getName() {
        return name;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getNumber() {
        return number;
    }

    public Player(String name, int[] position, String number) {
        this.name = name;
        this.position = position;
        this.isAlive = true;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

