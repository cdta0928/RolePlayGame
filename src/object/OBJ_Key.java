package object;

public class OBJ_Key extends entity.Entity {

    public OBJ_Key(main.GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/res/object/Key", gp.tileSize, gp.tileSize); 
        description = "[" + name + "]\nUse to open the door.";
    }
}
