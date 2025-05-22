package tile_interactive;

public class IT_DryTree extends Interactive {
    main.GamePanel gp;

    public IT_DryTree(main.GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        down1 = setup("/res/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
    }
}
