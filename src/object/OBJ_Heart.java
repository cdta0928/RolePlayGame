package object;

public class OBJ_Heart extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Heart";

    public OBJ_Heart(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = objName;
        type = type_pickupOnly;
        value = 2;
        down1 = setup("/res/object/heart/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/res/object/heart/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/object/heart/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/res/object/heart/heart_blank", gp.tileSize, gp.tileSize);
    }

    public boolean use(entity.Entity entity) {
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
        return true;
    }
}
