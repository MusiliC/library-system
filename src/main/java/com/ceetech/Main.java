package com.ceetech;
import com.ceetech.util.CustomFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        initializeLogger();        
       
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