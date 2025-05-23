package main;

public class GamePanel extends javax.swing.JPanel implements Runnable {

    // SCRREEN SETTINGS
    final int originalTileSize = 16; 
    final int scale = 3; 

    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 20; 
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    // WORLD SETTINGS
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50; 
    public final int worldWith = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 

    // FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    java.awt.image.BufferedImage tempScreen;
    java.awt.Graphics2D g2;     

    // FPS (Frames Per Second) settings
    int FPS = 60; 

    // SYSTEM
    tile.TileManager tileManager = new tile.TileManager(this); 
    public KeyHandler keyHandler = new KeyHandler(this); 
    public UI ui = new UI(this);
    Thread gameThread; 
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);

    // PLAYER, ENTITY, OBJECT
    public entity.Player player = new entity.Player(this, keyHandler); 
    public entity.Entity obj[] = new entity.Entity[20];
    public entity.Entity npc[] = new entity.Entity[10];
    public entity.Entity monster[] = new entity.Entity[20];
    java.util.ArrayList<entity.Entity> entityList = new java.util.ArrayList<>(); 
    public java.util.ArrayList<entity.Entity> projectileList = new java.util.ArrayList<>(); 
    public java.util.ArrayList<entity.Entity> particleList = new java.util.ArrayList<>();  
    public tile_interactive.Interactive iTile[] = new tile_interactive.Interactive[50];
    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 0;
    public final int characterState = 4;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        setUpGame();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); 
    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        gameState = titleState;

        tempScreen = new java.awt.image.BufferedImage(screenWidth, screenHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        g2 = (java.awt.Graphics2D)tempScreen.getGraphics();

    }

    @Override
    public void run() { 
        double drawInterval = 1000000000 / FPS; 
        double delta = 0; 
        long lastTime = System.nanoTime(); 
        long currentTime; 
        long timer = 0; 
        int drawCount = 0; 

        while(gameThread != null) { 
            
            currentTime = System.nanoTime(); 
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); 
            lastTime = currentTime; 
            if (delta >= 1) { 
                update();
                drawToTempScreen();
                drawToScreen();
                delta--; 
                drawCount++; 
            }
            if (timer >= 1000000000) { 
                System.out.println("FPS: " + drawCount); 
                drawCount = 0; 
                timer = 0; 
            }
        }
    }

    public void update() {
        // Update game logic here (e.g., player movement, enemy AI, etc.)
        if (gameState == playState) {
            // PLAYER
            player.update(); 

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) { npc[i].update(); }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false){
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // ...
        }
    }

    public void drawToTempScreen() {
        // TITLE SCREEN
        if (gameState == titleState) { ui.draw(g2); }

        // OTHER
        else {
            // TILE 
            tileManager.draw(g2); 
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].draw(g2);
                }
            }
            
            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            // SORT
            java.util.Collections.sort(entityList, new java.util.Comparator<entity.Entity>() {
                @Override
                public int compare(entity.Entity o1, entity.Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });

            // DRAW SETTING
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);
        }
    }
    public void drawToScreen() {
        java.awt.Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
}