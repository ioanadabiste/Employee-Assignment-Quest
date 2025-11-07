package mainClasses;
import java.io.Serializable;
import java.io.Serializable;
import dataAccess.SerializationUtil;
public abstract sealed class Task implements Serializable permits SimpleTask, ComplexTask {
    private static final long serialVersionUID = 2L; // Adaugă un UID explicit
    private int idTask;
    private String statusTask;

    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    // Getters și setters
    public int getIdTask() {
        return idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public abstract int estimateDuration();
}