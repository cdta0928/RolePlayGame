package object;

public class OBJ_Chest extends entity.Entity {
    
    public OBJ_Chest(main.GamePanel gp) {
        super(gp);
        name = "Chest";
        try {
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/chest.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
    }

}
