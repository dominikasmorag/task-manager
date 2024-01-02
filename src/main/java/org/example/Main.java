package org.example;

import database.DataBase;

import database.TaskDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.mvstore.MVStoreException;
import usertools.Controller;

import java.sql.Connection;
import java.sql.SQLException;

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
        Controller controller = new Controller(connection);
        controller.showPanel();

    }

}