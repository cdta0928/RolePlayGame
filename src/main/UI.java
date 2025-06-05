package main;

import javax.imageio.ImageIO;

public class UI {
    
    GamePanel gp;
    java.awt.Graphics2D g2;

    java.awt.Font arial_40, arial_80B;
    java.awt.Font pixelOBold, pixelOBold16F, pixelOBold32F, pixelOBold64F, pixelOBold48F, pixelOBold56F;
    java.awt.Font pixelOBold100F, pixelOBold80F;
    java.awt.Font pixelO;

    java.awt.image.BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    java.awt.image.BufferedImage couple = null, background = null;

    public boolean messageOn = false;
    java.util.ArrayList<String> message = new java.util.ArrayList<>();
    java.util.ArrayList<Integer> messageCounter = new java.util.ArrayList<>();

    public boolean gameFinished = false;
    public int commandNum = 0;

    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int merchantSlotCol = 0;
    public int merchantSlotRow = 0;

        // DIALOGUE
    public String currentDialogue = "";
    public int displayedCharCount = 0;
    public int charDisplayDelay = 1; // số frame giữa mỗi ký tự
    private int charDelayCounter = 0;
    private String lastDialogue = "";

    int subState = 0;
    int counter = 0;

    public entity.Entity merchant;

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
        entity.Entity bronze_coin = new object.OBJ_Coin_Bronze(gp);
        coin = bronze_coin.down1;
        getPixelFont();
    }

    public void getPixelFont() {
        try {
            pixelO = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/font/PixelOperator.ttf"));
            pixelOBold = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/font/PixelOperator-Bold.ttf"));
            pixelOBold48F = pixelOBold.deriveFont(48F);
            pixelOBold56F = pixelOBold.deriveFont(56F);
            pixelOBold32F = pixelOBold.deriveFont(32F);
            pixelOBold64F = pixelOBold.deriveFont(64F);
            pixelOBold32F = pixelOBold.deriveFont(32F);
            pixelOBold16F = pixelOBold.deriveFont(16F);
            pixelOBold100F = pixelOBold.deriveFont(100F);
            pixelOBold80F = pixelOBold.deriveFont(80F);
            background = ImageIO.read(getClass().getResourceAsStream("/res/tiles/image.png"));
            couple = ImageIO.read(getClass().getResourceAsStream("/res/player/couple.png"));
        }
        catch (java.awt.FontFormatException e) {
            e.printStackTrace(); 
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage() {
        int messageX  = gp.tileSize;
        int messageY = gp.tileSize*3;
        g2.setFont(pixelOBold32F);

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

        g2.setFont(pixelOBold32F);
        g2.setColor(java.awt.Color.white);

        // TITLE SCREEN
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        else
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            //...
            drawMonsterLife();
            drawPlayerLife();
            drawMessage();
        }
        else
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        else
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        else
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        else
        // OPTION STATE
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        else
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        else
        // TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        else
        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        else
        // SLEEP STATE
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    public void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gp.eManager.lightning.filterAlpha += 0.01f;
            if (gp.eManager.lightning.filterAlpha > 1f) {
                gp.eManager.lightning.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lightning.filterAlpha -= 0.01f;
            if (gp.eManager.lightning.filterAlpha <= 0f) {
                gp.eManager.lightning.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lightning.dayCounter = 0;
                gp.eManager.lightning.dayState = gp.eManager.lightning.day;
                gp.gameState = gp.playState;
                gp.player.getPlayerImage();
            }
        }
    }

    public void drawTradeScreen() {
        switch (subState) {
            case 0: tradeSelect(); break;
            case 1: tradeBuy(); break;
            case 2: tradeSell(); break;
        }
        gp.keyHandler.enterPressed = false;
    }

    public void tradeSelect() {
        drawDialogueScreen();
        // DRAW WINDOW
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = gp.tileSize*3;
        int height = (int)(gp.tileSize*3.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed == true) {
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed == true) {
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed == true) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come again, hehe!";
            }
        }
    }

    public void tradeBuy() {
        // DRAW INVENTORY
        drawInventory(gp.player, false);
        drawInventory(merchant, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2; 
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(merchantSlotCol, merchantSlotRow);
        if (itemIndex < merchant.inventory.size()) {
            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = merchant.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize*8 - 20);
            g2.drawString(text, x, y + 34);

            // BUY AN ITEM
            if (gp.keyHandler.enterPressed == true) {
                if (merchant.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                }
                else {
                    if (gp.player.canObtainItem(merchant.inventory.get(itemIndex)) == true) {
                        gp.player.coin -= merchant.inventory.get(itemIndex).price;
                    } 
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot carry any more!";
                    }
                }
                // else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                //     subState = 0;
                //     gp.gameState = gp.dialogueState;
                //     currentDialogue = "You cannot carry any more!";
                //     drawDialogueScreen();
                // }
                // else {
                //     gp.player.coin -= merchant.inventory.get(itemIndex).price;
                //     gp.player.inventory.add(merchant.inventory.get(itemIndex));
                // }
            }
        }
    }

    public void tradeSell() {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2; 
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize*18 - 20);
            g2.drawString(text, x, y + 34);

            // SELL AN ITEM
            if (gp.keyHandler.enterPressed == true) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    subState = 0;
                    commandNum = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
        }
    }

    public void drawTransition() {
        counter++;
        g2.setColor(new java.awt.Color(0, 0, 0, counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize*gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize*gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
    }

    public void drawMonsterLife() {
        for (int i = 0; i < gp.monster[1].length; i++) {
            entity.Entity monster = gp.monster[gp.currentMap][i];
            if (monster != null && monster.inCamera() == true) {
                // MONSTER HEALTH BAR
                if (monster.hpBarOn == true && monster.boss == false) {
                    double oneScale = (double)gp.tileSize/monster.maxLife;
                    double hpBarValue = oneScale*monster.life;

                    g2.setColor(new java.awt.Color(35, 35, 35));
                    g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() -16, gp.tileSize + 2, 12);
                    g2.setColor(new java.awt.Color(255, 0, 30));
                    g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);

                    monster.hpBarCounter++;
                    if (monster.hpBarCounter > 600) {
                        monster.hpBarOn = false;
                        monster.hpBarCounter = 0;
                    }
                }
                else if (monster.boss == true) {
                    //
                }
            }
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new java.awt.Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        
        g2.setFont(pixelOBold100F);
        text = "Game Over";
        g2.setColor(java.awt.Color.black);
        x = getXForCenteredText(text, pixelOBold100F);
        y = gp.tileSize * 4;

        g2.drawString(text, x, y);
        // Main
        g2.setColor(java.awt.Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(pixelOBold80F);
        text = "Retry";
        x = getXForCenteredText(text, pixelOBold80F);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
            if (gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.playState;
                gp.retry();
                commandNum = 0;
            }
        }

        // Back to title screen 
        text = "Quit";
        x = getXForCenteredText(text, pixelOBold80F);
        y += 60;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
            if (gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.titleState;
                gp.restart();
                commandNum = 0;
            }
        }

        gp.keyHandler.enterPressed = false;
    }

    public void drawOptionScreen() {
        g2.setColor(java.awt.Color.white);
        g2.setFont(pixelOBold.deriveFont(24F));
        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*9;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0: optionTop(frameX, frameY); break;
            case 1: optionFullScreenNotification(frameX, frameY); break;
            case 2: optionControl(frameX, frameY); break;
            case 3: optionEndGame(frameX, frameY); break;
        }

        gp.keyHandler.enterPressed = false;
    }

    public void optionEndGame(int frameX, int frameY) {
        int textX = frameX + (int)(gp.tileSize*0.5);
        int textY = frameY + gp.tileSize*3;
        g2.setFont(pixelO.deriveFont(32F));
        currentDialogue = "Quit the game and \nreturn to the title screen?";
        for (String line:currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXForCenteredText(text, pixelOBold32F);
        textY += gp.tileSize*2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        // NO
        text = "No";
        textX = getXForCenteredText(text, pixelOBold32F);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 2;
            }
        }
    }

    public void optionTop(int frameX, int frameY) {
        int textX;
        int textY;
        // TITLE 
        String text = "Options";
        g2.setFont(pixelOBold32F);
        textX = getXForCenteredText(text, pixelOBold32F);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("FullScreen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                if (gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else gp.fullScreenOn = false;
                subState = 1;
            }
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        
        // END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULLSCREEN CHECKBOX
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new java.awt.BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }
        gp.config.saveConfig();
    }

    public void optionFullScreenNotification(int frameX, int frameY) {
        g2.setFont(pixelO.deriveFont(32F));
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        currentDialogue = "The change will take\neffect after restating\nthe game.";
        for (String line:currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // BACK
        textY = frameY + gp.tileSize*7;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
            }
        }
    }

    public void optionControl(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        g2.setFont(pixelO.deriveFont(32F));
        String text = "Control";
        textX = getXForCenteredText(text, pixelO.deriveFont(32F));
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;
        // BACK
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 1;
            }
        }

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;
    }

    public void drawInventory(entity.Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        if (entity == gp.player) {
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight  = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else if (entity == merchant){
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight  = gp.tileSize*5;
            slotCol = merchantSlotCol;
            slotRow = merchantSlotRow;
        }
        // FRAME (20 slot)

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // DRAW ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {
            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon ||
                entity.inventory.get(i) == entity.currentShield ||
                entity.inventory.get(i) == entity.currentLight) {
                    g2.setColor(new java.awt.Color(240, 190, 90));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            // DISPLAY AMOUNT
            if (entity == gp.player && entity.inventory.get(i).amount > 1) {
                g2.setFont(pixelOBold.deriveFont(24F));
                int amountX;
                int amountY;
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXForAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;
                // SHADOW
                g2.setColor(new java.awt.Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);
                // NUMBER
                g2.setColor(java.awt.Color.white);
                g2.drawString(s, amountX - 3, amountY - 3);

            }
            slotX += slotSize;
            if (i % 5 == 4) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
        
        // CURSOR
        if (cursor == true) {
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
            g2.setFont(pixelO.deriveFont(32F));
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line:entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }

    public void drawText(String text, int x, int y, java.awt.Font font, java.awt.Color cTxt, java.awt.Color cShadow, int offset) {
        g2.setFont(font);
        g2.setColor(cShadow);
        g2.drawString(text, x + offset, y + offset);
        g2.setColor(cTxt);
        g2.drawString(text, x, y);
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow*5;
        return itemIndex;
    }

    public void drawPauseScreen() {
        String text = "PAUSE";
        int x = getXForCenteredText(text, pixelOBold32F);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text, java.awt.Font font) {
        java.awt.FontMetrics metrics = g2.getFontMetrics(font);
        int length = metrics.stringWidth(text);
        return gp.screenWidth / 2 - length / 2;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = tailX - length;
        return x;
    }

    public void drawDialogueScreen() {
        int x = gp.tileSize*3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize*4;
        drawBox(x, y, width, height, 30, 4);

        g2.setFont(pixelOBold32F);
        g2.setColor(java.awt.Color.PINK);
        y += gp.tileSize;

        if (!currentDialogue.equals(lastDialogue)) {
            displayedCharCount = 0;
            charDelayCounter = 0;
            lastDialogue = currentDialogue;
        }

        if (displayedCharCount < currentDialogue.length()) {
            charDelayCounter++;
            if (charDelayCounter >= charDisplayDelay) {
                displayedCharCount++;
                charDelayCounter = 0;
            }
        }

        String[] lines = currentDialogue.split("\n");
        int charsLeft = displayedCharCount;
        for (String line : lines) {
            int showLen = Math.min(charsLeft, line.length());
            String showLine = line.substring(0, showLen);
            x = getXForCenteredText(line, pixelOBold32F);
            g2.drawString(showLine, x, y);
            y += 40;
            charsLeft -= showLen;
            if (charsLeft <= 0) break;
        }
    }

    public void drawBox(int x, int y, int width, int height, int arc, int offset) {
        g2.setColor(new java.awt.Color(0, 0, 0, 100));
        g2.fillRoundRect(x + offset, y + offset, width, height, arc, arc);

        g2.setColor(new java.awt.Color(30, 30, 30, 200));
        g2.fillRoundRect(x, y, width, height, arc, arc);
        
        g2.setColor(java.awt.Color.WHITE);
        g2.setStroke(new java.awt.BasicStroke(4));
        g2.drawRoundRect(x + 2, y + 2, width - 4, height - 4, arc - 2, arc - 2);

        g2.setColor(new java.awt.Color(255, 255, 255, 80));
        g2.setStroke(new java.awt.BasicStroke(2));
        g2.drawRoundRect(x + 7, y + 7, width - 14, height - 14, arc - 4, arc - 4);
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

    public void changeAlpha(float a) {
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, a));
    }

    public void drawTitleScreen() {
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);
        changeAlpha(0.65f);
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        changeAlpha(1f);

        // GROUP NAME
        String text = "INFINITY CODE PROUDLY PRESENTS";
        int x = getXForCenteredText(text, pixelOBold48F);
        int y = (int)(gp.tileSize * 1.5);
        drawText(text, x, y, pixelOBold48F, java.awt.Color.WHITE, java.awt.Color.BLACK, 4);

        // TITLE NAME
        g2.setFont(pixelOBold56F);
        text = "Homebound - Island Survivors";
        x = getXForCenteredText(text, pixelOBold56F);
        y += (int)(gp.tileSize * 1.5);
        drawText(text, x, y, pixelOBold56F, java.awt.Color.WHITE, java.awt.Color.BLACK, 4);

        // COUPLE IMAGE
        int width = (int)(gp.tileSize * 4.5);
        int height = gp.tileSize * 2;
        x = (int)(gp.screenWidth / 2 - width / 2);
        y += (int)(gp.tileSize);
        g2.drawImage(couple, x, y, width, height, null);

        // MENU
        text = "NEW GAME";
        x = getXForCenteredText(text, pixelO.deriveFont(64F));
        y += height + gp.tileSize * 1.75;
        drawText(text, x, y, pixelO.deriveFont(64F), java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
        if (commandNum == 0) {
            drawText(">", x - gp.tileSize, y, pixelOBold64F, java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text, pixelO.deriveFont(64F));
        y += gp.tileSize * 1.5;
        drawText(text, x, y, pixelO.deriveFont(64F), java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
        if (commandNum == 1) {
            drawText(">", x - gp.tileSize, y, pixelOBold64F, java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
        }

        text = "EXIT";
        x = getXForCenteredText(text, pixelO.deriveFont(64F));
        y += gp.tileSize * 1.5;
        drawText(text, x, y, pixelO.deriveFont(64F), java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
        if (commandNum == 2) {
            drawText(">", x - gp.tileSize, y, pixelOBold64F, java.awt.Color.WHITE, java.awt.Color.BLACK, 3);
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
        g2.setFont(pixelO.deriveFont(32F));
        
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
