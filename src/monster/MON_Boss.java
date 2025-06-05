package monster;

public class MON_Boss extends entity.Entity {
    main.GamePanel gp;
    public static final String monName = "Goblin Boss";

    public MON_Boss(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = monName;
        boss = true;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 3;
        exp = 50;
        motion1_duration = 25;
        motion2_duration = 50;
        knockBackPower = 5;
        int size = gp.tileSize*5;
        solidArea = new java.awt.Rectangle(48, 48, size - 48*2, size - 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea = new java.awt.Rectangle(0, 0, 170, 170);  

        getBossImage();
        getAttackImage();
    }

    public void getBossImage() {
        int i = 5;
        up1 = setup("/res/monster/boss/goblinu1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/res/monster/boss/goblinu2", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("/res/monster/boss/goblind1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/res/monster/boss/goblind2", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/res/monster/boss/goblinl1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/res/monster/boss/goblinl2", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/res/monster/boss/goblinr1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/res/monster/boss/goblinr2", gp.tileSize*i, gp.tileSize*i);
    }

    public void getAttackImage() {
        int i = 5;

        attackUp1 = setup("/res/monster/boss/attacku1", gp.tileSize*i, gp.tileSize*2*i);
        attackUp2 = setup("/res/monster/boss/attacku2", gp.tileSize*i, gp.tileSize*2*i);
        attackDown1 = setup("/res/monster/boss/attackd1", gp.tileSize*i, gp.tileSize*2*i);
        attackDown2 = setup("/res/monster/boss/attackd2", gp.tileSize*i, gp.tileSize*2*i);
        attackLeft1 = setup("/res/monster/boss/attackl1", gp.tileSize*2*i, gp.tileSize*i);
        attackLeft2 = setup("/res/monster/boss/attackl2", gp.tileSize*2*i, gp.tileSize*i);
        attackRight1 = setup("/res/monster/boss/attackr1", gp.tileSize*2*i, gp.tileSize*i);
        attackRight2 = setup("/res/monster/boss/attackr2", gp.tileSize*2*i, gp.tileSize*i);
    }

    public void setAction() {
        if (rageMode == false && life < maxLife/2) {
            rageMode = true;
            defaultSpeed += 2;
            speed = defaultSpeed;
            attack *= 2;
            defense *= 3;
        }

        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        }
        else {
            getRandomDirection(120);
        }

        if (attacking == false) {
            checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
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
