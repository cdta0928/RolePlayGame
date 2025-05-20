package object;

public class OBJ_Chest extends entity.Entity {
    public OBJ_Chest(main.GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setup("/res/object/chest", gp.tileSize, gp.tileSize);
    }
}
