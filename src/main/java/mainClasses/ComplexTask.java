package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import dataAccess.SerializationUtil;
public final class ComplexTask extends Task implements Serializable {
    private static final long serialVersionUID = 3L; // Adaugă un UID explicit
    private List<Task> tasks = new ArrayList<>();

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public int estimateDuration() {
        int totalDuration = 0;
        for (Task task : tasks) {
            totalDuration += task.estimateDuration();
        }
        return totalDuration;
    }

    @Override
    public String toString() {
        return "ComplexTask{" +
                "idTask=" + getIdTask() +
                ", statusTask='" + getStatusTask() + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}