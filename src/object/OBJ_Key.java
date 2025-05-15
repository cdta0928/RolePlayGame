package object;

public class OBJ_Key extends SuperObject{

    public OBJ_Key() {

        name = "Key";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/Key.png"));

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
    
}
