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

        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

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

}
