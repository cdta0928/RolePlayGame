package tile;

public class TileManager {

    main.GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/map01.txt");
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
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                x = 0;
                y += gp.tileSize;
            } else {
                x += gp.tileSize;
            }
        }

    }

}