package object;

public class OBJ_Shield_Wood extends entity.Entity {
    main.GamePanel gp;

    public OBJ_Shield_Wood(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/res/object/inventory/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by wood.";
        price = 75;
    }
}
