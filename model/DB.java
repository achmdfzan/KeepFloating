package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fauzan
 * 
**/

// class untuk mengatur koneksi ke database
public class DB
{
    //deklarasi atribut
    private String connAddress = "jdbc:mysql://localhost/db_keep_floating?user=root&password=";
    private Connection conn = null; // koneksi dengan database
    private ResultSet rs = null;    // menyimpan hasil
    private Statement stmt = null;  // menyimpan perintah yang akan dijalankan
    
    // constructor
    public DB() throws Exception, SQLException
    {
        try
        {
            // buat driver mysql
            Class.forName("com.mysql.jdbc.Driver");
            // memulai koneksi dengan mysql
            conn = DriverManager.getConnection(connAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        }
        catch (SQLException es)
        {
            // error jika koneksi gagal
            throw es;
        }
    }
    
    // method untuk malakukan query bersifat read-only
    public void createQuery(String Query) throws Exception, SQLException
    {
        try
        {
            stmt = conn.createStatement();
            // eksekusi query
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query))
            {
                // ambil hasil query
                rs = stmt.getResultSet();
            }
        }
        catch (SQLException e)
        {
            // error jika query gagal di eksekusi
            throw e;
        }
    }
    
    // method untuk melakukan query bersifat read/write
    public void createUpdate(String Query)throws Exception, SQLException
    {
        try
        {
            stmt = conn.createStatement();
            // eksekusi query
            int res = stmt.executeUpdate(Query);
        } catch (SQLException e)
        {
            // eksekusi jika query gagal di eksekusi
            throw e;
        }
    }
    
    // method untuk mendapatkan hasil dari query yang telah dijalankan
    public ResultSet getResult() throws Exception
    {
        ResultSet Temp = null;
        try
        {
            return rs;
        }
        catch (Exception e)
        {
            // eksepsi jika hasil tidak dapat dikembalikan
            return Temp;
        }
    }
    
    // method untuk menutup variabal yang digunakan dalam proses query
    public void closeResult() throws Exception, SQLException
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException es)
            {
                rs = null;
                throw es;
            }
        }
        if(stmt != null)
        {
            try 
            {
                stmt.close();
            }
            catch (SQLException es)
            {
                stmt = null;
                throw es;
            }
        }
    }
    
    // method untuk menutup koneksi dengan database
    public void closeConnection() throws Exception, SQLException
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException es)
            {
                conn = null;
            }
        }
    }
}
