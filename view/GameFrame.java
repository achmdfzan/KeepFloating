package view;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

import viewmodel.Game;

/**
 *
 * @author fauzan
 * 
**/

public class GameFrame extends Canvas
{
    // deklarasi atribut
    private JFrame frame;
    
    // constructor
    public GameFrame(int width, int height, String title)
    {
        this.frame = new JFrame(title);        
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        
        this.frame.setResizable(false);
    }
    
    // method untuk memunculkan window dan memulai thread
    public void open(Game game)
    {
        this.frame.add(game);
        this.frame.setVisible(true);
        
        game.start();
    }
    
    // method untuk menutup window
    public void close()
    {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}
