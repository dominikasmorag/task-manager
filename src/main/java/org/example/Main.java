package org.example;

import command.CreateTaskCommand;
import database.CategoryDAO;
import database.DataBase;


import database.TaskDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.mvstore.MVStoreException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


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
        } catch (SQLException | MVStoreException e) {
            throw new RuntimeException(e);
        }
        CategoryDAO categoryDAO = new CategoryDAO(connection);
        TaskDAO taskDAO = new TaskDAO(connection);

        CreateTaskCommand command = new CreateTaskCommand(taskDAO, categoryDAO);
        command.execute();

        Map<String, Integer> map = categoryDAO.updateMap();
        System.out.println("Entry set: " + map.entrySet());
    }

}