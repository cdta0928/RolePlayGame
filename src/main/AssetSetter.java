package main;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Lantern(gp));
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;
        // gp.obj[mapNum][i] = new object.OBJ_Boots(gp);
        // gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 42 * gp.tileSize; i++;
        // gp.obj[mapNum][i] = new object.OBJ_Axe(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize; i++;
        // gp.obj[mapNum][i] = new object.OBJ_Shiled_Blue(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 21 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Lantern(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 21 * gp.tileSize;  
        // i++;
        // gp.obj[mapNum][i] = new object.OBJ_Tent(gp);
        // gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 20 * gp.tileSize;  
        // i++;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new entity.NPC_GirlFriend(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 3;
        gp.npc[mapNum][i].worldY = gp.tileSize * 3; i++;

        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new entity.NPC_Trader(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7; i++;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 4;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 4;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 9;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 9;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;

        // mapNum = 1;
        // gp.monster[mapNum][i] = new monster.MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        // i++;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 16, 13); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 15, 13); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 15, 12); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 15, 11); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 15, 10); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 14, 10); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 13, 10); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 10); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 9); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 8); i++;

        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 9, 17); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 10, 17); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 11, 17); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 17); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 16); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 15); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 14); i++;
        gp.iTile[mapNum][i] = new tile_interactive.IT_DryTree(gp, 12, 13); i++;
    }
}
