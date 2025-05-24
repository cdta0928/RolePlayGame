package tile_interactive;

public class IT_Trunk extends Interactive{
    main.GamePanel gp;

    public IT_Trunk(main.GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        down1 = setup("/res/tiles_interactive/trunk", gp.tileSize, gp.tileSize);
        destructible = false;
        solidArea = new java.awt.Rectangle(0, 0, 0, 0);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }
}
