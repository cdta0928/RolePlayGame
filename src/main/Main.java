package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        
        GamePanel gamePanel = new GamePanel(); // Create an instance of GamePanel
        window.add(gamePanel); // Add the GamePanel to the JFrame

        window.pack(); // Pack the window to fit the preferred size of the GamePanel

        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setVisible(true);
        
    }
}