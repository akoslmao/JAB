	package DBLayer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DbConnection {

	private static final String driver = "jdbc:sqlserver://kraka.ucn.dk:1433";
    private static final String databaseName = ";databaseName=dmaj0917_1067658";

    private static String userName = ";user=dmaj0917_1067658";
    private static String password = ";password=Password1!";

    private DatabaseMetaData dma;
    private static Connection con;
    
    private static DbConnection instance = null;
    
    private DbConnection() {
        String url = driver + databaseName + userName + password;

        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver class loaded ok");

        } catch (Exception e) {
            System.out.println("Cannot find the driver");
            System.out.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            dma = con.getMetaData();
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            
        }
        catch (Exception e) {
            System.out.println("Problems with the connection to the database:");
            System.out.println(e.getMessage());
            System.out.println(url);
        }
    }
    
    public void connect() throws Exception
    {
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver couldn't load");
		}
    	
    	String url = driver + databaseName + userName + password;
    	con = DriverManager.getConnection(url);
    }
    
    public static void closeConnection() {
        try {
            con.close();
            instance = null;
            System.out.println("The connection is closed");
        } catch (Exception e) {
            System.out.println("Error trying to close the database " + e.getMessage());
        }
    }
    
    public Connection getDBcon() {
        return con;
    }
    
    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }
	
	
}

