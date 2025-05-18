package object;

public class OBJ_Heart extends entity.Entity {
    
    main.GamePanel gp;

    public OBJ_Heart(main.GamePanel gp) {
        super(gp);
        
        name = "Heart";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/heart/heart_full.png"));
            image2 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/heart/heart_half.png"));
            image3 = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/object/heart/heart_blank.png"));
            
            // Scale images to match the game tile size
            image = scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public java.awt.image.BufferedImage scaleImage(java.awt.image.BufferedImage original, int width, int height) {
        java.awt.Image temp = original.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        java.awt.image.BufferedImage scaledImage = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(temp, 0, 0, null);
        g2.dispose();
        return scaledImage;
    }
    
}
