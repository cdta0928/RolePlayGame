package object;

public class OBJ_Axe extends entity.Entity {
    public OBJ_Axe (main.GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "WoodCutter's Axe";
        down1 = setup("/res/object/inventory/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nA bit rusty but still\ncan cut some trees.";
        price = 75;
    }
}
