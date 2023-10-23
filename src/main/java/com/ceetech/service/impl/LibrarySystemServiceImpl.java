package com.ceetech.service.impl;

import com.ceetech.service.LibrarySystemService;

import java.util.logging.Logger;

public class LibrarySystemServiceImpl implements LibrarySystemService {

    @Override
    public void startApplication(Logger logger) {
        logger.info("Library Management System App");
    }
}
