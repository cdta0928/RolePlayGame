package main;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        // gp.obj[mapNum][i] = new object.OBJ_Key(gp);
        // gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_ManaCrystal(gp);
        // gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Heart(gp);
        // gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        // i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
        i++;
        // gp.obj[mapNum][i] = new object.OBJ_Chest(gp);
        // gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        // i++;
        gp.obj[mapNum][i] = new object.OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize; i++;
        gp.obj[mapNum][i] = new object.OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize; i++;
        // gp.obj[mapNum][i] = new object.OBJ_Shiled_Blue(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 21 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Coin_Bronze(gp);
        // gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize;  
        // i++;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new entity.NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 21; i++;

        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new entity.NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7; i++;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36; i++;
        gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37; i++;
        gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37; i++;
        gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42; i++;
        gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

        // mapNum = 1;
        // gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        // i++;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 27, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 28, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 29, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 30, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 31, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 32, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 33, 7); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 17, 40); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 16, 40); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 15, 40); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 14,40); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 14, 39); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 13, 39); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 39); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 11, 39); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 11, 38); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 29, 21); i++;
    }
}
