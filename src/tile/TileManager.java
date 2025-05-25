package tile;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.UtilityTool;

public class TileManager {
    main.GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = false;

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        
        tile = new Tile[10];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt", 0);
        loadMap("/res/maps/interior01.txt", 1);
    }

    public void getTileImage() {
        setup(0, "grass.png", false);
        setup(1, "wall.png", true);
        setup(2, "water.png", true);
        setup(3, "earth.png", false);
        setup(4, "tree.png", true);
        setup(5, "sand.png", false);
        setup(6, "hut.png", false);
        setup(7, "floor01.png", false);
        setup(8, "table01.png", true);
    }

    public void loadMap(String filePath, int map) {
        try {
            java.io.InputStream is = getClass().getResourceAsStream(filePath);
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                // Corrected condition: use gp.maxWorldCol
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();                            
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+ imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) 
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
        if (drawPath == true) {
            g2.setColor(new java.awt.Color(255, 0, 0, 70));
            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
                int worldX = gp.pFinder.pathList.get(i).col*gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row*gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}