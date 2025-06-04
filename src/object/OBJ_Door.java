package object;

public class OBJ_Door extends entity.Entity {
    main.GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Door(main.GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/res/object/door", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea = new java.awt.Rectangle(0, 16, 48, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this";
    }
}
