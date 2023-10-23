package com.ceetech;

import com.ceetech.service.LibrarySystemService;
import com.ceetech.service.impl.LibrarySystemServiceImpl;
import com.ceetech.util.CustomFormatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LibrarySystemMain {
    private static final Logger LOGGER = Logger.getLogger(LibrarySystemMain.class.getName());


    public static void main(String[] args) throws IOException {
        initializeLogger();
        System.out.println("Hello world!");
        LibrarySystemService librarySystemService = new LibrarySystemServiceImpl();
        librarySystemService.startApplication(LOGGER);
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