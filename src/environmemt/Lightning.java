package environmemt;

public class Lightning {
    main.GamePanel gp;
    java.awt.image.BufferedImage darknessFilter;

    public Lightning(main.GamePanel gp, int circleSize) {
        // Create a buffered image
        darknessFilter = new java.awt.image.BufferedImage(gp.screenWidth, gp.screenHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D)darknessFilter.getGraphics();

        // Create screen-sized rectangle area
        java.awt.geom.Area screenArea = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));
        
        // Get the center x and y of the light circle
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        // Get the top left x and y of the light circle
        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        // Create a light circle shape
        java.awt.Shape circleShape = new java.awt.geom.Ellipse2D.Double(x, y, circleSize, circleSize);

        // Create a light circle area
        java.awt.geom.Area lightArea = new java.awt.geom.Area(circleShape);

        // Subtract the light circle from the screen rectangle
        screenArea.subtract(lightArea);

        // Set a color to draw the rectangle
        g2.setColor(new java.awt.Color(0, 0, 0, 0.95F));

        // Draw the screen rectangle without the light circle area
        g2.fill(screenArea);

        g2.dispose();
    }

    public void draw(java.awt.Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
