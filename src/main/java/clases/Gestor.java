package clases;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Gestor {

    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/prueba";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    private static Gestor instance;

    private Gestor() {
    }

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        try {

            if (connection == null || connection.isClosed()) {

                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public  static Gestor getInstance() {
        if (instance == null) {
            instance = new Gestor();
        }
        return instance;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet query(String sql) {
        try {
            return connect().createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public boolean trans(String sql) {
        try {
            return connect().createStatement().execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

}
