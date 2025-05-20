package object;

public class OBJ_Boots extends entity.Entity {
    public OBJ_Boots(main.GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setup("/res/object/boots", gp.tileSize, gp.tileSize);
    }
}
