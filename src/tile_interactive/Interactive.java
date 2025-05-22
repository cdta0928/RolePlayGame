package tile_interactive;

public class Interactive extends entity.Entity {
    main.GamePanel gp;
    public boolean destructible = false;

    public Interactive(main.GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(entity.Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public Interactive getDestroyedForm() {
        Interactive tile = null;
        return tile;
    }

    public void update() {
        
    }
}
