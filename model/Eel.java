package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author fauzan
 * 
**/
public abstract class Eel extends GameObject
{
    // deklarasi atribut
    private final int id;
    
    // constructor
    public Eel(double x, double y, double width, double height, String type, int id)
    {
        super(x, y, width, height, type);
        this.id = id;
        this.velY = -2.5;
    }
    
    // getter
    public int getID()
    {
        return id;
    }
    
    public int getScore()
    {
        return 0;
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        object.setColor(Color.WHITE);
        object.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    
    @Override
    public void loop()
    {
        this.x += this.velX;
        this.y += this.velY;
        
        updateBoxCollider();
    }
    
    // method kosong untuk di override oleh child class
    public void react()
    {
    }
    
    // method tambahan untuk memperhalus gerakan
    public double lerp(double start, double end, double t)
    {
        return start + t * (end - start);
    }
}
