package main;

public class GamePanel extends javax.swing.JPanel implements Runnable {

    // SCRREEN SETTINGS
    final int originalTileSize = 16; 
    final int scale = 3; 

    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    // WORLD SETTINGS
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50; 
    public final int worldWith = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 

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
    public entity.Entity obj[] = new entity.Entity[10];
    public entity.Entity npc[] = new entity.Entity[10];
    public entity.Entity monster[] = new entity.Entity[20];
    java.util.ArrayList<entity.Entity> entityList = new java.util.ArrayList<>(); 

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

        gameState = titleState;
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
                repaint();
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
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // ...
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); 
        // Draw game elements here (e.g., tiles, characters, etc.)
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        
        // TITLE SCREEN
        if (gameState == titleState) { ui.draw(g2); }

        // OTHER
        else {
            // TILE 
            tileManager.draw(g2); 
            
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
            g2.dispose(); 
        } 
    }
}