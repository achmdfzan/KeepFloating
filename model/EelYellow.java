package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author fauzan
 * 
**/

public class EelYellow extends Eel
{
    // constructor
    public EelYellow(double x, double y, int id)
    {
        super(x, y, 288 + 48, 48, "YellowEel", id);
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        Image eelImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/yellow_eel.png"));
        object.drawImage(eelImage, (int)x, (int)y, null);
    }
    
    // method Eel
    @Override
    public int getScore()
    {
        return 36;
    }
}
