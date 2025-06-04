package object;

public class OBJ_Shield_Iron extends entity.Entity {
    public static final String objName = "Iron Shield";

    public OBJ_Shield_Iron (main.GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = objName;
        down1 = setup("/res/object/inventory/shield_iron", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny iron shield.";
        price = 100;
    }
}
