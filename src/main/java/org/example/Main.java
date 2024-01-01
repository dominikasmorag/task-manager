package org.example;

import database.DataBase;

import database.TaskDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.mvstore.MVStoreException;
import task.Status;
import task.TaskEntity;
import usertools.TaskModifier;

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

        TaskModifier taskModifier = new TaskModifier(connection);
        taskModifier.createTask();

        TaskDAO taskDAO = new TaskDAO(connection);
        try {
            List<TaskEntity> tasks = taskDAO.findAllWithStatus(Status.COMPLETED);
            for(TaskEntity t : tasks) {
                System.out.println(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}