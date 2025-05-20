package object;

public class OBJ_Heart extends entity.Entity {
    main.GamePanel gp;

    public OBJ_Heart(main.GamePanel gp) {
        super(gp);
        
        name = "Heart";
        image = setup("/res/object/heart/heart_full.png", gp.tileSize, gp.tileSize);
        image2 = setup("/res/object/heart/heart_half.png", gp.tileSize, gp.tileSize);
        image3 = setup("/res/object/heart/heart_blank.png", gp.tileSize, gp.tileSize);
    }
}
