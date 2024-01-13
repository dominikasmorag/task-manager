package report;

import task.TaskEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReportHTML implements ReportStrategy {
    private final String filePath;
    private String[] names;
    private final Set<String> namesSet;
    public ReportHTML(String filePath) {
        this.filePath = filePath + "\\tasks-report.html";
        namesSet = addAllNames();
    }

    @Override
    public void generate(List<TaskEntity> list) {
        if(validateNames()) {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                String textToSave = openTable() + addRecords(list) + closeTable();
                fileWriter.write(textToSave);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.err.println("names are not valid");
        }
    }

    public void setColNames(String... names) {
        for(String s : names) {
            s = s.toLowerCase().replaceAll(" ", "");
        }
        this.names = Arrays.copyOf(names, names.length);
    }

    private String addRecords(List<TaskEntity> list) {
        StringBuilder records = new StringBuilder();

        for(TaskEntity t : list) {
            records.append("<tr>");
            for(String name : names) {
                records.append("<th>");
                records.append(namesToMap(t).get(name).toString());
                records.append("</th>");
            }
            records.append("</tr>");
        }

        return String.valueOf(records);
    }

    private String openTable() {
        StringBuilder open = new StringBuilder("<html><body><table><tr>");
        for(String name : names) {
            open.append("<th>" + name + "</th>");
        }
        open.append("</tr>");
        return String.valueOf(open);
    }

    private boolean validateNames() {
        return namesSet.containsAll(Arrays.asList(names));
    }

    private Map<String, Object> namesToMap(TaskEntity taskEntity) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskid", taskEntity.getId());
        map.put("title", taskEntity.getTitle());
        map.put("description", taskEntity.getDescription());
        map.put("duedate", taskEntity.getDueDate());
        map.put("status", taskEntity.getStatus());
        map.put("categoryid", taskEntity.getCategory().getId());
        map.put("categoryname", taskEntity.getCategory().getName());
        map.put("prioritylevel", taskEntity.getPriorityLevel());
        map.put("creationdate", taskEntity.getCreationDate());
        return map;
    }
    private String closeTable() {
        return "</table></body></html>";
    }

    private Set<String> addAllNames() {
        Set<String> set = new HashSet<>();
        set.add("taskid");
        set.add("title");
        set.add("description");
        set.add("dueDate");
        set.add("status");
        set.add("categoryid");
        set.add("categoryname");
        set.add("prioritylevel");
        set.add("creationdate");
        return set;
    }

}
