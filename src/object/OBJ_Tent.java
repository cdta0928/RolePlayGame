package object;

public class OBJ_Tent extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/res/object/inventory/tent", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nYou can sleep until\nnext morning.";
        price = 300;
        stackable = true;
    }

    public boolean use(entity.Entity entity) {
        gp.gameState = gp.sleepState;
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
