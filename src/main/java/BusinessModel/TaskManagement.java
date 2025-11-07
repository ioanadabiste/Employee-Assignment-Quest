package BusinessModel;

import mainClasses.Employee;
import mainClasses.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagement implements Serializable {
    private static final long serialVersionUID = 4L; // Adaugă un UID explicit
    private Map<Employee, List<Task>> map = new HashMap<>();
    private List<Task> allTasks = new ArrayList<>();


    public void assignTaskToEmployee(Employee employee, Task task) {
        map.computeIfAbsent(employee, k -> new ArrayList<>()).add(task);
    }

    public int calculateEmployeeWorkDuration(Employee employee) {
        int totalDuration = 0;
        List<Task> tasks = map.get(employee);
        if (tasks != null) {
            for (Task task : tasks) {
                if ("Completed".equals(task.getStatusTask())) {
                    totalDuration += task.estimateDuration();
                }
            }
        }
        return totalDuration;
    }

    public void modifyTaskStatus(Employee employee, int idTask, String newStatus) {
        List<Task> tasks = map.get(employee);
        if (tasks != null) {
            for (Task task : tasks) {
                if (task.getIdTask() == idTask) {
                    task.setStatusTask(newStatus);
                    break;
                }
            }
        }
    }
    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : map.keySet()) {
            if (employee.getIdEmployee() == employeeId) {
                return employee;
            }
        }
        return null; // Angajatul nu a fost găsit
    }

    // Metodă pentru a găsi un task după ID
    public Task findTaskById(int taskId) {
        for (Task task : allTasks) {
            if (task.getIdTask() == taskId) {
                return task;
            }
        }
        return null; // Task-ul nu a fost găsit
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public Map<Employee, List<Task>> getMap() {
        return map;
    }
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(map.keySet());
    }
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (List<Task> tasks : map.values()) {
            allTasks.addAll(tasks);
        }
        return allTasks;
    }
}