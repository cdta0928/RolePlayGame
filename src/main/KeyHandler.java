package main;

public class KeyHandler implements java.awt.event.KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean shotKeyPressed;

    public KeyHandler(GamePanel gp) { this.gp = gp; }

    @Override  
    public void keyTyped(java.awt.event.KeyEvent e) { }
    
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode(); 

        // TITLE STATE
        if (gp.gameState == gp.titleState) { titleState(code); }

        // PLAY STATE
        else if (gp.gameState == gp.playState) { playState(code); }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) { pauseState(code); }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) { dialogueState(code); }

        // CHARACTER STATE
        else if (gp.gameState == gp.characterState) { characterState(code); }
        
        // OPTION STATE
        else if (gp.gameState == gp.optionState) { optionState(code); }

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) { gameOverState(code); }

        // TRADE STATE
        else if (gp.gameState == gp.tradeState) { tradeState(code); }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code == java.awt.event.KeyEvent.VK_W) { upPressed = false; }
        if (code == java.awt.event.KeyEvent.VK_A) { leftPressed = false; }
        if (code == java.awt.event.KeyEvent.VK_S) { downPressed = false; }
        if (code == java.awt.event.KeyEvent.VK_D) { rightPressed = false; }
        if (code == java.awt.event.KeyEvent.VK_F) { shotKeyPressed = false; }
    }

    public void titleState(int code) {
        switchCommandNum(2, code);

        if (code == java.awt.event.KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) { gp.gameState = gp.playState; }
            if (gp.ui.commandNum == 1) {
                // Later
            }
            if (gp.ui.commandNum == 2) { System.exit(0); }
        }
    }

    public void playState(int code) {
        if (code == java.awt.event.KeyEvent.VK_W) { upPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_A) { leftPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_S) { downPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_D) { rightPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_P) { gp.gameState = gp.pauseState; }
        if (code == java.awt.event.KeyEvent.VK_ENTER) { enterPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_C) { gp.gameState = gp.characterState; }
        if (code == java.awt.event.KeyEvent.VK_F) { shotKeyPressed = true; }
        if (code == java.awt.event.KeyEvent.VK_ESCAPE) { gp.gameState = gp.optionState; }
        if (code == java.awt.event.KeyEvent.VK_R) { 
            switch (gp.currentMap) {
                case 0:
                    gp.tileManager.loadMap("/res/maps/world01.txt", 0);
                    break;
                case 1:
                    gp.tileManager.loadMap("/res/maps/interior01.txt", 1);
                    break;
            }
        }
    }

    public void pauseState(int code) {
        if (code == java.awt.event.KeyEvent.VK_P) { gp.gameState = gp.playState; }
    }

    public void dialogueState(int code) {
        if (code == java.awt.event.KeyEvent.VK_ENTER) { gp.gameState = gp.playState; }
    }

    public void characterState(int code) {
        if (code == java.awt.event.KeyEvent.VK_C) { gp.gameState = gp.playState; }
        playerInventory(code);
        if (code == java.awt.event.KeyEvent.VK_ENTER) { gp.player.selectItem(); }
    }

    public void optionState(int code) {
        if (code == java.awt.event.KeyEvent.VK_ESCAPE) { gp.gameState = gp.playState; }
        if (code == java.awt.event.KeyEvent.VK_ENTER) { enterPressed = true; }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 3; break;
            case 3: maxCommandNum = 1; break;
        }
        switchCommandNum(maxCommandNum, code);  
    }

    public void gameOverState(int code) {
        switchCommandNum(1, code); 
        if (code == java.awt.event.KeyEvent.VK_ENTER) { enterPressed = true; }
    }

    public void tradeState(int code) {
        if (code == java.awt.event.KeyEvent.VK_ENTER) { enterPressed = true; }
        switch (gp.ui.subState) {
            case 0: 
                switchCommandNum(2, code);
                break;
            case 1:
                merchantInventory(code);
                if (code == java.awt.event.KeyEvent.VK_ESCAPE) { gp.ui.subState = 0; }
                break;
            case 2: 
                playerInventory(code);
                if (code == java.awt.event.KeyEvent.VK_ESCAPE) { gp.ui.subState = 0; }
                break;
        }
    }
    
    public void switchCommandNum(int maxCommandNum, int code) {
        if (code == java.awt.event.KeyEvent.VK_W || code == java.awt.event.KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == java.awt.event.KeyEvent.VK_S || code == java.awt.event.KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
    }

    public void playerInventory(int code) {
        if (code == java.awt.event.KeyEvent.VK_W) { 
            if (gp.ui.playerSlotRow != 0) { gp.ui.playerSlotRow--; }
        }
        if (code == java.awt.event.KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) { gp.ui.playerSlotCol--; }
        }
        if (code == java.awt.event.KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) { gp.ui.playerSlotRow++; }
        }
        if (code == java.awt.event.KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) { gp.ui.playerSlotCol++; }
        }
    }

    public void merchantInventory(int code) {
        if (code == java.awt.event.KeyEvent.VK_W) { 
            if (gp.ui.merchantSlotRow != 0) { gp.ui.merchantSlotRow--; }
        }
        if (code == java.awt.event.KeyEvent.VK_A) {
            if (gp.ui.merchantSlotCol != 0) { gp.ui.merchantSlotCol--; }
        }
        if (code == java.awt.event.KeyEvent.VK_S) {
            if (gp.ui.merchantSlotRow != 3) { gp.ui.merchantSlotRow++; }
        }
        if (code == java.awt.event.KeyEvent.VK_D) {
            if (gp.ui.merchantSlotCol != 4) { gp.ui.merchantSlotCol++; }
        }
    }
}
