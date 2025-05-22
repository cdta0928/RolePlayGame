package main;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;

        gp.obj[i] = new object.OBJ_Key(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 23 * gp.tileSize;
        i++;
        gp.obj[i] = new object.OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = 21 * gp.tileSize;
        gp.obj[i].worldY = 19 * gp.tileSize;
        i++;
        gp.obj[i] = new object.OBJ_Heart(gp);
        gp.obj[i].worldX = 26 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;
        // i++;
        // gp.obj[i] = new object.OBJ_Door(gp);
        // gp.obj[i].worldX = 10 * gp.tileSize;
        // gp.obj[i].worldY = 11 * gp.tileSize;
        // i++;
        // gp.obj[i] = new object.OBJ_Door(gp);
        // gp.obj[i].worldX = 8 * gp.tileSize;
        // gp.obj[i].worldY = 28 * gp.tileSize;
        // i++;
        // gp.obj[i] = new object.OBJ_Door(gp);
        // gp.obj[i].worldX = 12 * gp.tileSize;
        // gp.obj[i].worldY = 22 * gp.tileSize;
        // i++;
        // gp.obj[i] = new object.OBJ_Chest(gp);
        // gp.obj[i].worldX = 10 * gp.tileSize;
        // gp.obj[i].worldY = 7 * gp.tileSize;
        i++;
        gp.obj[i] = new object.OBJ_Boots(gp);
        gp.obj[i].worldX = 37 * gp.tileSize;
        gp.obj[i].worldY = 42 * gp.tileSize;
        i++;
        gp.obj[i] = new object.OBJ_Axe(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;  
        i++;
        gp.obj[i] = new object.OBJ_Shiled_Blue(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 21 * gp.tileSize;  
        i++;
        gp.obj[i] = new object.OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;  
        i++;
        gp.obj[i] = new object.OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;  
        i++;
    }

    public void setNPC() {
        int i = 0;

        gp.npc[i] = new entity.NPC_OldMan(gp);
        gp.npc[i].worldX = gp.tileSize * 21;
        gp.npc[i].worldY = gp.tileSize * 21;  
        i++;
    }

    public void setMonster() {
        int i = 0;

        gp.monster[i] = new monster.MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 20;
        gp.monster[i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[i] = new monster.MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 20;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new monster.MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new monster.MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new monster.MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
    }

    public void setInteractiveTile() {
        int i = 0;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 27, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 28, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 29, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 30, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 31, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 32, 7);
        i++;
        gp.iTile[i] = new tile_interactive.IT_DryTree(gp, 33, 7);
        i++;
    }

}
