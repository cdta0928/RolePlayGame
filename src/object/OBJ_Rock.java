package object;
public class OBJ_Rock extends entity.Projectile {
    main.GamePanel gp;

    public OBJ_Rock(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 6;
        useCost = 1;
        alive = false;
        getImage();
    }
    
    public void getImage() {
        up1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }
}
