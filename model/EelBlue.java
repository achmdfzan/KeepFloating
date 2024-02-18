package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author fauzan
 * 
**/

public class EelBlue extends Eel
{
    // deklarasi atribut
    private int horizontalSpeed = 0;
    
    // constructor
    public EelBlue(double x, double y, int id)
    {
        super(x, y, 576 + 48, 48, "BlueEel", id);
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        Image eelImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/blue_eel.png"));
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
        horizontalSpeed = -5;
    }
    
    @Override
    public int getScore()
    {
        return 12;
    }
}
