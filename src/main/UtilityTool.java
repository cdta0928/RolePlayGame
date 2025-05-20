package main;

public class UtilityTool {
    public java.awt.image.BufferedImage scaleImage(java.awt.image.BufferedImage original, int width, int height) {
        java.awt.image.BufferedImage scaledImage = new java.awt.image.BufferedImage(width, height, original.getType());
        java.awt.Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
