package GUI.components;


import mainClasses.*;
import BusinessModel.TaskManagement;
import BusinessModel.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ViewStatisticsGUI extends JFrame {
    private TaskManagement taskManagement;

    public ViewStatisticsGUI(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("View Statistics");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Afișăm statisticile și task-urile asignate
        StringBuilder statistics = new StringBuilder();

        // Afișăm angajații și task-urile asignate lor
        statistics.append("=== Angajați și Task-uri Asignate ===\n");
        for (Map.Entry<Employee, List<Task>> entry : taskManagement.getMap().entrySet()) {
            Employee employee = entry.getKey();
            List<Task> tasks = entry.getValue();

            statistics.append("Angajat: ").append(employee.getName()).append(" (ID: ").append(employee.getIdEmployee()).append(")\n");
            if (tasks.isEmpty()) {
                statistics.append("  Nu are task-uri asignate.\n");
            } else {
                for (Task task : tasks) {
                    statistics.append("  - Task ID: ").append(task.getIdTask())
                            .append(", Status: ").append(task.getStatusTask())
                            .append(", Durată estimată: ").append(task.estimateDuration()).append(" ore\n");
                }
            }
            statistics.append("\n");
        }

        // Afișăm statisticile despre task-uri completate și necompletate
        statistics.append("=== Statistici Task-uri ===\n");
        Map<String, Map<String, Integer>> taskStatusCount = Utility.calculateTaskStatusCount(taskManagement);
        for (Map.Entry<String, Map<String, Integer>> entry : taskStatusCount.entrySet()) {
            String employeeName = entry.getKey();
            Map<String, Integer> statusCount = entry.getValue();

            statistics.append("Angajat: ").append(employeeName).append("\n");
            statistics.append("  Task-uri Completate: ").append(statusCount.get("Completed")).append("\n");
            statistics.append("  Task-uri Necompletate: ").append(statusCount.get("Uncompleted")).append("\n");
            statistics.append("\n");
        }

        // Afișăm angajații cu durata de muncă > 40 de ore
        statistics.append("=== Angajați cu Durată de Muncă > 40 Ore ===\n");
        List<Employee> filteredEmployees = Utility.filterEmployeesByWorkDuration(taskManagement, 40);
        if (filteredEmployees.isEmpty()) {
            statistics.append("Niciun angajat nu are o durată de muncă mai mare de 40 de ore.\n");
        } else {
            for (Employee employee : filteredEmployees) {
                statistics.append("Angajat: ").append(employee.getName())
                        .append(", Durată totală: ").append(taskManagement.calculateEmployeeWorkDuration(employee)).append(" ore\n");
            }
        }

        textArea.setText(statistics.toString());
        add(panel);
    }
}