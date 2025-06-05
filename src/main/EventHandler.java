package main;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    
    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;

            if (col == gp.maxWorldCol) {
                row++;
                col = 0;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);

        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) { canTouchEvent = true; }

        if (canTouchEvent == true) {
            if (hit(0, 28, 33, "any") == true) {
                healingPool(28,33, gp.dialogueState);
            }
            else if (hit(0, 11, 8, "any") == true) {
                teleport(1, 12, 13, gp.indoor);
            }
            else if (hit(1, 12, 13, "any") == true) {
                teleport(0, 11, 8, gp.outside);
            }
            else if (hit(1, 12, 9, "up") == true || hit(1, 12, 8, "any")) {
                speak(gp.npc[1][0]);
            }
            else if (hit (0, 45, 7, "any") == true) {
                teleport(2, 4, 4, gp.dungeon);
            }
            else if (hit(2, 4, 4, "any") == true) {
                teleport(0, 45, 7, gp.outside);
            }
            // if (hit (26, 15, "any")) {
            //     teleport(26, 15, gp.dialogueState);
            // }
        }
    }

    public void speak(entity.Entity entity) {
        if (gp.keyHandler.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;  
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyHandler.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\nYour life and mana has been recovered.";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.player.attackCanceled = true;
            gp.aSetter.setMonster();
        }
        // canTouchEvent = false;
    }

    public void teleport(int map, int col, int row, int area) {
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        // gp.currentMap = map;
        // gp.gameState = gameState;
        // gp.ui.currentDialogue = "Teleport!";
        // gp.player.worldX = gp.tileSize*col;
        // gp.player.worldY = gp.tileSize*row;
        // previousEventX = gp.player.worldX;
        // previousEventY = gp.player.worldY;
        // eventRect[map][col][row].eventDone = true;
        canTouchEvent = false;
    }
}
