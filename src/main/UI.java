package main;

public class UI {
    
    GamePanel gp;
    java.awt.Graphics2D g2;
    java.awt.Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;   
        arial_40 = new java.awt.Font("Arial", java.awt.Font.PLAIN, 40);
        arial_80B = new java.awt.Font("Arial", java.awt.Font.BOLD, 80);
    }

    public void showMessage(String text) {
        
        message = text;
        messageOn = true;

    }

    public void draw(java.awt.Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(java.awt.Color.white);
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            //...
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

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

    public void drawDialogueScreen() {

    }

}
