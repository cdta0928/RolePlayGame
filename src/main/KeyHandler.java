package main;

public class KeyHandler implements java.awt.event.KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed; // Movement flags

    @Override  
    public void keyTyped(java.awt.event.KeyEvent e) {
        // Not used
    }
    
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        
        int code = e.getKeyCode(); // Get the key code of the pressed key
        if (code == java.awt.event.KeyEvent.VK_W) { // If the 'W' key is pressed
            System.out.println("W key pressed"); // Placeholder for action
            upPressed = true; // Set the upPressed flag to true
        }
        if (code == java.awt.event.KeyEvent.VK_A) { // If the 'A' key is pressed
            System.out.println("A key pressed"); // Placeholder for action
            leftPressed = true; // Set the leftPressed flag to true
        }
        if (code == java.awt.event.KeyEvent.VK_S) { // If the 'S' key is pressed
            System.out.println("S key pressed"); // Placeholder for action
            downPressed = true; // Set the rightPressed flag to true
        }
        if (code == java.awt.event.KeyEvent.VK_D) { // If the 'D' key is pressed
            System.out.println("D key pressed"); // Placeholder for action
            rightPressed = true; // Set the downPressed flag to true
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
