package viewmodel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.Clip;

import model.GameInterface;
import model.GameObject;
import model.Player;
import model.Eel;

/**
 *
 * @author fauzan
 * 
**/

public class Handler implements GameInterface
{
    // deklarasi atribut
    private ArrayList<GameObject> object; // Array / List of GameObject.
    private Random rand;                  // Randomizer.
    private Game game;
    private Player player;
    private EelManager eelManager;
    
    private Boolean pressedUp = false;
    private Boolean pressedLeft = false;
    private Boolean pressedRight = false;
    private Boolean onGround = false;
    private int prevEel = -1;
    
    private Clip jumpClip;
    private Clip stickyClip;
    
    // constructor
    public Handler(Game game)
    {
        this.object = new ArrayList<>();
        this.rand = new Random();
        this.game = game;
        this.player = game.getPlayer();
        this.eelManager = game.getEelManager();
    }
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    public void setPressedUp(Boolean pressedUp) {
        this.pressedUp = pressedUp;
    }

    public void setPressedLeft(Boolean pressedLeft) {
        this.pressedLeft = pressedLeft;
    }

    public void setPressedRight(Boolean pressedRight) {
        this.pressedRight = pressedRight;
    }
    
    /**
     * Object access and manipulations.
     */
    
    // Add object to list.
    public void add(GameObject object)
    {
        this.object.add(object);
    }
    
    // Access object from list.
    public GameObject get(int i)
    {
        return this.object.get(i);
    }
    
    // Count total object on list.
    public int count()
    {
        return this.object.size();
    }
    
    // Remove object from list based on its index.
    public void remove(int i)
    {
        this.object.remove(i);
    }
    
    // Remove object from list.
    public void remove(GameObject object)
    {
        this.object.remove(object);
    }
    
    public double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }
    
    public void movePlayer()
    {
        player.setVelY(player.getVelY() + 0.5);
        if (player.getVelY() > 2.5) player.setVelY(5);
        
        double xSpeed = 0;
        if ((pressedLeft && pressedRight) || (!pressedLeft && !pressedRight))
        {
            xSpeed = lerp(player.getVelX(), 0, 0.1);
        }
        else if (pressedLeft)
        {
            xSpeed = lerp(player.getVelX(), -player.getHorizontalSpeed(), 0.1);
        }
        else if (pressedRight)
        {
            xSpeed = lerp(player.getVelX(), player.getHorizontalSpeed(), 0.1);
        }
        
        player.setAdditionalVelocity(lerp(player.getAdditionalVelocity(), 0, 0.1));
        
        for(Eel eel : eelManager.getEels())
        {
            if(player.getBoundBottom().intersects(eel.getBoxCollider()))
            {
                
                if (prevEel != eel.getID())
                {
                    game.setScore(game.getScore() + eel.getScore());
                    game.setStanding(game.getStanding() + 1);
                    
                    eel.react();
                    
                    prevEel = eel.getID();
                    
                    if (eel.getType() == "GreenEel")
                    {
                        
                    }
                }
                
                onGround = true;
                player.setVelY(0);
                player.setY(eel.getBoxCollider().y - player.getHeight());
                
                player.setAdditionalVelocity(eel.getVelX());
                
                if (eel.getType() == "GreenEel")
                {
                    xSpeed = 0;
                }
                
                if (pressedUp)
                {
                    Sound.playSound(jumpClip, "jump.wav", false);
                    player.setVelY(-player.getJumpForce());
                    onGround = false;
                    
                    if (eel.getType() == "RedEel")
                    {
                        eel.react();
                    }
                }
            }
            else if(player.getBoundTop().intersects(eel.getBoxCollider()))
            {
                player.setVelY(0);
            }
            else if(player.getBoundLeft().intersects(eel.getBoxCollider()))
            {
                player.setVelX(0);
                player.setX(eel.getBoxCollider().x + eel.getBoxCollider().width + 1);
            }
            else if(player.getBoundRight().intersects(eel.getBoxCollider()))
            {
                player.setVelX(0);
                player.setX(eel.getBoxCollider().x - player.getWidth() - 1);
            }
        }
        
        player.setVelX(xSpeed);
    }
    
    public void CheckGameState()
    {
        double offset = player.getHeight() * 0.5;
        if (player.getBoundBottom().y > Game.height + offset ||
            player.getBoundTop().y < -offset)
        {
            game.stopGame();
        }
    }
    
    /**
     * 
     * Override interface.
     */
    
    @Override
    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject temp;
            temp = object.get(i);
            
            temp.render(g);
        }
    }
    
    @Override
    public void loop()
    {
        eelManager.removeEels();
        eelManager.addEels();
        
        this.movePlayer();
        this.CheckGameState();
        
        for(int i = 0; i < object.size(); i++)
        {
            GameObject temp;
            temp = object.get(i);
            
            temp.loop();
        }
    }
}
