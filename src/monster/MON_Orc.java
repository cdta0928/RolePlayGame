package monster;

import entity.Entity;

public class MON_Orc extends Entity {
    main.GamePanel gp;

    public MON_Orc(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        motion1_duration = 40;
        motion2_duration = 85;

        solidArea = new java.awt.Rectangle(4, 4, 40, 34);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea = new java.awt.Rectangle(0, 0, 48, 48);  

        getOrcImage();
        getAttackImage();
    }

    public void getOrcImage() {
        up1 = setup("/res/monster/orc/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/orc/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/orc/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/orc/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/orc/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/orc/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/orc/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/orc/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("/res/monster/orc/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/monster/orc/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/monster/orc/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/monster/orc/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/monster/orc/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/monster/orc/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/monster/orc/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/monster/orc/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    public void setAction() {
        if (onPath == true) {
            checkStopChasingOrNot(gp.player, 15, 100);
            searchPath(getGoatCol(gp.player), getGoatRow(gp.player));
        }
        else {
            checkStartChasingOrNot(gp.player, 5, 100);
            getRandomDirection();
        }

        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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
