package me.robotix.roboquests.quests.task;

import java.util.List;

//Contains a list of QuestTask to used for defining a QuestStage.
public class QuestTasks {

    private final List<QuestTask> tasks;

    public QuestTasks(List<QuestTask> tasks) {
        this.tasks = tasks;
    }

    public List<QuestTask> getTasks() {
        return tasks;
    }

    public QuestTask getContainedTask(QuestTask task) {
        return tasks.stream()
                .filter(entry -> entry.getTaskID().equals(task.getTaskID()))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}