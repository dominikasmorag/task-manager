package report;

import task.TaskWithCategory;

import java.util.List;

public interface ReportStrategy {

    void generateReport(List<TaskWithCategory> list);
}
