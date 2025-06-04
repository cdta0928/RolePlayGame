package object;

public class OBJ_Chest extends entity.Entity {
    main.GamePanel gp;
    entity.Entity loot;
    boolean opened = false; 
    public static final String objName = "Chest";

    public OBJ_Chest(main.GamePanel gp, entity.Entity loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = objName;
        image = setup("/res/object/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/res/object/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;
        solidArea = new java.awt.Rectangle(4, 16, 40, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        if (opened == false) {
            StringBuilder sb = new StringBuilder();
            sb.append("You open the chest and found a " + loot.name + "!");
            if (gp.player.canObtainItem(loot) == false) {
                sb.append("\n.. But you cannot carry any more!");
            }
            else {
                sb.append("\nYou obtain the " + loot.name + "!");
                // gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "It's empty";
        }
    }
}
