package object;

import tile_interactive.IT_Trunk;
import tile_interactive.Interactive;

public class OBJ_Fireball extends entity.Projectile {
    main.GamePanel gp;
    public static final String objName = "Fireball";

    public OBJ_Fireball(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1; // Mana cost
        alive = false;
        knockBackPower = 5;
        getImage();
    }
    
    public void getImage() {
        up1 = setup("/res/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/fireball_right_2", gp.tileSize, gp.tileSize);
    }
    
    public boolean haveResource(entity.Entity user) {
        boolean haveResource = false;
        if (user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(entity.Entity user) {
        user.mana -= useCost;
    }

    public Interactive getDestroyedForm() {
        Interactive tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public java.awt.Color getParticleColor() {
        java.awt.Color color = new java.awt.Color(240, 50, 0);
        return color;
    }
    
    public int getParticleSize() {
        int size = 10; // 6 pixels
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
