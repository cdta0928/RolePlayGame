package environmemt;

public class Lightning {
    main.GamePanel gp;
    java.awt.image.BufferedImage darknessFilter;

    public Lightning(main.GamePanel gp, int circleSize) {
        // Create a buffered image
        darknessFilter = new java.awt.image.BufferedImage(gp.screenWidth, gp.screenHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D)darknessFilter.getGraphics();
    }
}
