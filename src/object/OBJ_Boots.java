package object;

public class OBJ_Boots extends entity.Entity {
    
    public OBJ_Boots(main.GamePanel gp) {
        super(gp);
        name = "Boots";
        try {
            down1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/boots.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

}
