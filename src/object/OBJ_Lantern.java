package object;

public class OBJ_Lantern extends entity.Entity {
    public OBJ_Lantern(main.GamePanel gp) {
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = setup("/res/object/inventory/lantern", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIlluminates your \nsurroundings.";
        price = 200;
        lightRadius = 250;
    }
}
