package environmemt;

public class EnvironmentManager {
    main.GamePanel gp;
    Lightning lightning;

    public EnvironmentManager(main.GamePanel gp) {
        this.gp = gp;
    }
    public void setup() {
        lightning = new Lightning(gp, 350);
    }
    public void draw(java.awt.Graphics2D g2) {
        lightning.draw(g2);
    }
}
