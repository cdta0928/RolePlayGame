package tile;

public class TileManager {

    main.GamePanel gp;
    Tile[] tile;

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        
        setTileImage(0, "/res/tiles/grass.png");
        setTileImage(1, "/res/tiles/wall.png");
        setTileImage(2, "/res/tiles/water.png");

    }

    public void setTileImage(int index, String imagePath) {
        try {
            tile[index] = new Tile();
            tile[index].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}