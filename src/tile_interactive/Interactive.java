package tile_interactive;

public class Interactive extends entity.Entity {
    main.GamePanel gp;
    public boolean destructible = false;

    public Interactive(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    public void update() {
        
    }
}
