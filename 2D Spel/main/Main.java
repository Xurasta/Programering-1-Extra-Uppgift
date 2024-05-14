package main; // Makes it possibe for other .java files too reach the classes from this one

import javax.swing.JFrame; // Imports the JFrame package which handles frames

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // Creates the window that pops up when you start the game
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default close mechanism so you can close it whenever
        window.setResizable(false); // Makes it impossible for the player too change the size of the window since that would make the character and everything else squish and stretch
        window.setTitle("2D Game"); // Names the window too 2D Game

        GamePanel gamePanel = new GamePanel(); // Creates a gamepanel that will be edited in GamePanel.java
        window.add(gamePanel); // adds the gamepanel to the window

        window.pack(); // Makes it be the size that the gamepanel.java tells it to be

        window.setLocationRelativeTo(null); // Makes the window to not preffer a specific place and will therefore just start up in the middle
        window.setVisible(true); // Makes sure that the window and therefore the gamepanel to be visible

        gamePanel.startGameThread(); // Runs a the startGameThread() class from within the GamePanel.java file
    }
}