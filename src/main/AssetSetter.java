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
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Shield_Iron(gp));
        gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 2 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Axe(gp));
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new object.OBJ_Chest(gp, new object.OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        i++;
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
        gp.monster[mapNum][i].worldY = gp.tileSize * 30; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 29;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36; i++;
        gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32; i++;

        gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41; i++;
        gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 40;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39; i++;
        gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38; i++;
        gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31; i++;
        gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34; i++;

        // gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 37;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 20; i++;

        // gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 19; i++;

        // gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 41;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 17; i++;

        // gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 21; i++;

        // gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 18; i++;

        gp.monster[mapNum][i] = new monster.MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 37;
        gp.monster[mapNum][i].worldY = gp.tileSize * 19; i++;

        // for (int ii = 32; ii <= 39; ii++) {
        //     for (int j = 5; j <= 8; j++) {
        //         int r = new java.util.Random().nextInt(100) + 1;
        //         if (r >= 50) {
        //             gp.monster[mapNum][i] = new monster.MON_Spider(gp);
        //         }
        //         else {
        //             gp.monster[mapNum][i] = new monster.MON_Slime(gp);
        //         }
        //         gp.monster[mapNum][i].worldX = gp.tileSize*ii;
        //         gp.monster[mapNum][i].worldY = gp.tileSize*j;
        //         i++;
        //     }
        // }
        mapNum = 2;
        gp.monster[mapNum][i] = new monster.MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5; i++;
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
