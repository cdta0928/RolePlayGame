package environmemt;

public class Lightning {
    main.GamePanel gp;
    java.awt.image.BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    // DAY STATE
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;


    public Lightning(main.GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void draw(java.awt.Graphics2D g2) {
        if (gp.currArea == gp.outside) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, filterAlpha));
        }
        if (gp.currArea == gp.outside || gp.currArea == gp.dungeon) {
            g2.drawImage(darknessFilter, 0, 0, null);
        }
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1F));
    }

    public void setLightSource() {
        // Create a buffered image
        darknessFilter = new java.awt.image.BufferedImage(gp.screenWidth, gp.screenHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D)darknessFilter.getGraphics();

        if (gp.player.currentLight == null) {
            g2.setColor(new java.awt.Color(0, 0, 0.1f, 0.98f));
        }
        else {
            // Get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            // Create a gradation effect within the light circle
            java.awt.Color color[] = new java.awt.Color[12];
            float fraction[] = new float[12];

            color[0] = new java.awt.Color(0, 0, 0.1f, 0.1f);
            color[1] = new java.awt.Color(0, 0, 0.1f, 0.42f);
            color[2] = new java.awt.Color(0, 0, 0.1f, 0.52f);
            color[3] = new java.awt.Color(0, 0, 0.1f, 0.61f);
            color[4] = new java.awt.Color(0, 0, 0.1f, 0.69f);
            color[5] = new java.awt.Color(0, 0, 0.1f, 0.76f);
            color[6] = new java.awt.Color(0, 0, 0.1f, 0.82f);
            color[7] = new java.awt.Color(0, 0, 0.1f, 0.87f);
            color[8] = new java.awt.Color(0, 0, 0.1f, 0.91f);
            color[9] = new java.awt.Color(0, 0, 0.1f, 0.94f);
            color[10] = new java.awt.Color(0, 0, 0.1f, 0.96f);
            color[11] = new java.awt.Color(0, 0, 0.1f, 0.98f);

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
            java.awt.RadialGradientPaint gPaint = new java.awt.RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if (gp.player.lightUpdated == true) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        // Check the state of the day
        if (dayState == day) {
            dayCounter++;
            if (dayCounter > 6000) {
                dayState = dusk;
                dayCounter = 0;
            }
        }

        if (dayState == dusk) {
            filterAlpha += 0.001F;
        }

        if (filterAlpha > 1f) {
            filterAlpha = 1f;
            dayState = night;
        }

        if (dayState == night) {
            dayCounter++;
            if (dayCounter > 600) {
                dayState = dawn;
                dayCounter = 0;
            }
        }

        if (dayState == dawn) {
            filterAlpha -= 0.001F;
            if (filterAlpha < 0F) {
                filterAlpha = 0;
                dayState = day;
            }
        }
    }
}
