package report;

import org.json.simple.JSONObject;
import task.TaskEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReportJSON implements ReportStrategy {
    private final String filePath;

    public ReportJSON(String filePath)
    {
        this.filePath = filePath + "\\tasks-report.json";
    }

    public void generate(List<TaskEntity> tasks) {
        List<JSONObject> objects = new ArrayList<>();
        createMaps(tasks, objects);
        saveFile(objects);
    }

    private void createMaps(List<TaskEntity> tasks, List<JSONObject> jsonObjects) {
        for(TaskEntity t : tasks) {
            Map<String, Object> categoryMap = new TreeMap<>();
            categoryMap.put("id", t.getCategory().getId());
            categoryMap.put("name", t.getCategory().getName());
            categoryMap.put("creationDate", String.valueOf(t.getCategory().getCreationDate()));

            Map<String, Object> taskMap = new TreeMap<>();
            taskMap.put("category", categoryMap);
            taskMap.put("id", t.getId());
            taskMap.put("title", t.getTitle());
            taskMap.put("description", t.getDescription());
            taskMap.put("dueDate", t.getDueDate().toString());
            taskMap.put("status", t.getStatus().toString());
            taskMap.put("priorityLevel", String.valueOf(t.getPriorityLevel().toString()));
            taskMap.put("creationDate", t.getCreationDate().toString());

            JSONObject taskBody = new JSONObject();
            taskBody.put("task", taskMap);

            jsonObjects.add(new JSONObject(taskBody));
        }
    }

    private void saveFile(List<JSONObject> jsonObjects) {
        try (FileWriter file = new FileWriter(filePath)) {
            for(JSONObject jsonObj : jsonObjects) {
                file.write(jsonObj.toJSONString() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
