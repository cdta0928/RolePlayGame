package tile_interactive;

public class IT_DryTree extends Interactive {
    main.GamePanel gp;

    public IT_DryTree(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        down1 = setup("/res/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
    }
}
