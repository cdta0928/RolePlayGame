package object;

public class OBJ_Chest extends SuperObject {
    
    public OBJ_Chest() {
        
        name = "Chest";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/chest.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
    }

}
