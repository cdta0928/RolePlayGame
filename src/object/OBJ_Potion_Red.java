package object;

public class OBJ_Potion_Red extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Red Potion";

    public OBJ_Potion_Red(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("/res/object/inventory/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
        price = 25;
        stackable = true;
    }
    public boolean use(entity.Entity entity) {
        gp.gameState = gp.dialogueState; 
        gp.ui.currentDialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        return true;
    }
}