package entity;

public class Particle extends Entity {
    main.GamePanel gp;
    Entity generator;
    java.awt.Color color;
    int size;
    int speed;
    int maxLife;
    int xd;
    int yd;

    public Particle(main.GamePanel gp, Entity generator, java.awt.Color color, int size, int speed,
        int maxLife, int xd, int yd) {
        super(gp);
        this.gp = gp;
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        worldX = generator.worldX;
        worldY = generator.worldY;
    }
    
}
