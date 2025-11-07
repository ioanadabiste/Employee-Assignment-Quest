package BusinessModel;

import mainClasses.Employee;
import mainClasses.Task;

import java.util.*;

public class Utility {
    public static List<Employee> filterEmployeesByWorkDuration(TaskManagement taskManagement, int threshold) {
        List<Employee> filteredEmployees = new ArrayList<>();
        Map<Employee, Integer> workDurations = new HashMap<>();

        for (Map.Entry<Employee, List<Task>> entry : taskManagement.getMap().entrySet()) {
            int workDuration = taskManagement.calculateEmployeeWorkDuration(entry.getKey());
            if (workDuration > threshold) {
                filteredEmployees.add(entry.getKey());
                workDurations.put(entry.getKey(), workDuration);
            }
        }

        // Sortăm utilizând valorile deja calculate
        filteredEmployees.sort(Comparator.comparingInt(workDurations::get));

        return filteredEmployees;
    }


    public static Map<String, Map<String, Integer>> calculateTaskStatusCount(TaskManagement taskManagement) {
        Map<String, Map<String, Integer>> statusCountMap = new HashMap<>();
        for (Map.Entry<Employee, List<Task>> entry : taskManagement.getMap().entrySet()) {
            Map<String, Integer> countMap = new HashMap<>();
            countMap.put("Completed", 0);
            countMap.put("Uncompleted", 0);
            for (Task task : entry.getValue()) {
                if ("Completed".equals(task.getStatusTask())) {
                    countMap.put("Completed", countMap.get("Completed") + 1);
                } else {
                    countMap.put("Uncompleted", countMap.get("Uncompleted") + 1);
                }
            }
            statusCountMap.put(entry.getKey().getName(), countMap);
        }
        return statusCountMap;
    }
}
