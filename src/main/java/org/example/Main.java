package org.example;

import database.DataBase;

import database.TaskDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.mvstore.MVStoreException;
import task.TaskWithCategory;
import usertools.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
        TaskDAO taskDAO = new TaskDAO(connection);
        List<TaskWithCategory> list = taskDAO.findAllByCategory("university");
        for(TaskWithCategory task : list) {
            System.out.println(task);
        }
        Controller controller = new Controller(connection);
        controller.showPanel();

    }
}