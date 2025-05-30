package monster;

public class MON_Spider extends entity.Entity {
    main.GamePanel gp;
    
    public MON_Spider(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        setDefaultAttributes();
        getImage();
    }

    public void setDefaultAttributes() {
        name = "Spider";
        type = type_monster;
        defaultSpeed = 2;
        speed = defaultSpeed;
        direction = "down";
        solidArea = new java.awt.Rectangle(6, 16, 36, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 2;
        exp = 4;
        projectile = new object.OBJ_Rock(gp);
        projectile.speed *= 2;
    }

    public void getImage() {
        up1 = setup("/res/monster/spider/spideru1", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/spider/spiderd1", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/spider/spiderl1", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/spider/spiderr1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/spider/spideru2", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/spider/spiderd2", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/spider/spiderl2", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/spider/spiderr2", gp.tileSize, gp.tileSize);
    }
    public void setAction() {
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
        }
        else {
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
        }
        int i = new java.util.Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 180) {
            projectile.set(worldX, worldY, direction, true, this);
            for (int ii = 0; ii < gp.projectile[gp.currentMap].length; ii++) {
                if (gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    @Override
    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;
        if (onPath == false && tileDistance < 10) {
            int i = new java.util.Random().nextInt(100) + 1;
            if (i > 50) { onPath = true; }
        }
        if (onPath == true && tileDistance > 30) { onPath = false; }
    }

    public void damageReaction() {
        actionLockCounter = 0; 
        onPath = true;
    }

    public void checkDrop() {
        // CAST A DIE
        int i = new java.util.Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 25) {
            dropItem(new object.OBJ_Coin_Bronze(gp));
            dropItem(new object.OBJ_Coin_Bronze(gp));
            dropItem(new object.OBJ_Coin_Bronze(gp));
            dropItem(new object.OBJ_Coin_Bronze(gp));
            dropItem(new object.OBJ_Coin_Bronze(gp));
        }
        if (i >= 25 && i < 75) {
            dropItem(new object.OBJ_Heart(gp));
        }
        if (i >= 75 && i <= 100) {
            dropItem(new object.OBJ_ManaCrystal(gp));
        }
    }
}
