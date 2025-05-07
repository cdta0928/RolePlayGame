package main;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable {

    // SCRREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // Scale the tile size to 48x48

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // 16 tiles across the screen
    public final int maxScreenRow = 12; // 12 tiles down the screen
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    Thread gameThread; // Thread for the game loop

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // Enable double buffering for smoother graphics
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread
    }

    @Override
    public void run() {
    }
    
}