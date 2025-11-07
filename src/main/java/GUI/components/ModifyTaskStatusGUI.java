package GUI.components;


import BusinessModel.TaskManagement;
import mainClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyTaskStatusGUI extends JFrame {
    private TaskManagement taskManagement;

    public ModifyTaskStatusGUI(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Modify Task Status");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel employeeLabel = new JLabel("Select Employee:");
        JComboBox<Employee> employeeComboBox = new JComboBox<>(taskManagement.getAllEmployees().toArray(new Employee[0]));

        JLabel taskLabel = new JLabel("Select Task:");
        JComboBox<Task> taskComboBox = new JComboBox<>();

        JLabel statusLabel = new JLabel("New Status:");
        String[] statuses = {"Completed", "Uncompleted"};
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);

        JButton modifyButton = new JButton("Modify Status");

        // Când selectăm un angajat, încărcăm task-urile lui
        employeeComboBox.addActionListener(e -> {
            taskComboBox.removeAllItems();
            Employee selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
            if (selectedEmployee != null) {
                for (Task task : taskManagement.getMap().get(selectedEmployee)) {
                    taskComboBox.addItem(task);
                }
            }
        });

        // Buton pentru modificarea statusului
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
                Task selectedTask = (Task) taskComboBox.getSelectedItem();
                String newStatus = (String) statusComboBox.getSelectedItem();

                if (selectedEmployee != null && selectedTask != null) {
                    taskManagement.modifyTaskStatus(selectedEmployee, selectedTask.getIdTask(), newStatus);
                    JOptionPane.showMessageDialog(null, "Task status updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an employee and a task.");
                }
            }
        });

        panel.add(employeeLabel);
        panel.add(employeeComboBox);
        panel.add(taskLabel);
        panel.add(taskComboBox);
        panel.add(statusLabel);
        panel.add(statusComboBox);
        panel.add(new JLabel());
        panel.add(modifyButton);

        add(panel);
    }
}
