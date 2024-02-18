package model;

import viewmodel.Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *
 * @author fauzan
 * 
 */

public class Player extends GameObject
{
    // deklarasi atribut
    private double additionalVelocity = 0;
    private double horizontalSpeed = 7.5;
    private double jumpForce = 12.5;
    
    // constructor
    public Player(double x, double y, double width, double height)
    {
        super(x, y, width, height, "Player");
        super.setWidth(width);
        super.setHeight(height);
    }
    
    // getter
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public double getJumpForce() {
        return jumpForce;
    }

    public double getAdditionalVelocity() {
        return additionalVelocity;
    }

    public Rectangle getBoundBottom(){
        return new Rectangle((int) ((int)x+((int)width/2)-((int)width/4)), (int) ((int)y+((int)height/2)), (int)width/2, (int)height/2);
    }
    
    public Rectangle getBoundTop(){
        return new Rectangle((int) ((int)x+((int)width/2)-((int)width/4)), (int) (y), (int)width/2, (int)height/2);
    }
    
    public Rectangle getBoundRight(){
        return new Rectangle((int) (int)x+(int)width-5, (int)y + 5, 5, (int)height-10);
    }
    
    public Rectangle getBoundLeft(){
        return new Rectangle((int) x, (int)y + 5, 5, (int)height-10);
    }
    
    // setter 
    public void setAdditionalVelocity(double additionalVelocity) {
        this.additionalVelocity = additionalVelocity;
    }
    
    // method GameInterface
    @Override
    public void render(Graphics object)
    {
        Image playerImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/crab.png"));
        object.drawImage(playerImage, (int)x, (int)y, null);
    }
    
    @Override
    public void loop()
    {   
        this.x += (this.velX + this.additionalVelocity);
        this.y += this.velY;
        
        x = Game.clamp(x, 0, (Game.width - 60));
        
        updateBoxCollider();
    }
}
