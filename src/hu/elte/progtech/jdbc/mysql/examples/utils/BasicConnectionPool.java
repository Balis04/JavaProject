package hu.elte.progtech.jdbc.mysql.examples.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
    private static final int INITIAL_CONNECTIONS_NUMBER = 5;
    private String url;
    private String user;
    private String password;
    private List<Connection> connections;
    private List<Connection> usedConnections = new ArrayList<>();

    public static BasicConnectionPool createConnectionPool(final String url, final String user, final String password) throws SQLException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("DB URL can't be null or empty!");
        }
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("DB Username can't be null or empty!");
        }
        if (password == null) {
            throw new IllegalArgumentException("DB Password can't be null!");
        }
        List<Connection> connectionPool = new ArrayList<>(INITIAL_CONNECTIONS_NUMBER);
        for (int i = 0; i < INITIAL_CONNECTIONS_NUMBER; i++) {
            connectionPool.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool(url,user,password,connectionPool);
    }

    private BasicConnectionPool(final String url, final String user, final String password, final List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connections = pool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connections.removeFirst();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connections.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(final String url, final String user, final String password) throws SQLException {
        try {
            //TODO: correct driver name
            Class.forName("");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver nem található: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, password);
    }

}
