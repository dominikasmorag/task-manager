package report;

import database.CategoryDAO;
import org.json.simple.JSONObject;
import task.TaskEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class ReportJSON {
    private String filePath;
    private final Connection connection;

    public ReportJSON(String filePath, Connection connection)
    {
        this.filePath = filePath + "\\tasks-report.json";
        this.connection = connection;
    }

    public void generate(List<TaskEntity> tasks) {
        CategoryDAO categoryDAO = new CategoryDAO(connection);
        List<JSONObject> objects = new ArrayList<>();
        long start = Calendar.getInstance().getTimeInMillis();
        for(TaskEntity t : tasks) {
            Map<String, Object> obj = new LinkedHashMap<>();
            Map<String, Object> categoryMap = new LinkedHashMap<>();
            obj.put("id", t.getId());
            obj.put("title", t.getTitle());
            obj.put("description", t.getDescription());
            obj.put("dueDate", t.getDueDate().toString());
            obj.put("status", t.getStatus().toString());
//            JSONObject categoryObj = new JSONObject();
//            categoryObj.put("id", t.getCategory().getId());
//            categoryObj.put("name", t.getCategory().getName());
//            categoryObj.put("creationDate", String.valueOf(t.getCategory().getCreationDate()));
            //obj.put("Category", );
            obj.put("priorityLevel", String.valueOf(t.getPriorityLevel().toString()));
            obj.put("creationDate", t.getCreationDate().toString());
            objects.add(new JSONObject(obj));
            System.out.println(obj);
        }
        long end = Calendar.getInstance().getTimeInMillis();
        long time = end - start;
        System.out.println("TIME FOR LINKEDLIST " + time);
        try (FileWriter file = new FileWriter(filePath)) {
            for(JSONObject jsonObj : objects) {
                file.write(jsonObj.toJSONString() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
