package org.example;

import command.CreateTaskCommand;
import database.CategoryDAO;
import database.DataBase;


import database.TaskDAO;
import org.h2.jdbcx.JdbcDataSource;
import task.Category;
import task.PriorityLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Connection connection;
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:C:/Users/domin/Desktop/taskmanager.db");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        try {
            connection = dataSource.getConnection();
            System.out.println(connection.isClosed());

            DataBase.createSchema(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CreateTaskCommand command = new CreateTaskCommand(new TaskDAO());
        command.execute();

        Category category = new Category("praca");
        try {
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            categoryDAO.insert(category);
            int a = categoryDAO.getIdByName("praca");
            System.out.println("int a: " + a);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}