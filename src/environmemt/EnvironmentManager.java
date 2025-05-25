package environmemt;

public class EnvironmentManager {
    main.GamePanel gp;
    public Lightning lightning;

    public EnvironmentManager(main.GamePanel gp) {
        this.gp = gp;
    }
    public void setup() {
        lightning = new Lightning(gp); // Kích thưóc vòng sáng
    }
    public void draw(java.awt.Graphics2D g2) {
        lightning.draw(g2);
    }
    public void update() {
        lightning.update();
    }
}
