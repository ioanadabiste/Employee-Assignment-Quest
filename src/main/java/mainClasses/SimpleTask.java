package mainClasses;

import java.io.Serializable;
import dataAccess.SerializationUtil;
public final class SimpleTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L; // Adaugă un UID explicit
    private int startHour;
    private int endHour;

    public SimpleTask(int idTask, String statusTask, int startHour, int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        return endHour - startHour;
    }

    @Override
    public String toString() {
        return "SimpleTask{" +
                "idTask=" + getIdTask() +
                ", statusTask='" + getStatusTask() + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }
}