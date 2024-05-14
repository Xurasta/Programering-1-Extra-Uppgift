package Entity; // Calls the Entity package

// Imports all the stuff needed
import java.awt.Graphics; 
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

// Imports the GamePanel and KeyHandler since we need the inputs and graphics here
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{ // A extension called Player that extends the Entity method from Entity.java
    GamePanel gp; // refrences the GamePanel and KayHandler from the other files
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) { // A Method that will use both the GamePanel and KeyHandler that were previously refrenced
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues(); // Runs this method
        getPlayerImage(); // Runs this method
    }
    public void setDefaultValues() {
        x = (768/2) - 24; // This is too place the player in the middle since the gamepanel is 768 pixels wide but the sprites "middle" is the upper right corner so we subtract with 24 for the offset
        y = (576/2) - 24; // This is too place the player in the middle since the gamepanel is 576 pixels high but the sprites "middle" is the upper right corner so we subtract with 24 for the offset
        speed = 4; // This is the start speed off the player so that if we had added abbilities too give us more speed it would reset
        direction = "down"; // The starting direction we will be facing
    }



    public void getPlayerImage() { // All off the ImageIO.read commands reffrence too different sprites from the folder and links them too the names given in the Entity.java file
        try {
            up0 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Up.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Down_00.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Down_01.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Down_02.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Left.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/res/Player_Sprite_Right.png"));

        }catch(IOException e) { // This checks for errors
            e.printStackTrace(); // This prints out the error
        }
    }
    
    public void update() { // The player.update method that we run in the update method
        if(keyH.upPressed == true) { // All of these checks with help from the KeyHandler if we press any keys and if we do it changes the direction and position off the player
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics g2) { // Creates the draw method that uses the Graphics2D component
        BufferedImage image = null; // Sets the bufferedImage too nothing so that we can change between the different player sprites

        switch(direction) { // switches the sprite based on the players rotation
            case "up":
                image = up0;
                break;
            case "down":
                image = down0;
                break;
            case "left":
                image = left0;
                break;
            case "right":
                image = right0;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // This draws the players sprite based on (rotation, x-coordinate, y-coordinate, width off the sprite, heigth off the sprite, and an observer which I didn't understand but it is for animations if I figured that part out at least)
    }
}

// I was going to try too animate the player sprite aswell but didn't have the time sadly. I worked on this from home aswell which is why I got this far.