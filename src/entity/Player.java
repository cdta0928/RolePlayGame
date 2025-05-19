package entity;

public class Player extends Entity {

    main.KeyHandler keyHandler; // Reference to the KeyHandler for input handling

    // POSITION IN SCREEN
    public final int screenX; // X position on the screen
    public final int screenY; // Y position on the screen

    // INVENTORY
    public int hasKey = 0;

    public Player(main.GamePanel gp, main.KeyHandler keyHandler) {
        super(gp);

        type = 0; // Player
        this.keyHandler = keyHandler; // Initialize the KeyHandler
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Center the player on the screen
        
        // SOLID AREA
        solidArea = new java.awt.Rectangle(8,16, 32, 32); 
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // ATTACK
        attackArea = new java.awt.Rectangle(0, 0, 36, 36);

        setDefaultValues(); // Set default values for the player
        getPlayerImage(); // Load player images
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; // Set default X position
        worldY = gp.tileSize * 21; // Set default Y position
        speed = 4; // Set default speed
        direction = "down"; // Set default direction

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
            up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
            up2 = setup("/res/player/boy_up_2", gp.tileSize, gp.tileSize);
            down1 = setup("/res/player/boy_down_1", gp.tileSize, gp.tileSize);
            down2 = setup("/res/player/boy_down_2", gp.tileSize, gp.tileSize);
            left1 = setup("/res/player/boy_left_1", gp.tileSize, gp.tileSize);
            left2 = setup("/res/player/boy_left_2", gp.tileSize, gp.tileSize);
            right1 = setup("/res/player/boy_right_1", gp.tileSize, gp.tileSize);
            right2 = setup("/res/player/boy_right_2", gp.tileSize, gp.tileSize);

            attackUp1 = setup("/res/player/attack/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/player/attack/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/player/attack/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/attack/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/player/attack/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/player/attack/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/player/attack/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/attack/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    public void update() {
        if (attacking == true) {
            attacking();
        }
        else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed
            || keyHandler.enterPressed) { // If any movement key is pressed
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

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            keyHandler.enterPressed = false;

            if (collisionOn == false && keyHandler.enterPressed == false) {
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

            keyHandler.enterPressed = false;

            spriteCounter++; // Increment sprite counter for animation
            if (spriteCounter > 10) { // If sprite counter exceeds threshold
                if (spriteNum == 1) { // If current sprite is 1
                    spriteNum = 2; // Switch to sprite 2
                } else if (spriteNum == 2) { // If current sprite is 2
                    spriteNum = 1; // Switch to sprite 1
                }
                spriteCounter = 0; // Reset sprite counter
            }

            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        } 
    }

    public void pickupObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key !");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        hasKey--;
                        gp.obj[i] = null;
                        gp.ui.showMessage("You opened the door !");
                    }
                    else gp.ui.showMessage("You need the key !");
                    break;
                case "Boots":
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up !");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if (gp.keyHandler.enterPressed == true) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else attacking = true;
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        // g2.setColor(java.awt.Color.white); // Set color for the player
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // Draw the player as a rectangle
        java.awt.image.BufferedImage image = null; // Placeholder for the player image
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) { // Determine which image to use based on direction
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) { image = up1; } 
                    else if (spriteNum == 2) { image = up2; }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) { image = attackUp1; } 
                    else if (spriteNum == 2) { image = attackUp2; }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) { image = down1; } 
                    else if (spriteNum == 2) { image = down2; }    
                }
                if (attacking == true) {
                    if (spriteNum == 1) { image = attackDown1; } 
                    else if (spriteNum == 2) { image = attackDown2; }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) { image = left1; } 
                    else if (spriteNum == 2) { image = left2; }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize; 
                    if (spriteNum == 1) { image = attackLeft1; } 
                    else if (spriteNum == 2) { image = attackLeft2; }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) { image = right1; } 
                    else if (spriteNum == 2) { image = right2; }
                }
                if (attacking == true) {
                    if (spriteNum == 1) { image = attackRight1; } 
                    else if (spriteNum == 2) { image = attackRight2; }
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null); // Draw the player image
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));
        g2.setColor(java.awt.Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                life--;
                invincible = true;
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            
            // SAVE CURRENT STATUS
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // ATTACK AREA
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            // ATTACK AREA -> SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // CHECK MONSTER AFTER UPDATE SOLID AREA
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height  = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    
    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0) {
                    gp.monster[i] = null;
                }
            }
        }
        else System.out.println("Miss!");
    }

}
