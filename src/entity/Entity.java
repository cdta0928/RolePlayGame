package entity;

import java.util.Random;

public class Entity {

    main.GamePanel gp;
    
    // IMAGE
    public java.awt.image.BufferedImage image, image2, image3;
    public java.awt.image.BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; 
    public java.awt.image.BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;

    // STATE
    public int worldX, worldY;
    public String direction = "down"; // Direction of the entity (up, down, left, right)
    public int spriteNum = 1; // Current sprite number (1 or 2)
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;

    // SOLID AREA
    public java.awt.Rectangle solidArea = new java.awt.Rectangle(0, 0, 48, 48); // Rectangle for collision detection
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    // COLLISION
    public boolean collision = false; 
    public boolean collisionOn = false; 

    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;

    public java.awt.Rectangle attackArea = new java.awt.Rectangle(0, 0, 0, 0);
    public Entity attacker;

    // COUNTER
    public int spriteCounter = 0; 
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter = 0;

    // CHAR ATTIBUTES
    public String name;
    public int defaultSpeed;
    public int speed; 
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public int ammo;

    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;

    public Projectile projectile;

    // ITEM ATTRIBUTES
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    public java.util.ArrayList<Entity> inventory = new java.util.ArrayList<>();
    public final int maxInventorySize = 20;

    public int useCost;
    public int price;
    public int knockBackPower = 0;

    // TYPE
    public int type; 
    // player   =   0
    // npc      =   1
    // monster  =   2
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;

    public Entity(main.GamePanel gp) { this.gp = gp; }

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

    public void interact() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch (gp.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {
            // NEXT WORLD X / Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // ENTITY SOLID AREA POSITION
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // LEFT OR RIGHT
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // UP OR LEFT
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // UP OR RIGHT
                direction = "up";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // DOWN OR LEFT
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // DOWN OR RIGHT
                direction = "down";
                checkCollision();

                if (collisionOn == true) {
                    direction = "right";
                }
            }

            // IF REACH ThE GOAL, STOP THE SEARCH
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;

            // if (nextCol == goalCol && nextRow == goalRow) {
            //     onPath = false;
            // }
        }
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {

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
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // ATTACK AREA -> SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            }
            else {
                // CHECK MONSTER AFTER UPDATE SOLID AREA
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                // CHECK ATTACK INTERACTIVE TILE
                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }
            
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

    public void checkAttackOrNot(int rate, int straight, int holizontal) {
        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch (direction) {
            case "up" -> {
                if (gp.player.worldY < worldY && yDis < straight && xDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "down" -> {
                if (gp.player.worldY > worldY && yDis < straight && xDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "left" -> {
                if (gp.player.worldX < worldX && xDis < straight && yDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "right" -> {
                if (gp.player.worldX > worldX && xDis < straight && yDis < holizontal) {
                    targetInRange = true;
                }
            }
        }

        if (targetInRange == true) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;
        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        switch (user.direction) {
            case "up": nextWorldY = user.getTopY() - 1; break;
            case "down": nextWorldY = user.getBottomY() + 1; break;
            case "left": nextWorldX = user.getLeftX() - 1; break;
            case "right": nextWorldX = user.getRightX() + 1; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[gp.currentMap].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row &&
                    target[gp.currentMap][i].name.equals(targetName)) {
                        index = i;
                        break;
                    }
            }
        }
        return index;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return worldY + solidArea.y;
    }
    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol() {
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow() {
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getXDistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYDistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXDistance(target) + getYDistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoatCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }
    public int getGoatRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }

    public void dropItem(entity.Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.iTile);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {
        if (knockBack == true) {
            checkCollision();
            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (collisionOn == false) {
                switch (knockBackDirection) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }
        }
        else if (attacking == true) {
            attacking();
        }
        else {
            setAction();
            checkCollision();
            if (collisionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            spriteCounter++; 
            if (spriteCounter > 24) { 
                if (spriteNum == 1) spriteNum = 2; 
                else spriteNum = 1; 
                spriteCounter = 0; 
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 180) {
            shotAvailableCounter++;
        }
    }

    public void dyingAnimation(java.awt.Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) { changeAlpha(g2, 0f); }
        if (dyingCounter > i && dyingCounter <= i*2) { changeAlpha(g2, 1f); }
        if (dyingCounter > i*2 && dyingCounter <= i*3) { changeAlpha(g2, 0f); }
        if (dyingCounter > i*3 && dyingCounter <= i*4) { changeAlpha(g2, 1f); }
        if (dyingCounter > i*4 && dyingCounter <= i*5) { changeAlpha(g2, 0f); }
        if (dyingCounter > i*5 && dyingCounter <= i*6) { changeAlpha(g2, 1f); }
        if (dyingCounter > i*6 && dyingCounter <= i*7) { changeAlpha(g2, 0f); }
        if (dyingCounter > i*7 && dyingCounter <= i*8) { changeAlpha(g2, 1f); }
        if (dyingCounter > i*8) {
            // dying = false;
            alive = false;
        }
    }

    public void changeAlpha(java.awt.Graphics2D g2, float alphaValue) {
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, alphaValue));
    }

    public void damageReaction() {

    }

    public void damagePlayer(int attack) {
            if (gp.player.invincible == false) {
                int damage = attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.player.life -= damage;
                gp.player.invincible = true;
            }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void draw(java.awt.Graphics2D g2) {
        java.awt.image.BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) { 
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
            
            // MONSTER HEALTH BAR
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new java.awt.Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY -16, gp.tileSize + 2, 12);
                g2.setColor(new java.awt.Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarOn = false;
                    hpBarCounter = 0;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if (dying == true) { dyingAnimation(g2); }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);
        }
    }

    public java.awt.Color getParticleColor() {
        java.awt.Color color = null;
        return color;
    }
    
    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }
    
    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {
        java.awt.Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void updateMonster(Entity e, int speed, int attack, int defense, int pSpeed, int exp) {
        this.exp = exp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.projectile.speed = speed;
    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new java.util.Random().nextInt(rate) + 1;
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            for (int ii = 0; ii < gp.projectile[gp.currentMap].length; ii++) {
                if (gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void getRandomDirection() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            java.util.Random random = new java.util.Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) { direction = "up"; }
            if (i > 25 && i <= 50) { direction = "down"; }
            if (i > 50 && i <= 75) { direction = "left"; }
            if (i > 75 && i <= 100) { direction = "right"; }

            actionLockCounter = 0;
        }
    }
}
