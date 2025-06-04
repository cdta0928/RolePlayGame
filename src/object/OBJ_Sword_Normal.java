package object;

public class OBJ_Sword_Normal extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_sword;
        name = objName;
        down1 = setup("/res/object/inventory/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 10;
        knockBackPower = 3;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
