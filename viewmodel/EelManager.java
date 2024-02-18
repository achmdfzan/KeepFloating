package viewmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.Eel;
import model.EelBlue;
import model.EelGreen;
import model.EelRed;
import model.EelYellow;

/**
 *
 * @author fauzan
 * 
**/

public class EelManager
{
    // deklarasi atribut
    private Game game;
    private Random rand = new Random();
    
    private final ArrayList<Eel> eels = new ArrayList<>();
    private final int MAX_COUNT = 5;

    private int idCount = 0;
    private int eelCount = 0;
    
    // constructor
    public EelManager(Game game)
    {
        this.game = game;
    }
    
    // getter
    public ArrayList<Eel> getEels()
    {
        return eels;
    }
    
    // method untuk menambahkan eel
    public void addEels()
    {
        if (eelCount < MAX_COUNT)
        {
            int type = 2;
            double y = Game.height;
            
            if (eelCount > 0)
            {
                type = rand.nextInt(4);
                y = eels.get(eelCount - 1).getY() + rand.nextInt(3, 7) * 50;
            }
            
            Eel eel;
            switch(type)
            {
                case 0:
                    eel = new EelRed(-48, y, idCount++);
                break;
                case 1:
                    eel = new EelGreen(-48, y, idCount++);
                break;
                case 2:
                    eel = new EelBlue(-48, y, idCount++);
                break;
                default:
                    eel = new EelYellow(-48, y, idCount++);
                break;
            }
            
            eels.add(eel);
            game.getHandler().add(eel);
            eelCount++;
        }
    }
    
    // method untuk menghapus eel
    public void removeEels()
    {
        Iterator<Eel> it = eels.iterator();
        while (it.hasNext())
        {
            Eel pl = it.next();
            if (pl.getY() < -50)
            {
                it.remove();
                eelCount--;
            }
        }
    }
}
