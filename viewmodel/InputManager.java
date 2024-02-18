package viewmodel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author fauzan
 * 
**/
public class InputManager extends KeyAdapter implements KeyListener
{
    /**
     * Attribute declaration.
     */
    
    private Game game;
    private Handler handler;
    
    /**
     * Constructor.
     */
    
    // Constructor with controller data.
    public InputManager(Game game, Handler handler)
    {
        this.game = game;
        this.handler = handler;
    }
    
    /**
     * Getter and Setter.
     */

    /* InputManager's game. */
    
    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    /* InputManager's handler. */
    
    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }
    
    /**
     * Public methods.
     */
    
    // Override trait when key is pressed.
    @Override
    public synchronized void keyPressed(KeyEvent e)
    {        
        // Get key code (what key that pressed?).
        int key = e.getKeyCode();
        if(game.isRunning())
        {
            if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP))
            {
                handler.setPressedUp(true);
            }                
            if((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT))
            {
                handler.setPressedLeft(true);
            }
            if((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT))
            {
                handler.setPressedRight(true);
            }
        }
    }
    
    // Override trait when key is released from being pressed.
    @Override
    public synchronized void keyReleased(KeyEvent e)
    {        
        // Get key code (what key that released?).
        int key = e.getKeyCode();
        if(game.isRunning())
        {         
            if(key == KeyEvent.VK_SPACE)
            {
                game.stopGame();
            }
            if((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP))
            {
                handler.setPressedUp(false);
            }
            if((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT))
            {
                handler.setPressedLeft(false);
            }
            if((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT))
            {
                handler.setPressedRight(false);
            }
        }
    }
}
