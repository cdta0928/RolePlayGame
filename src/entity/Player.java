package entity;

public class Player extends Entity {

    main.KeyHandler keyHandler; 

    // POSITION IN SCREEN
    public final int screenX; 
    public final int screenY;
    int standCounter = 0;

    public boolean attackCanceled = false;

    // INVENTORY
    public int hasKey = 0;

    public Player(main.GamePanel gp, main.KeyHandler keyHandler) {
        super(gp);

        type = 0; 
        this.keyHandler = keyHandler; 
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); 
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        
        // SOLID AREA
        solidArea = new java.awt.Rectangle(8,16, 32, 32); 
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // ATTACK AREA
        // attackArea = new java.awt.Rectangle(0, 0, 36, 36);

        setDefaultValues(); 
        getPlayerImage(); 
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 12; 
        worldY = gp.tileSize * 12; 
        worldX = gp.tileSize * 23; 
        worldY = gp.tileSize * 21; 
        gp.currentMap = 0; 
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down"; 

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        currentWeapon = new object.OBJ_Sword_Normal(gp);
        // currentWeapon = new object.OBJ_Axe(gp);
        currentShield = new object.OBJ_Shield_Wood(gp);
        projectile = new object.OBJ_Fireball(gp);
        // projectile = new object.OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23; 
        worldY = gp.tileSize * 21; 
        direction = "down";
    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new object.OBJ_Key(gp));
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if (itemIndex < inventory.size()) {
            entity.Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                if (selectedItem.use(this) == true) { inventory.remove(itemIndex); }
            }
        }
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void knockBack(Entity entity, int knockBackPower) {
        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;
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

            if (currentWeapon.type == type_sword) {
                attackUp1 = setup("/res/player/attack/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
                attackUp2 = setup("/res/player/attack/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
                attackDown1 = setup("/res/player/attack/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
                attackDown2 = setup("/res/player/attack/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
                attackLeft1 = setup("/res/player/attack/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
                attackLeft2 = setup("/res/player/attack/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
                attackRight1 = setup("/res/player/attack/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
                attackRight2 = setup("/res/player/attack/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
            }
            else if (currentWeapon.type == type_axe) {
                attackUp1 = setup("/res/player/attack/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
                attackUp2 = setup("/res/player/attack/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
                attackDown1 = setup("/res/player/attack/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
                attackDown2 = setup("/res/player/attack/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
                attackLeft1 = setup("/res/player/attack/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
                attackLeft2 = setup("/res/player/attack/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
                attackRight1 = setup("/res/player/attack/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
                attackRight2 = setup("/res/player/attack/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
            }
    }

    public void update() {
        // System.out.println(worldX/gp.tileSize + " " + worldY/gp.tileSize);
        if (attacking == true) {
            attacking();
        }
        else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed
            || keyHandler.enterPressed) { 
            if (keyHandler.upPressed) { direction = "up"; }
            if (keyHandler.downPressed) { direction = "down"; }
            if (keyHandler.leftPressed) { direction = "left"; }
            if (keyHandler.rightPressed) { direction = "right"; }

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

            // CHECK INTERACTIVE TILE COLLISION
            gp.cChecker.checkEntity(this, gp.iTile);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            if (collisionOn == false && keyHandler.enterPressed == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if (keyHandler.enterPressed == true && attackCanceled == false) {
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            keyHandler.enterPressed = false;

            spriteCounter++; 
            if (spriteCounter > 10) { 
                if (spriteNum == 1) spriteNum = 2; else spriteNum = 1; 
                spriteCounter = 0; 
            }
        } 
        else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (gp.keyHandler.shotKeyPressed == true && projectile.alive == false 
            && shotAvailableCounter == 60 && projectile.haveResource(this) == true) {
            // SET DEFAULT COORDINATES, DIRECTION, USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT
            projectile.subtractResource(this);

            // CHECK VACANCY
            for (int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 60) {
            shotAvailableCounter++;
        }

        if (life > maxLife) { life = maxLife; }
        if (mana > maxMana) { mana = maxMana; }
        if (life <= 0) { gp.gameState = gp.gameOverState; gp.ui.commandNum = -1; }
    }

    public void pickupObject(int i) {
        if (i != 999) {
            // PICKUP ONLY ITEM
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if (keyHandler.enterPressed == true) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            // INVENTORY ITEM
            else {
                String text = "";
                if (inventory.size() != maxInventorySize) {
                    inventory.add(gp.obj[gp.currentMap][i]);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                }
                else {
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactNPC(int i) {
        if (gp.keyHandler.enterPressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        java.awt.image.BufferedImage image = null; 
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
        if (invincible == true) {
            changeAlpha(g2, 0.4f);
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null); 
        changeAlpha(g2, 1f);
        g2.setColor(java.awt.Color.RED);

        // HITBOX
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
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
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // ATTACK AREA -> SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // CHECK MONSTER AFTER UPDATE SOLID AREA
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);

            // CHECK ATTACK INTERACTIVE TILE
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);
            
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

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
            && gp.iTile[gp.currentMap][i].invincible == false) {
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
            if (gp.iTile[gp.currentMap][i].life == 0) { gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm(); }
        }
    }
    
    public void damageMonster(int i, int attack, int knockBackPower) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                if (knockBackPower > 0) knockBack(gp.monster[gp.currentMap][i], knockBackPower);
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying =  true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\nYou feel stronger!";
        }
    }
}
