package object;

public class OBJ_Lantern extends entity.Entity {
    public static final String objName = "Lantern";
    main.GamePanel gp;

    public OBJ_Lantern(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_light;
        name = objName;
        down1 = setup("/res/object/inventory/lantern", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIlluminates your \nsurroundings.";
        price = 200;
        lightRadius = 250;
    }
}
