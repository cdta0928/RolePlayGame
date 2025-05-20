package entity;

public class NPC_OldMan extends Entity {
    
    public NPC_OldMan(main.GamePanel gp) {
        super(gp);

        type = 1;
        direction = "down";
        speed = 1;

        getOldManImage();
        setDialogue();

        solidArea = new java.awt.Rectangle(0, 16, 48, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getOldManImage() {
        up1 = setup("/res/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/oldman_right_2", gp.tileSize, gp.tileSize);
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
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you're come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a greate wizard but now... \nI'm a litte bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
    }

    public void speak() {
        super.speak();
    }

}
