package report;

import task.TaskEntity;

import java.util.List;

@FunctionalInterface
public interface ReportStrategy {

    void generate(List<TaskEntity> list);
}
