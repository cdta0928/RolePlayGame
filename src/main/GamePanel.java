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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 
    public final int maxMap = 10;
    public int currentMap = 0;

    // FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    java.awt.image.BufferedImage tempScreen;
    java.awt.Graphics2D g2;     
    public boolean fullScreenOn = false;

    // FPS (Frames Per Second) settings
    int FPS = 60; 

    // SYSTEM
    public tile.TileManager tileManager = new tile.TileManager(this); 
    public KeyHandler keyHandler = new KeyHandler(this); 
    public UI ui = new UI(this);
    Thread gameThread; 
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public ai.PathFinder pFinder = new ai.PathFinder(this);
    environmemt.EnvironmentManager eManager = new environmemt.EnvironmentManager(this);

    // PLAYER, ENTITY, OBJECT
    public entity.Player player = new entity.Player(this, keyHandler); 
    public entity.Entity obj[][] = new entity.Entity[maxMap][20];
    public entity.Entity npc[][] = new entity.Entity[maxMap][10];
    public entity.Entity monster[][] = new entity.Entity[maxMap][20];
    java.util.ArrayList<entity.Entity> entityList = new java.util.ArrayList<>(); 
    public entity.Entity projectile[][] = new entity.Entity[maxMap][20];
    // public java.util.ArrayList<entity.Entity> projectile[currentMap] = new java.util.ArrayList<>(); 
    public java.util.ArrayList<entity.Entity> particleList = new java.util.ArrayList<>();  
    public tile_interactive.Interactive iTile[][] = new tile_interactive.Interactive[maxMap][50];
    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 0;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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
        eManager.setup();
        gameState = titleState;

        tempScreen = new java.awt.image.BufferedImage(screenWidth, screenHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        g2 = (java.awt.Graphics2D)tempScreen.getGraphics();
        if (fullScreenOn == true) {
            setFullScreen();
        }
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
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) { npc[currentMap][i].update(); }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectile[currentMap].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if (projectile[currentMap][i].alive == false) {
                        projectile[currentMap][i] = null;
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
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
        }
        eManager.update();
        if (gameState == pauseState) {
            // ...
        }
    }

    /*
     * Retry
     * Keep: level, aquiredItems, Progress, ...
     * Reset: Positions, Life/Mana, Monsters/NPCs
     */

    /*
     * Restart
     * Keep: Nothing
     */

    public void retry() {
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void restart() {
        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }
    
    public void drawToTempScreen() {
        // TITLE SCREEN
        if (gameState == titleState) { ui.draw(g2); }

        // OTHER
        else {
            // TILE 
            tileManager.draw(g2); 
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }
            
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectile[currentMap].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
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

            eManager.draw(g2);

            // UI
            ui.draw(g2);
        }
    }

    public void drawToScreen() {
        java.awt.Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void setFullScreen() {
        // GET LOCAL SCREEN DEVICE
        java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
}