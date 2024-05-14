package main;

import java.awt.Color; // Imports the package Color that's used in the beginning to test the character movement before applying the sprites
import java.awt.Dimension; // Imports the package Dimension that I'm not one hundred percent sure about but think is used to apply the size another time inside the gamepanel so it can be the same size as the window
import java.awt.Graphics; // Imports the package Graphics which is used too handle graphics but not quite sure why you need both this and the 2D one but I think the 2DGraphics relies on some stuff from this package
import java.awt.Graphics2D; // Imports the package Graphics2D which is used too handle 2Dgraphics such as sprites

import javax.swing.JPanel; // Imports the JPanel that helps making a grid for a game

import Entity.Player; // Iports a package that helps with handling a Player Entity

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16 * 16 pixels per tile before muliplying it
    final int scale = 3; // Used the make every sprite bigger too have a good pixel size

    public final int tileSize = originalTileSize * scale; // 48 * 48 pixels per tile
    final int maxScreenCol = 16; // The amount of tiles horizontally
    final int maxScreenRow = 12; // the amount of tiles vertical
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels horizontal
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels vertical

    int FPS = 60; // A home-made framerate too easily make the movement the same speed even if different clients run at different speeds

    KeyHandler keyH = new KeyHandler(); // We refrence the KeyHanlder.java file too be able too know what keys are pressed on the keyboard
    Thread gameThread; // We create a thread which is kind of like a loop that runs over and over and in this specific thread we have put the game so it refreshes every frame
    Player player = new Player(this, keyH); // We use the Entity.Player package to create a player and inside of the parentheses we refrence (this) which is the GamePanel.java file and since we are in it we use (this) as the refrence while the (keyH) refrences the nely made KeyHandler that we later on will use too create the basic movement

    public GamePanel() { // Inside of these brackets we specify the GamePanel that we created inside of the main.java file
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // We set the max width and height of the gamepanel
        this.setBackground(Color.black); // We put the background color too black
        this.setDoubleBuffered(true); // I didn't understand what exactly it does but it is supposed too help with the rendering off the images since they are all painted first in another space and then put into the game as frames. That makes it look much more clean if the rendered place is too complicated too render quickly
        this.addKeyListener(keyH); // Thiis just adds the already made KeyHandler too the gamepanel
        this.setFocusable(true); // This is too make sure that the keyboards input is directed into the gamepanel
    }

    public void startGameThread() { // This is the method that tells the game too run
        gameThread = new Thread(this); // This is the Game that is created as a Thread which is a special kind of "loop" that runs over and over again
        gameThread.start(); // This starts the Game Thread/ The actual game
    }

    public void run() { // This method is what keeps track of what is actually happening in the game
        double drawInterval = 1000000000/FPS; // This is a double that holds one billion divided by the amount of Frames Per Second which is the time it should take between every frame because the time we use too count is in nano seconds for a more percise measure
        double nextDrawTime = System.nanoTime() + drawInterval; // This double is the amount of time that has passed in the gamepanel plus the time it should take

        while(gameThread != null) { // This while loop runs whenever the gameThread is on, with other words whenever you start the game
            update(); // An method called update that checks for player input and renews the position off the player

            repaint(); // A method called repaint that draws the frames on the screen

            try { // A try and catch statement
                double remainingTime = nextDrawTime - System.nanoTime(); // This double is the amount of time that it took too draw the frame plus the amount it SHOULD take minus the time it has taken since the last frame was drawn
                remainingTime = remainingTime/1000000; // This remainingTime is the time that it is left until the next frame should be drawn

                if(remainingTime < 0) { // Checks if the time remaining time has run out
                    remainingTime = 0; // Sets the remaining time too zero when it goes past it for the next time the run method is run
                }

                Thread.sleep((long)remainingTime); // This puts the thread too sleep for a super super short time before waking it upp again which resets the timer for the System.nanoTime()

                nextDrawTime += drawInterval; // This makes nextDrawTime equalls too the previous nextDrawTime plus the drawInterval

            } catch (InterruptedException e) { // This checks for unexpected errors
                e.printStackTrace(); // This prints out errors
            }
        }
    }
    public void update() { // This is the update method
        player.update(); // If I understood it correctly this is the way the player package retries everything in the Player.java
    }
    public void paintComponent(Graphics g) { // This creates a new method that is called paintComponent and it uses the Graphics package from before and the graphics are called g
        super.paintComponent(g); // This uses some UI implements too paint the stated graphics if the method is called

        Graphics2D g2 = (Graphics2D)g; // We create the Graphics 2D out of the previous Graphics component too get everything in a specified 2D gamespace

        player.draw(g2); // This component tells the Entity.java too redraw the entity known as player with the graphics 2D component

        g2.dispose(); // This deletes the recent graphics 2D frame when it switches too a new one
    }
}
