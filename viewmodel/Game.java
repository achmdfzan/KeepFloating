package viewmodel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.Toolkit;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import model.Player;
import model.TableScore;
import view.MenuFrame;
import view.GameFrame;

/**
 *
 * @author fauzan
 * 
**/

public class Game extends Canvas implements Runnable
{
    // enum untuk menyimpan state pada game
    public enum GameState
    {
        running,    // sedang berjalan
        gameOver    // sudah selesai
    }
    
    // deklarasi atribut
    public static final int width = 720;
    public static final int height = 720;
    
    private GameFrame gameFrame;
    private Handler handler;
    private Player player;
    private EelManager eelManager;
    private Thread thread;
    private Clip backgroundMusicClip;
    private Clip gameOverSoundClip;
    
    private boolean running;
    private boolean startCounting = false;
    private int score = 0;
    private int standing = 0;
    private int counter = 0;
    private int stateCounter = 0;
    private int direction = 0;
    private String username;
    
    private GameState gameState = GameState.running;
    
    // constructor
    public Game()
    {
        try
        {
            this.player = new Player(width/2 - 20, 0, 56, 48);
            this.eelManager = new EelManager(this);

            this.handler = new Handler(this);
            
            this.gameFrame = new GameFrame(width, height, "Keep Standing");
            this.gameFrame.open(this);
            
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new InputManager(this, handler));
            
            this.running = true;

            this.handler.add(player);
            
            this.backgroundMusicClip = Sound.playSound(this.backgroundMusicClip, "background_music.wav", true);
        }
        catch(Exception e)
        {
            System.err.println("Failed to instantiate data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Handler getHandler() {
        return handler;
    }

    public Player getPlayer() {
        return player;
    }

    public EelManager getEelManager() {
        return eelManager;
    }
    
    // batasi posisi player
    public static double clamp(double var, double min, double max)
    {
        if(var >= max)
        {
            return var = max;
        }
        else if(var <= min)
        {
            return var = min;
        }
        
        return var;
    }
    
    // Close gameFrame.
    public void close()
    {
        gameFrame.close();
    }
    
    // Start threading.
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start(); running = true;
    }
    
    // Stop threading.
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread error : " + e.getMessage());
        }
    }
    
    public void stopGame()
    {        
        Sound.stopSound(backgroundMusicClip);
        Sound.playSound(gameOverSoundClip, "game_over.wav", false);

        this.gameState = GameState.gameOver;
    }
    
    public void saveScore()
    {
        try
        {
            TableScore tscore = new TableScore();
            tscore.insertOrUpdate(this.username, this.score, this.standing);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        JOptionPane.showMessageDialog(null, "Username : " + this.username + "\nScore : " + this.score + "\nStanding : " + this.standing, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Initialize game when it run for the first time.
    public void render()
    {
        // Use buffer strategy.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(4);
            return;
        }
        
        // Initialize graphics.
        Graphics g = bs.getDrawGraphics();
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/ocean_background.png"));
        g.drawImage(bg, 0, 0, null);
        
        if(running == true)
        {
            // Render handler.
            this.handler.render(g);
            
            // Render score.
            Font oldFont = g.getFont();
            Font newFont = oldFont.deriveFont(oldFont.getSize() * 1.3f);
            g.setFont(newFont);
            
            g.setColor(Color.BLACK);
            g.drawString("Score : " + Integer.toString(score), width/2 - 100, 30);
            g.drawString("Standing : " + Integer.toString(standing), width/2 + 10, 30);
        }
        
        // Loop the process so it seems like "FPS".
        g.dispose();
        bs.show();
    }
    
    // Main loop proccess.
    public void loop()
    {
        this.handler.loop();
        if(this.running)
        {   
            counter++;
            if(startCounting)
            {
                stateCounter++;
            }
            
            if(stateCounter >= 40)
            {
                stateCounter = 0;
                startCounting = false;
            }
            
            if(counter >= 50)
            {
                direction = (direction == 0) ? 1 : 0;
                counter = 0;
            }
        }
        if(gameState == GameState.gameOver)
        {
            saveScore();
            
            Sound.stopSound(backgroundMusicClip);
                        
            close();
            new MenuFrame().setVisible(true);
            
            stop();
        }
    }
    
    /**
     * 
     * Override interface.
     */
    
    @Override
    public void run()
    {
        double fps = 60.0;
        double ns = (1000000000 / fps);
        double delta = 0;
        
        // Timer attributes.
        long time = System.nanoTime();
        long now = 0;
        long timer = System.currentTimeMillis();
        
        int frames = 0;
        while(running)
        {
            now = System.nanoTime();
            delta += (now - time) / ns;
            time = now;
            
            while(delta > 1)
            {
                loop();
                delta--;
            }
            
            if(running)
            {
                render();
                frames++;
            }
            
            if((System.currentTimeMillis() - timer) > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        
        stop();
    }
}
