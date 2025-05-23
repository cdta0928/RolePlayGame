package main;

public class UI {
    
    GamePanel gp;
    java.awt.Graphics2D g2;

    java.awt.Font arial_40, arial_80B;

    java.awt.image.BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;

    public boolean messageOn = false;
    java.util.ArrayList<String> message = new java.util.ArrayList<>();
    java.util.ArrayList<Integer> messageCounter = new java.util.ArrayList<>();
    public String currentDialogue = "";

    public boolean gameFinished = false;
    public int commandNum = 0;

    public int slotCol;
    public int slotRow;

    public UI(GamePanel gp) {
        this.gp = gp;   

        arial_40 = new java.awt.Font("Arial", java.awt.Font.PLAIN, 40);
        arial_80B = new java.awt.Font("Arial", java.awt.Font.BOLD, 80);

        // CREATE HUD OBJECT
        entity.Entity heart = new object.OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        entity.Entity crystal = new object.OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage() {
        int messageX  = gp.tileSize;
        int messageY = gp.tileSize*3;
        g2.setFont(g2.getFont().deriveFont(java.awt.Font.BOLD, 22F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(java.awt.Color.black);
                g2.drawString(message.get(i),  messageX + 2, messageY + 2);
                g2.setColor(java.awt.Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // set the counter to the arr

                messageY += 50;
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(java.awt.Color.white);

        // TITLE SCREEN
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            //...
            drawPlayerLife();
            drawMessage();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
        // OPTION STATE
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
    }

    public void drawOptionScreen() {
        g2.setColor(java.awt.Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));
        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    }

    public void drawInventory() {
        // FRAME (20 slot)
        int frameX = gp.tileSize*12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight  = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // DRAW ITEMS
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            // EQUIP CURSOR
            if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
                gp.player.inventory.get(i) == gp.player.currentShield) {
                    g2.setColor(new java.awt.Color(240, 190, 90));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (i % 5 == 4) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
        
        // CURSOR
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // DRAW CURSOR
        g2.setColor(java.awt.Color.white);
        g2.setStroke(new java.awt.BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESCRIPTION WINDOW
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        
        // DRAW TEXT DESCRIPTION
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(24F));
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line:gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + slotRow*5;
        return itemIndex;
    }

    public void drawPauseScreen() {
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = tailX - length;
        return x;
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(java.awt.Font.PLAIN, 25F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line:currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        java.awt.Color c = new java.awt.Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new java.awt.Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new java.awt.BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawTitleScreen() {
        g2.setColor(new java.awt.Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(java.awt.Font.BOLD, 76F));
        String text = "Blue Boy Adventure";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;

        // SHADOW
        g2.setColor(java.awt.Color.gray);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(java.awt.Color.white);
        g2.drawString(text, x, y); 

        // BLUE BOY IMAGE
        x = gp.screenWidth/2 - gp.tileSize;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
        
        // MENU
        g2.setFont(g2.getFont().deriveFont(java.awt.Font.BOLD, 48F));
        text = "NEW  GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize*3.5;
        g2.setColor(java.awt.Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(java.awt.Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(java.awt.Color.black);
            g2.drawString(">", x - gp.tileSize + 5, y + 5);
            g2.setColor(java.awt.Color.white);
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(java.awt.Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(java.awt.Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(java.awt.Color.black);
            g2.drawString(">", x - gp.tileSize + 5, y + 5);
            g2.setColor(java.awt.Color.white);
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(java.awt.Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(java.awt.Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(java.awt.Color.black);
            g2.drawString(">", x - gp.tileSize + 5, y + 5);
            g2.setColor(java.awt.Color.white);
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPlayerLife() {
        // gp.player.life = 5;
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW BLANK HEART
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURR LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
        
        // DRAW FULL MANA
        x = gp.tileSize/2 - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // DRAW MANA
        x = gp.tileSize/2 - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*6;
        final int frameHeight = gp.tileSize*10 - 40;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(java.awt.Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Shield", textX, textY);

        // VALUES
        int tailX = frameX + frameWidth - 30;
        textY = frameY + gp.tileSize;
        String value;
        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);

        textY += gp.tileSize;
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 45, null);

        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 50, null);
    }
}
