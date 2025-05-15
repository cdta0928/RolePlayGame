package entity;

public class Entity {
    
    public int worldX, worldY;
    public int speed; // Speed of the entity

    public java.awt.image.BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Images for different directions
    public String direction; // Direction of the entity (up, down, left, right)

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Current sprite number (1 or 2)

    public java.awt.Rectangle solidArea; // Rectangle for collision detection
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false; // Flag for collision detection

}
