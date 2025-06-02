package monster;

public class MON_Slime extends entity.Entity {

    main.GamePanel gp;

    public MON_Slime(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 1;
        exp = 2;
        projectile = new object.OBJ_Rock(gp);

        solidArea = new java.awt.Rectangle(3, 18, 42, 30);
        solidAreaDefaultX = 3;
        solidAreaDefaultY = 18;

        getSlimeImage();
    }

    public void getSlimeImage() {
        up1 = setup("/res/monster/slime/slimeup1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/slime/slimeup2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/slime/slimedown1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/slime/slimedown2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/slime/slimeleft1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/slime/slimeleft2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/slime/slimeright1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/slime/slimeright2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath == true) {
            checkStopChasingOrNot(gp.player, 15, 100);
            searchPath(getGoatCol(gp.player), getGoatRow(gp.player));
            checkShootOrNot(200, 30);
        }
        else {
            checkStartChasingOrNot(gp.player, 5, 100);
            getRandomDirection();
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
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