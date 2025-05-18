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

}