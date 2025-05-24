package monster;

public class MON_GreenSlime extends entity.Entity {

    main.GamePanel gp;

    public MON_GreenSlime(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
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
        if (onPath == true) {
            // NPC findPath to move
            // int goalCol = 10;
            // int goalRow = 8;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
            System.out.println(worldX/gp.tileSize + " " + worldY/gp.tileSize);
            System.out.println(collisionOn);
            if (worldX/gp.tileSize == 11 && worldY/gp.tileSize == 20) {
                direction = "down";
            }
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
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 60) {
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
        // direction = gp.player.direction; 
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