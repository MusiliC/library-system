package com.ceetech;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.db.impl.DatabaseHandlerImpl;
import com.ceetech.service.AuthenticationService;
import com.ceetech.service.LibraryService;
import com.ceetech.service.impl.AuthenticationServiceImpl;
import com.ceetech.service.impl.BookServiceImpl;
import com.ceetech.service.impl.LibraryServiceImpl;
import com.ceetech.service.impl.StudentServiceImpl;
import com.ceetech.util.CustomFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        initializeLogger();
        DatabaseHandler databaseHandler = new DatabaseHandlerImpl();
        AuthenticationService authenticationService = new AuthenticationServiceImpl(databaseHandler, LOGGER);
        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(databaseHandler, LOGGER);
        BookServiceImpl bookServiceImpl = new BookServiceImpl(databaseHandler, LOGGER);
        LibraryService libraryService = new LibraryServiceImpl(authenticationService,bookServiceImpl,studentServiceImpl, LOGGER);
        libraryService.startApplication();
    }

    static public void initializeLogger() {
        FileHandler handler;
        try {
            handler = new FileHandler("log.txt", true);
            CustomFormatter formatter = new CustomFormatter();
            LOGGER.addHandler(handler);
            handler.setFormatter(formatter);
            LOGGER.info("Starting Application!");
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        ;
    }
}