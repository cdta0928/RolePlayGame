package main;

public class Main {
    public static javax.swing.JFrame window;
    public static void main(String[] args) {
        window = new javax.swing.JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        window.setUndecorated(true);
        
        GamePanel gamePanel = new GamePanel(); 
        window.add(gamePanel); 

        window.pack(); 

        window.setLocationRelativeTo(null); 
        window.setVisible(true);
        
        gamePanel.setUpGame();
        gamePanel.startGameThread(); 
    }
}