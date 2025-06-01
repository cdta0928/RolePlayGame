package object;

public class OBJ_Shiled_Iron extends entity.Entity {
    public OBJ_Shiled_Iron (main.GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Iron Shield";
        down1 = setup("/res/object/inventory/shield_iron", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny iron shield.";
        price = 100;
    }
}
