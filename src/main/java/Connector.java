import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connector Class which helps connect to database.
 * @author huangdun
 */
public final class Connector {
    /**
     * constructor.
     */
    private Connector() {
    }

    /**
     * getConnection method which returns a connector to connect to database.
     * @return a connector to help connect to database
     * @throws ClassNotFoundException oh yeah
     */
    public static Connection getConnection() throws ClassNotFoundException {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://buzzmoviesselector.c37rymkezk"
                + "94.us-east-1.rds.amazonaws.com:5432/CS2340db";
        String user = "postgres";
        Properties props = new Properties();
        String password = "";
        try {
            try (InputStream in = Connector
                    .class.getResourceAsStream("db.properties")) {
                if (in == null) {
                    throw new
        NullPointerException("Couldn't find /db.properties");
                }
                props.load(in);
                password = props.getProperty("dbPassword");
            }
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        try {
           con = DriverManager.getConnection(url, user, password);
           System.out.println("Connection completed.");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return con;
    }
}
