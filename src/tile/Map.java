package tile;

import java.awt.AlphaComposite;

public class Map extends TileManager {
    main.GamePanel gp;
    java.awt.image.BufferedImage worldMap[];
    public boolean miniMapOn = false;

    public Map(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new java.awt.image.BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize*gp.maxWorldCol;
        int worldMapHeight = gp.tileSize*gp.maxWorldRow;
        for (int i = 0; i < gp.maxMap; i++) {
            worldMap[i] = new java.awt.image.BufferedImage(worldMapWidth, worldMapHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D)worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize*col;
                int y = gp.tileSize*row;
                g2.drawImage(tile[tileNum].image, x, y, null);
                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(java.awt.Graphics2D g2) {
        g2.setColor(java.awt.Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int width = 500;
        int height = 500;
        int x = gp.screenWidth/2 - width/2;
        int y = gp.screenHeight/2 - height/2;
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // DRAW PLAYER
        double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
        int playerX = (int)(x + gp.player.worldX/scale);
        int playerY = (int)(y + gp.player.worldY/scale);
        int playerSize = (int)(gp.tileSize/scale);
        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

        // Hint
        g2.setFont(g2.getFont().deriveFont(24f));
        g2.setColor(java.awt.Color.white);
        g2.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(java.awt.Graphics2D g2) {
        if (miniMapOn == true) {
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - width - 50;
            int y = 50;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
            // DRAW PLAYER
            double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
            int playerX = (int)(x + gp.player.worldX/scale);
            int playerY = (int)(y + gp.player.worldY/scale);
            int playerSize = (int)(gp.tileSize/3);
            g2.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
