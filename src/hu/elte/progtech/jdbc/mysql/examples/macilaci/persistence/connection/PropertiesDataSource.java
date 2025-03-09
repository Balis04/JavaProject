package hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class PropertiesDataSource {
    /**
     * Az adatbázis elérése. (Minta Derby esetén: jdbc:derby://localhost:1527/BandViewer )
     */
    private final String url;
    /**
     * A felhasználó neve.
     */
    private final String user;
    /**
     * A felhasználóhoz tartozó jelszó.
     */
    private final String password;

    PropertiesDataSource(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("The given properties file should not be null!");
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");
    }

    /**
     * Metódus, mely segítségével egy munkamenetet tudunk kérni az adatbázisunkhoz.
     * Ezen a kapcsolaton keresztül tudunk lekérdezéseket futtatni
     * @throws SQLException ha nem sikerült elérni az adatbázist vagy valamilyen hozzáférési probléma adódott, illetve ha
     *                      az url nem volt töltve.
     */
    Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver nem található: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, password);
    }
}