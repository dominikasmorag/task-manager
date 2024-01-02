package usertools;

import database.TaskDAO;
import task.PriorityLevel;
import task.TaskEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final Connection connection;
    private final TaskCreator taskCreator;
    private final TaskDAO taskDAO;
    private final TaskModifier taskModifier;
    public Controller(Connection connection) {
        this.connection = connection;
        this.taskCreator = new TaskCreator(connection);
        this.taskDAO = new TaskDAO(connection);
        this.taskModifier = new TaskModifier(connection);
    }

    public void showPanel() {
        System.out.println("1 - create Task");
        System.out.println("2 - show all tasks");
        System.out.println("3 - complete task");
        System.out.println("4 - show all tasks with LOW priority level");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch(input) {
            case "1" : taskCreator.createTask();
            break;
            case "2" : taskDAO.findAll();
            break;
            case "3" : taskModifier.changeStatus();
            break;
            case "4" : List<TaskEntity> lowTasks = taskDAO.findAllWithField(PriorityLevel.LOW);
            for(TaskEntity lowTask : lowTasks) {
                System.out.println(lowTask);
            }
            break;
            default:
                System.out.println("wrong number");
                showPanel();
        }
    }

 }
