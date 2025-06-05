package monster;

public class MON_Bat extends entity.Entity {
    main.GamePanel gp;

    public MON_Bat(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea = new java.awt.Rectangle(0, 6, 48, 30);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 6;

        getBatImage();
    }

    public void getBatImage() {
        up1 = setup("/res/monster/bat/batu1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/bat/batu2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/bat/batd1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/bat/batd2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/bat/batl1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/bat/batl2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/bat/batr1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/bat/batr2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath == true) {
        }
        else {
            getRandomDirection(10);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
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
