package object;

public class OBJ_Door extends entity.Entity {
    public OBJ_Door(main.GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("/res/object/door.png", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea = new java.awt.Rectangle(0, 16, 48, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
