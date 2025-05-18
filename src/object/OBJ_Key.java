package object;

public class OBJ_Key extends entity.Entity {

    public OBJ_Key(main.GamePanel gp) {
        super(gp);
        name = "Key";
        try {
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/Key.png"));

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } 

    }
    
}
