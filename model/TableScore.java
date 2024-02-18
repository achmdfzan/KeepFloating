package model;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fauzan
 * 
 */

// class untuk manipulasi data di tabel tscore
public class TableScore extends DB
{    
    // deklarasi atribut
    private String tableName;
    
    // constructor
    public TableScore() throws Exception, SQLException
    {
        super();
        this.tableName = "tscore";
    }
    
    // method untuk mengambil semua data pada tabel
    public void select()
    {
        try
        {
            String query = "SELECT * FROM " + this.tableName;
            createQuery(query);
        }
        catch (Exception e)
        {
            // menampilkan error
            System.out.println(e.toString());
        }
    }
    
    // method untuk mengambil semua data dengan username tertentu pada tabel
    public void select(String username)
    {
        try {
            String query = "SELECT * FROM " + this.tableName +" WHERE username='" + username + "'";
            createQuery(query);
        }
        catch (Exception e)
        {
            // menampilkan error
            System.err.println(e.toString());
        }
    }
    
    // method untuk insert atau update
    public void insertOrUpdate(String username, int score, int standing)
    {
        boolean update = false;
        try
        {
            TableScore temp = new TableScore();
            temp.select(username);
            if(temp.getResult().next())
            {
                update = true;
            }
            else
            {
                update = false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(!update)
        {
            try 
            {
                String query = "INSERT INTO " + this.tableName + " VALUES(null, '" + username + "', " + Integer.toString(score) + ", " + Integer.toString(standing) + ")";
                createUpdate(query);
            }
            catch (Exception e)
            {
                // menampilkan error
                System.out.println(e.toString());
            }
        }
        else if(update)
        {
            try
            {
                String query = "UPDATE " + this.tableName + " SET score=" + score + ", standing=" + standing + " WHERE username='" + username + "'";
                createUpdate(query);
            }
            catch (Exception e)
            {
                // menampilkan error
                System.out.println(e.getMessage());
            }
        }
    }
    
    // method untuk menyiapkan data untuk ditampilkan pada UI
    public DefaultTableModel getTableData()
    {
        DefaultTableModel tableData = null;
        try
        {
            Object[] column = {"USERNAME", "SCORE", "STANDING"};
            tableData = new DefaultTableModel(null, column);
            
            String query = "SELECT * FROM " + this.tableName + " ORDER BY score DESC";
            this.createQuery(query);
            
            while(this.getResult().next())
            {
                Object[] row = new Object[3];
                
                row[0] = this.getResult().getString(2);
                row[1] = this.getResult().getString(3);
                row[2] = this.getResult().getString(4);
                tableData.addRow(row);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return tableData;
    }
}
