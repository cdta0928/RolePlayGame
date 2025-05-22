package object;

public class OBJ_ManaCrystal extends entity.Entity {
    main.GamePanel gp;

    public OBJ_ManaCrystal(main.GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("/res/projectile/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/projectile/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
}
