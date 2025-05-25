package tile;

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
        
        setTileImage(0, "/res/tiles/grass.png");
        setTileImage(1, "/res/tiles/wall.png");
        setTileImage(2, "/res/tiles/water.png");
        setTileImage(3, "/res/tiles/earth.png");
        setTileImage(4, "/res/tiles/tree.png");
        setTileImage(5, "/res/tiles/sand.png");
        setTileImage(6, "/res/tiles/hut.png");
        setTileImage(7, "/res/tiles/floor01.png");
        setTileImage(8, "/res/tiles/table01.png");

        tile[0].collision = false; // Grass
        tile[1].collision = true; // Wall
        tile[2].collision = true; // Water
        tile[3].collision = false; // Earth
        tile[4].collision = true; // Tree
        tile[5].collision = false; // Sand
        
        tile[6].collision = false;
        tile[7].collision = false;
        tile[8].collision = true;
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

    public void setTileImage(int index, String imagePath) {
        try {
            tile[index] = new Tile();
            tile[index].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (java.io.IOException e) {
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