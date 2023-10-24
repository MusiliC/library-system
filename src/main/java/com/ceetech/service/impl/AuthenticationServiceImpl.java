package com.ceetech.service.impl;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.service.AuthenticationService;
import com.ceetech.util.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private DatabaseHandler databaseHandler;
    private Logger logger;
    boolean isAuthentic;

    public AuthenticationServiceImpl(DatabaseHandler databaseHandler, Logger logger) {
        this.databaseHandler = databaseHandler;
        this.logger = logger;
    }

    @Override
    public boolean authenticate(String username, String password) {

        logger.info("Initializing authentication for " + username);

        Connection connection;
        try {
            connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD);
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where username=? and password=?")) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                isAuthentic = false;
                while (resultSet.next()) {
                    isAuthentic = true;
                }
                resultSet.close();
                connection.close();
                return isAuthentic;
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Error when logging in! " + e.getMessage());
            e.printStackTrace();

        }
        return isAuthentic;

    }
}
