package model;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author fauzan
 * 
 */

public class GameObject implements GameInterface
{
    // deklarasi atribut
    protected double x, y;              // posisi
    protected double width, height;     // ukuran
    protected double velX, velY;        // kecepatan
    protected String type;              // tipe objek
    protected Rectangle boxCollider;    // collision box
    
    // constructor
    public GameObject(double x, double y, double width, double height, String type)
    {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.type = type;
        this.boxCollider = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
    
    // getter
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
        
    public double getVelX()
    {
        return velX;
    }
        
    public double getVelY()
    {
        return velY;
    }
    
    public String getType()
    {
        return type;
    }
    
    public Rectangle getBoxCollider()
    {
        return this.boxCollider;
    }
    
    // setter
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
        
    public void setWidth(double width)
    {
        this.width = height;
    }
    
    public void setHeight(double height)
    {
        this.height = height;
    }

    public void setVelX(double velX)
    {
        this.velX = velX;
    }

    public void setVelY(double velY)
    {
        this.velY = velY;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    // method lain
    protected void updateBoxCollider()
    {
        this.boxCollider.x = (int) x;
        this.boxCollider.y = (int) y;
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        
    }
    
    @Override
    public void loop()
    {
        
    }
}
