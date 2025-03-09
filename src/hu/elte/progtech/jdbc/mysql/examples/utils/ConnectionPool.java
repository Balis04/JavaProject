package hu.elte.progtech.jdbc.mysql.examples.utils;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    String getUrl();
    String getUser();
    String getPassword();
    boolean releaseConnection(final Connection connection);
}
