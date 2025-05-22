package monster;

public class MON_GreenSlime extends entity.Entity {

    main.GamePanel gp;

    public MON_GreenSlime(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new object.OBJ_Rock(gp);

        solidArea = new java.awt.Rectangle(3, 18, 42, 30);
        solidAreaDefaultX = 3;
        solidAreaDefaultY = 18;

        getSlimeImage();
    }

    public void getSlimeImage() {
        up1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            java.util.Random random = new java.util.Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) { direction = "up"; } 
            if (i > 25 && i <= 50) { direction = "down"; } 
            if (i > 50 && i <= 75) { direction = "left"; } 
            if (i > 75 && i <= 100) { direction = "right"; }
            actionLockCounter = 0;
        }
        int i = new java.util.Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 60) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction; 
    }

    public void checkDrop() {
        // CAST A DIE
        int i = new java.util.Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new object.OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new object.OBJ_Heart(gp));
        }
        if (i >= 75 && i <= 100) {
            dropItem(new object.OBJ_ManaCrystal(gp));
        }
    }
}