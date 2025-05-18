package monster;

public class MON_GreenSlime extends entity.Entity {

    public MON_GreenSlime(main.GamePanel gp) {
        super(gp);
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
    }

}