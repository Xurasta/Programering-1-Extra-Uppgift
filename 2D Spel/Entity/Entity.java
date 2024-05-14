package Entity; // Creates the Entity Package

import java.awt.image.BufferedImage; // Imports BufferedImages

public class Entity { // A method called Entity
    public int x, y; // Creates a x and y coordinate
    public int speed; // Creates a speed

    public BufferedImage up0, down0, down1, down2, left0, right0; // Creates one name for every frame the player will use
    public String direction; // Creates a variable for the direction the player is heading
}
