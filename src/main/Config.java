package main;

public class Config {
    GamePanel gp;
    
    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("config.txt"));
            // Full screen
            if (gp.fullScreenOn == true) {
                bw.write("On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();
            bw.close();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadConfig() {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("config.txt"));
            String s = br.readLine();
            // Full screen
            if (s.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }
            br.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
