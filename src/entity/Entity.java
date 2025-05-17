package entity;

import main.GamePanel;

public class Entity {

    GamePanel gp;
    
    public int worldX, worldY;
    public int speed; // Speed of the entity

    public java.awt.image.BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Images for different directions
    public String direction; // Direction of the entity (up, down, left, right)

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Current sprite number (1 or 2)

    public java.awt.Rectangle solidArea = new java.awt.Rectangle(0, 0, 48, 48); // Rectangle for collision detection
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false; // Flag for collision detection

    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gp) {

        this.gp = gp;

    }

    public void setAction() {

    }
    public void speak() {

    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

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

    public void draw(java.awt.Graphics2D g2) {
        java.awt.image.BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    
    }

}
