package entity;

public class NPC_Merchant extends Entity {
    public NPC_Merchant(main.GamePanel gp) {
        super(gp);

        type = 1;
        direction = "down";
        speed = 0;

        getMerchantImage();
        setDialogue();
        setItems();

        solidArea = new java.awt.Rectangle(0, 16, 48, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getMerchantImage() {
        up1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
    }

    public void setItems() {
        inventory.add(new object.OBJ_Potion_Red(gp));
        inventory.add(new object.OBJ_Key(gp));
        inventory.add(new object.OBJ_Sword_Normal(gp));
        inventory.add(new object.OBJ_Axe(gp));
        inventory.add(new object.OBJ_Shield_Wood(gp));
        inventory.add(new object.OBJ_Shiled_Blue(gp));
    }
}
