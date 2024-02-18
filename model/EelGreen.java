package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author fauzan
 * 
**/

public class EelGreen extends Eel
{
    // constructor
    public EelGreen(double x, double y, int id)
    {
        super(x, y, 432 + 48, 48, "GreenEel", id);
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        Image eelImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/green_eel.png"));
        object.drawImage(eelImage, (int)x, (int)y, null);
    }
    
    // method Eel
    @Override
    public int getScore()
    {
        return 24;
    }
}
