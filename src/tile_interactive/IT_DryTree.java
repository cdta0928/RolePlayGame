package tile_interactive;

public class IT_DryTree extends Interactive {
    main.GamePanel gp;

    public IT_DryTree(main.GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        life = 3;
        down1 = setup("/res/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
    }

    public boolean isCorrectItem(entity.Entity entity) {
        boolean isCorrectItem = false;
        if (entity.currentWeapon.type == type_axe) { isCorrectItem = true; }
        return isCorrectItem;
    }

    public Interactive getDestroyedForm() {
        Interactive tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public java.awt.Color getParticleColor() {
        java.awt.Color color = new java.awt.Color(65, 50, 30);
        return color;
    }
    
    public int getParticleSize() {
        int size = 6; // 6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    
    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }
}
