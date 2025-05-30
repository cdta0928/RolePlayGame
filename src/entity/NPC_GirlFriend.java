package entity;

public class NPC_GirlFriend extends Entity {
    
    public NPC_GirlFriend(main.GamePanel gp) {
        super(gp);

        type = 1;
        direction = "down";
        speed = 2;

        getOldManImage();
        setDialogue();

        solidArea = new java.awt.Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getOldManImage() {
        up1 = setup("/res/npc/gf/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/gf/up2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/gf/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/gf/down2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/gf/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/gf/left2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/gf/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/gf/right2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
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
    }

    public void setDialogue() {
        dialogues[0] = "Hey, honey!\nWhere is this place?";
        dialogues[1] = "I'm not sure, but...\nI think we are in another world.";
        dialogues[2] = "I don't know how we got here.\nBut I think we should go home.";
        dialogues[3] = "I hope we can got it.";
    }

    public void speak() {
        super.speak();
        // onPath = true;
    }

}
