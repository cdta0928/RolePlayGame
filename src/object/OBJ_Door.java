package object;

public class OBJ_Door extends entity.Entity {
    
    public OBJ_Door(main.GamePanel gp) {
        super(gp);
        name = "Door";
        try {
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/door.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

}
