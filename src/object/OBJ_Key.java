package object;

public class OBJ_Key extends entity.Entity {
    main.GamePanel gp;
    public OBJ_Key(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Key";
        down1 = setup("/res/object/Key", gp.tileSize, gp.tileSize); 
        description = "[" + name + "]\nUse to open the door.";
        price = 100;
    }

    public boolean use(entity.Entity entity) {
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999) {
            gp.ui.currentDialogue = "You use the " + name + " and open the door";
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }
    }
}
