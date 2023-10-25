package com.ceetech.service.impl;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ceetech.service.AuthenticationService;
import com.ceetech.service.LibraryService;
import com.ceetech.util.Config;

public class LibraryServiceImpl implements LibraryService {

    private AuthenticationService authenticationService;
    private Logger logger;

    Scanner scanner;

    public LibraryServiceImpl(AuthenticationService authenticationService, Logger logger) {
        scanner = new Scanner(System.in);
        this.authenticationService = authenticationService;
        this.logger = logger;
    }

    @Override
    public void startApplication() {
        logger.info("In Library Management System");
        boolean isLoggedIn = authenticateUser();
        logger.info("User authenticated: " + isLoggedIn);
        System.out.println();

    }

    private boolean authenticateUser() {
        System.out.println();
        logger.info("Authenticating the user");

        int loginEntries = 1;

        while (loginEntries <= Config.LOGIN_RETRIES_LIMIT) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                if (authenticationService.authenticate(username, password)) {
                    return true;
                }
                loginEntries++;
                logger.info("Wrong credentials!");
                System.out.println();
            } catch (ClassNotFoundException e) {
                logger.info("Driver class error " + e.getMessage());
                e.printStackTrace();
                return false;
            } catch (SQLException e) {
                logger.info("Error communicating to db " + e.getMessage());
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

}
