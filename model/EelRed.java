package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author fauzan
 * 
**/

public class EelRed extends Eel
{
    // deklarasi atribut
    private Boolean playerJumped = false;
    private int horizontalSpeed = 0;
    
    // constructor
    public EelRed(double x, double y, int id)
    {
        super(x, y, 144 + 48, 48, "RedEel", id);
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        Image eelImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/red_eel.png"));
        object.drawImage(eelImage, (int)x, (int)y, null);
    }
    
    @Override
    public void loop()
    {
        velX = lerp(velX, horizontalSpeed, 0.1);
        super.loop();
    }
    
    // method Eel
    @Override
    public void react()
    {
        if (playerJumped)
        {
            horizontalSpeed = 15;
        }
        else
        {
            playerJumped = true;
        }
    }
    
    @Override
    public int getScore()
    {
        return 48;
    }
}

