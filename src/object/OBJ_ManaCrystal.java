package object;

public class OBJ_ManaCrystal extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Mana Crystal";

    public OBJ_ManaCrystal(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_pickupOnly;
        value = 1;
        down1 = setup("/res/projectile/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("/res/projectile/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/projectile/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    public boolean use(entity.Entity entity) {
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
        return true;
    }
}
