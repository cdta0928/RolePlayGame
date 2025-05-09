package tile;

public class TileManager {

    main.GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage() {
        
        setTileImage(0, "/res/tiles/grass.png");
        setTileImage(1, "/res/tiles/wall.png");
        setTileImage(2, "/res/tiles/water.png");
        setTileImage(3, "/res/tiles/earth.png");
        setTileImage(4, "/res/tiles/tree.png");
        setTileImage(5, "/res/tiles/sand.png");

    }

    public void loadMap(String filePath) {
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
                    mapTileNum[col][row] = num;
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
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}