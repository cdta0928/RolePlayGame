package entity;

public class Player extends Entity {

    main.GamePanel gp; // Reference to the GamePanel for game logic
    main.KeyHandler keyHandler; // Reference to the KeyHandler for input handling

    public final int screenX; // X position on the screen
    public final int screenY; // Y position on the screen

    public Player(main.GamePanel gp, main.KeyHandler keyHandler) {
        this.gp = gp; // Initialize the GamePanel reference
        this.keyHandler = keyHandler; // Initialize the KeyHandler
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Center the player on the screen
        
        solidArea = new java.awt.Rectangle(); // Set the solid area for collision detection
        solidArea.x = 8; // Set the X position of the solid area
        solidArea.y = 16; // Set the Y position of the solid area
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32; // Set the width of the solid area
        solidArea.height = 32; // Set the height of the solid area

        setDefaultValues(); // Set default values for the player
        getPlayerImage(); // Load player images
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; // Set default X position
        worldY = gp.tileSize * 21; // Set default Y position
        speed = 4; // Set default speed
        direction = "down"; // Set default direction
    }

    public void getPlayerImage() {
        // Load player images here (up1, up2, down1, down2, left1, left2, right1, right2)
        // This method is not implemented in this snippet
        try {
            up1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace(); // Print stack trace if image loading fails
        }
    }

    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) { // If any movement key is pressed
            if (keyHandler.upPressed) { // If the up key is pressed
                direction = "up"; // Set direction to up
            }
            if (keyHandler.downPressed) { // If the down key is pressed
                direction = "down"; // Set direction to down
            }
            if (keyHandler.leftPressed) { // If the left key is pressed
                direction = "left"; // Set direction to left
            }
            if (keyHandler.rightPressed) { // If the right key is pressed
                direction = "right"; // Set direction to right
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }

            spriteCounter++; // Increment sprite counter for animation
            if (spriteCounter > 10) { // If sprite counter exceeds threshold
                if (spriteNum == 1) { // If current sprite is 1
                    spriteNum = 2; // Switch to sprite 2
                } else if (spriteNum == 2) { // If current sprite is 2
                    spriteNum = 1; // Switch to sprite 1
                }
                spriteCounter = 0; // Reset sprite counter
            }
            
        } 

    }

    public void draw(java.awt.Graphics2D g2) {
        // g2.setColor(java.awt.Color.white); // Set color for the player
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // Draw the player as a rectangle
        
        java.awt.image.BufferedImage image = null; // Placeholder for the player image
        switch (direction) { // Determine which image to use based on direction
            case "up":
                if (spriteNum == 1) {
                    image = up1; // Use the first up image
                } else if (spriteNum == 2) {
                    image = up2; // Use the second up image
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1; // Use the first down image
                } else if (spriteNum == 2) {
                    image = down2; // Use the second down image
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1; // Use the first left image
                } else if (spriteNum == 2) {
                    image = left2; // Use the second left image
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1; // Use the first right image
                } else if (spriteNum == 2) {
                    image = right2; // Use the second right image
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Draw the player image

    }
    
}
