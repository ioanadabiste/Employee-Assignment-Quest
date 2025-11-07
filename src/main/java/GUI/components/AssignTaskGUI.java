package GUI.components;

import mainClasses.*;
import BusinessModel.TaskManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignTaskGUI extends JFrame {
    private TaskManagement taskManagement;
    private JTextField employeeIdField;
    private JTextField taskIdField;
    private JComboBox<String> statusComboBox;

    public AssignTaskGUI(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Assign Task to Employee");
        setSize(300, 250); // Mărim fereastra pentru a încăpea noul câmp
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // Adăugăm un rând pentru status

        panel.add(new JLabel("Employee ID:"));
        employeeIdField = new JTextField();
        panel.add(employeeIdField);

        panel.add(new JLabel("Task ID:"));
        taskIdField = new JTextField();
        panel.add(taskIdField);

        panel.add(new JLabel("Task Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Completed", "Uncompleted"});
        panel.add(statusComboBox);

        JButton assignButton = new JButton("Assign");
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(employeeIdField.getText());
                    int taskId = Integer.parseInt(taskIdField.getText());
                    String status = (String) statusComboBox.getSelectedItem(); // Obținem statusul selectat

                    // Găsim angajatul și task-ul folosind metodele din TaskManagement
                    Employee employee = taskManagement.findEmployeeById(employeeId);
                    Task task = taskManagement.findTaskById(taskId);

                    if (employee != null && task != null) {
                        // Setăm statusul task-ului
                        ((mainClasses.Task) task).setStatusTask(status);

                        // Asignăm task-ul angajatului
                        taskManagement.assignTaskToEmployee(employee, task);
                        JOptionPane.showMessageDialog(AssignTaskGUI.this, "Task assigned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(AssignTaskGUI.this, "Employee or Task not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AssignTaskGUI.this, "Invalid input! Please enter valid IDs.");
                }
                dispose();
            }
        });

        panel.add(assignButton);

        add(panel);
    }
}