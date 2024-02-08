package Models;

public class Player {

    // Fields
    String name; // Player's name
    int[] position; // Player's position on the board
    boolean isAlive; // Player's status (alive or dead)
    String number; // Corresponding number on the board

    // Constructor
    public Player(String name, int[] position, String number) {
        this.name = name; // Initialize name
        this.position = position; // Initialize position
        this.isAlive = true; // Player is initially alive
        this.number = number; // Initialize number
    }

    // Getters and setters
    public String getName() {
        return name; // Get player's name
    }

    public int[] getPosition() {
        return position; // Get player's position
    }

    public boolean isAlive() {
        return isAlive; // Check if player is alive
    }

    public String getNumber() {
        return number; // Get player's number
    }

    public void setName(String name) {
        this.name = name; // Set player's name
    }

    public void setPosition(int[] position) {
        this.position = position; // Set player's position
    }

    public void setAlive(boolean alive) {
        isAlive = alive; // Set player's status (alive or dead)
    }

    public void setNumber(String number) {
        this.number = number; // Set player's number
    }
}
