package entity;

import java.util.Random;

public class NPC_OldMan extends Entity {
    
    public NPC_OldMan(main.GamePanel gp) {

        super(gp);
        type = 1;
        direction = "down";
        speed = 1;

        getOldManImage();
        setDialogue();

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getOldManImage() {

        try {
            up1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_up_1.png"));
            up2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_up_2.png"));
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_down_1.png"));
            down2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_down_2.png"));
            left1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_left_1.png"));
            left2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_left_2.png"));
            right1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_right_1.png"));
            right2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_right_2.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace(); // Print stack trace if image loading fails
        }

    }

    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 && i <= 50) {
                direction = "down";
            }

            if (i > 50 && i <= 75) {
                direction = "left";
            }

            if (i > 75 && i <= 100) {
                direction = "right";
            }
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
