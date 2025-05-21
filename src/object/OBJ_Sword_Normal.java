package object;

public class OBJ_Sword_Normal extends entity.Entity {
    main.GamePanel gp;

    public OBJ_Sword_Normal(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Normal Sword";
        down1 = setup("/res/object/inventory/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
