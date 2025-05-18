package main;

public class KeyHandler implements java.awt.event.KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed; // Movement flags

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override  
    public void keyTyped(java.awt.event.KeyEvent e) {
        // Not used
    }
    
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the pressed key
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (code == java.awt.event.KeyEvent.VK_W || code == java.awt.event.KeyEvent.VK_UP) { 
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_S || code == java.awt.event.KeyEvent.VK_DOWN) { 
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    // Later
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == java.awt.event.KeyEvent.VK_W) { 
                upPressed = true; // Set the upPressed flag to true
            }
            if (code == java.awt.event.KeyEvent.VK_A) { 
                leftPressed = true; // Set the leftPressed flag to true
            }
            if (code == java.awt.event.KeyEvent.VK_S) { 
                downPressed = true; // Set the rightPressed flag to true
            }
            if (code == java.awt.event.KeyEvent.VK_D) { 
                rightPressed = true; // Set the downPressed flag to true
            }
            if (code == java.awt.event.KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == java.awt.event.KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the released key
        if (code == java.awt.event.KeyEvent.VK_W) { // If the 'W' key is released
            upPressed = false; // Set the upPressed flag to false
        }
        if (code == java.awt.event.KeyEvent.VK_A) { // If the 'A' key is released
            leftPressed = false; // Set the leftPressed flag to false
        }
        if (code == java.awt.event.KeyEvent.VK_S) { // If the 'S' key is released
            downPressed = false; // Set the rightPressed flag to false
        }
        if (code == java.awt.event.KeyEvent.VK_D) { // If the 'D' key is released
            rightPressed = false; // Set the downPressed flag to false
        }
    }
    
}
