package me.robotix.roboquests.quests.task;

import java.util.List;

//Contains a list of QuestTask to used for defining a QuestStage.
public class QuestTasks {

    private final List<QuestTask> taskSet;

    public QuestTasks(List<QuestTask> tasks) {
        this.taskSet = tasks;
    }

    public List<QuestTask> getTasks() {
        return taskSet;
    }

    /**
     *
     * @param task The task to search for in task set.
     * @return The QuestTask object if found.
     * @throws NullPointerException If QuestTask not found.
     */
    public QuestTask getContainedTask(QuestTask task) {
        return taskSet.stream()
                .filter(entry -> entry.getTaskID().equals(task.getTaskID()))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}