package monster;

public class MON_GreenSlime extends entity.Entity {

    public MON_GreenSlime(main.GamePanel gp) {
        super(gp);
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        solidArea = new java.awt.Rectangle(3, 18, 42, 30);
        solidAreaDefaultX = 3;
        solidAreaDefaultY = 18;
    }

    public void getSlimeImage() {

        try {
            up1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            up2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            down2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            left1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            left2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            right1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            right2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace(); // Print stack trace if image loading fails
        }

    }

}