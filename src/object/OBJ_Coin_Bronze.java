package object;

public class OBJ_Coin_Bronze extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Coin";

    public OBJ_Coin_Bronze(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = objName;

        type = type_pickupOnly;
        value = 1;
        down1 = setup("/res/object/coin_bronze", gp.tileSize, gp.tileSize);
    }

    public boolean use(entity.Entity entity) {
        gp.ui.addMessage("Coin +" + value);
        entity.coin += value;
        return true;
    }
}
