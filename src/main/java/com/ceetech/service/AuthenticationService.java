package com.ceetech.service;

import java.sql.SQLException;

public interface AuthenticationService {
    boolean authenticate(String username, String password) throws SQLException, ClassNotFoundException;
}
