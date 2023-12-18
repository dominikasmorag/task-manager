package usertools;

import command.Command;
import database.CategoryDAO;
import database.TaskDAO;

import java.sql.Connection;
import java.util.HashMap;

public class Controller {
    private TaskDAO taskDAO;
    private CategoryDAO categoryDAO;
    private HashMap<String, Command> commands;

    public Controller(Connection connection) {
        System.out.println();
    }
}
