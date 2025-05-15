package main;

public class UI {
    
    GamePanel gp;
    java.awt.Font arial_40;

    public UI(GamePanel gp) {
        this.gp = gp;   
        arial_40 = new java.awt.Font("Arial", java.awt.Font.PLAIN, 40);
    }

    public void draw(java.awt.Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(java.awt.Color.WHITE);
        g2.drawString("Key = " + gp.player.hasKey, 50, 50);

    }

}
