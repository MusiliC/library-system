package com.ceetech.service.impl;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.model.Student;
import com.ceetech.service.StudentService;
import com.ceetech.util.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class StudentServiceImpl implements StudentService {

    DatabaseHandler databaseHandler;
    Logger logger;
    public  StudentServiceImpl(DatabaseHandler databaseHandler, Logger logger){
        this.databaseHandler = databaseHandler;
        this.logger = logger;
    }

    public void setupDB() {
        logger.info("Initializing table student in DB..");


        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String query = "create table if not exists students(id int not null auto_increment primary key,reg_no varchar(50) not null unique,name varchar(100) not null);";
            statement.executeUpdate(query);

        } catch (ClassNotFoundException e) {
            logger.severe("Failed connecting to class " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.severe("Error when creating table " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean saveStudent(Student student) throws RuntimeException {
        logger.info("Saving student " + student.getStudentName());
        String studentsQuery = "insert into students(reg_no, name)values(?,?);";
        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(studentsQuery)) {
            preparedStatement.setString(1, student.getRegNo());
            preparedStatement.setString(2, student.getStudentName());
            int noRowsInserted = preparedStatement.executeUpdate();
            return noRowsInserted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Error when fetching student " + e.getMessage());
            return false;
        }

    }

    @Override
    public Student getStudentByRegNo(String regNo) {
        return null;
    }
}
