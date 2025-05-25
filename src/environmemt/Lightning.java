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

        // Create a gradation effect within the light circle
        java.awt.Color color[] = new java.awt.Color[12];
        float fraction[] = new float[12];

        color[0] = new java.awt.Color(0, 0, 0, 0.1f);
        color[1] = new java.awt.Color(0, 0, 0, 0.42f);
        color[2] = new java.awt.Color(0, 0, 0, 0.52f);
        color[3] = new java.awt.Color(0, 0, 0, 0.61f);
        color[4] = new java.awt.Color(0, 0, 0, 0.69f);
        color[5] = new java.awt.Color(0, 0, 0, 0.76f);
        color[6] = new java.awt.Color(0, 0, 0, 0.82f);
        color[7] = new java.awt.Color(0, 0, 0, 0.87f);
        color[8] = new java.awt.Color(0, 0, 0, 0.91f);
        color[9] = new java.awt.Color(0, 0, 0, 0.94f);
        color[10] = new java.awt.Color(0, 0, 0, 0.96f);
        color[11] = new java.awt.Color(0, 0, 0, 0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        // Create a gradation paint settings for the light circle
        java.awt.RadialGradientPaint gPaint = new java.awt.RadialGradientPaint(centerX, centerY, (circleSize)/2, fraction, color);

        // Set the gradient data on g2
        g2.setPaint(gPaint);

        // Draw the light circle
        g2.fill(lightArea);

        // // Set a color to draw the rectangle
        // g2.setColor(new java.awt.Color(0, 0, 0, 0.95F));

        // Draw the screen rectangle without the light circle area
        g2.fill(screenArea);

        g2.dispose();
    }

    public void draw(java.awt.Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
