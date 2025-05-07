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

    // FPS (Frames Per Second) settings
    int FPS = 60; // Target frames per second

    KeyHandler keyHandler = new KeyHandler(); // Key handler for keyboard input
    Thread gameThread; // Thread for the game loop

    // Set player's position
    int playerX = 100; // Player's X position
    int playerY = 100; // Player's Y position
    int playerSpeed = 4; // Player's speed

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); // Enable double buffering for smoother graphics
        this.addKeyListener(keyHandler); // Add the key listener to the panel
        this.setFocusable(true); // Make the panel focusable to receive key events
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // Calculate the draw interval in nanoseconds
        double delta = 0; // Delta time for frame rate control
        long lastTime = System.nanoTime(); // Get the current time in nanoseconds
        long currentTime; // Variable to store the current time
        long timer = 0; // Timer for FPS calculation
        int drawCount = 0; // Count of frames drawn

        while(gameThread != null) { // Main game loop
            
            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            // System.out.println("Game is running..."); // Placeholder for game logic
            delta += (currentTime - lastTime) / drawInterval; // Calculate the delta time
            timer += (currentTime - lastTime); // Update the timer
            lastTime = currentTime; // Update the last time
            if (delta >= 1) { // If enough time has passed
                update(); // Update game state
                repaint(); // Repaint the screen
                delta--; // Decrease delta by 1
                drawCount++; // Increment the draw count
            }
            if (timer >= 1000000000) { // If 1 second has passed
                System.out.println("FPS: " + drawCount); // Print the FPS
                drawCount = 0; // Reset the draw count
                timer = 0; // Reset the timer
            }

        }

    }

    public void update() {
        // Update game logic here (e.g., player movement, enemy AI, etc.)

        if (keyHandler.upPressed) { // If the up key is pressed
            playerY -= playerSpeed; // Move the player up
        }
        if (keyHandler.downPressed) { // If the down key is pressed
            playerY += playerSpeed; // Move the player down
        }
        if (keyHandler.leftPressed) { // If the left key is pressed
            playerX -= playerSpeed; // Move the player left
        }
        if (keyHandler.rightPressed) { // If the right key is pressed
            playerX += playerSpeed; // Move the player right
        }

    }

    @Override
    public void paintComponent(java.awt.Graphics g) {

        super.paintComponent(g); // Call the superclass method to clear the screen
        // Draw game elements here (e.g., tiles, characters, etc.)
        // Example: g.drawImage(image, x, y, tileSize, tileSize, null);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g; // Cast to Graphics2D for advanced drawing 
        g2.setColor(java.awt.Color.WHITE); // Set the color to white
        g2.fillRect(playerX, playerY, tileSize, tileSize); // Fill the screen with white for testing
        g2.dispose(); // Dispose of the Graphics2D object to free resources

    }

}