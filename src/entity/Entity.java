package entity;

public class Entity {

    main.GamePanel gp;
    
    // IMAGE
    public java.awt.image.BufferedImage image, image2, image3;
    public java.awt.image.BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Images for different directions
    public java.awt.image.BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    
    public boolean collision = false; // Flag for collision detection

    // STATE
    public int worldX, worldY;
    public String direction = "down"; // Direction of the entity (up, down, left, right)
    public int spriteNum = 1; // Current sprite number (1 or 2)
    int dialogueIndex = 0;
    public boolean collisionOn = false; // Flag for collision detection
    public boolean invincible = false;
    String dialogues[] = new String[20];
    boolean attacking = false;

    // SOLID AREA
    public java.awt.Rectangle solidArea = new java.awt.Rectangle(0, 0, 48, 48); // Rectangle for collision detection
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public java.awt.Rectangle attackArea = new java.awt.Rectangle(0, 0, 0, 0);

    // COUNTER
    public int spriteCounter = 0; // Counter for sprite animation
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;

    // CHAR ATTIBUTES
    public String name;
    public int type; 
    // player   =   0
    // npc      =   1
    // monster  =   2
    public int speed; // Speed of the entity
    public int maxLife;
    public int life;

    public Entity(main.GamePanel gp) {
        this.gp = gp;
    }

    public java.awt.image.BufferedImage setup(String imagePath, int width, int height) {
        main.UtilityTool uTool = new main.UtilityTool();
        java.awt.image.BufferedImage image = null;
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void setAction() {

    }
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                gp.player.life--;
                gp.player.invincible = true;
            }
        }

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
