package object;

public class OBJ_Shiled_Blue extends entity.Entity {
    public OBJ_Shiled_Blue (main.GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Blue Shield";
        down1 = setup("/res/object/inventory/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
    }
}
