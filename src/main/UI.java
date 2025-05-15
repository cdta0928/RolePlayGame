package main;

public class UI {
    
    GamePanel gp;
    java.awt.Font arial_40;
    java.awt.image.BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;   
        arial_40 = new java.awt.Font("Arial", java.awt.Font.PLAIN, 40);
        object.OBJ_Key key = new object.OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        
        message = text;
        messageOn = true;

    }

    public void draw(java.awt.Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(java.awt.Color.WHITE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);

        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
        }

    }

}
