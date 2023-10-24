package com.ceetech.service.impl;

import java.util.Scanner;
import java.util.logging.Logger;

import com.ceetech.service.AuthenticationService;
import com.ceetech.service.LibraryService;

public class LibraryServiceImpl implements LibraryService {

    private AuthenticationService authenticationService;
    private Logger logger;

    Scanner scanner;

    public LibraryServiceImpl(AuthenticationService authenticationService, Logger logger){
        scanner = new Scanner(System.in);
        this.authenticationService = authenticationService;
        this.logger = logger;
    }

    @Override
    public void startApplication() {
        
    }
    
}
