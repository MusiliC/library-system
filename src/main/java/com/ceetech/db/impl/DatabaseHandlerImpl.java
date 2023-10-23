package com.ceetech.db.impl;

import com.ceetech.db.DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandlerImpl implements DatabaseHandler {
    @Override
    public Connection connect(String connectionUrl, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}
