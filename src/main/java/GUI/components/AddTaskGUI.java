package GUI.components;


import mainClasses.*;
import BusinessModel.TaskManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddTaskGUI extends JFrame {
    private TaskManagement taskManagement;
    private JTextField idField;
    private JComboBox<String> statusComboBox;
    private JTextField startHourField;
    private JTextField endHourField;
    private JComboBox<String> taskTypeComboBox;
    private JLabel startHourLabel;
    private JLabel endHourLabel;

    public AddTaskGUI(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Add Task");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Task ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Task Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Uncompleted", "Completed"});
        panel.add(statusComboBox);

        panel.add(new JLabel("Task Type:"));
        taskTypeComboBox = new JComboBox<>(new String[]{"SimpleTask", "ComplexTask"});
        panel.add(taskTypeComboBox);

        startHourLabel = new JLabel("Start Hour (for SimpleTask):");
        panel.add(startHourLabel);
        startHourField = new JTextField();
        panel.add(startHourField);

        endHourLabel = new JLabel("End Hour (for SimpleTask):");
        panel.add(endHourLabel);
        endHourField = new JTextField();
        panel.add(endHourField);

        // Ascundem câmpurile pentru orele de început și sfârșit când se selectează ComplexTask
        taskTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("ComplexTask".equals(taskTypeComboBox.getSelectedItem())) {
                    startHourLabel.setVisible(false);
                    startHourField.setVisible(false);
                    endHourLabel.setVisible(false);
                    endHourField.setVisible(false);
                } else {
                    startHourLabel.setVisible(true);
                    startHourField.setVisible(true);
                    endHourLabel.setVisible(true);
                    endHourField.setVisible(true);
                }
            }
        });

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String status = (String) statusComboBox.getSelectedItem();
                    String taskType = (String) taskTypeComboBox.getSelectedItem();

                    Task task;

                    if ("SimpleTask".equals(taskType)) {
                        int startHour = Integer.parseInt(startHourField.getText());
                        int endHour = Integer.parseInt(endHourField.getText());

                        if (startHour >= 0 && endHour >= 0 && startHour < endHour) {
                            task = new SimpleTask(id, status, startHour, endHour);
                        } else {
                            JOptionPane.showMessageDialog(AddTaskGUI.this, "Invalid start or end hour!");
                            return; // Oprire dacă orele nu sunt valide
                        }
                    } else {
                        task = new ComplexTask(id, status);
                    }

                    // Adăugăm task-ul în TaskManagement
                    taskManagement.addTask(task);
                    JOptionPane.showMessageDialog(AddTaskGUI.this, taskType + " added successfully!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddTaskGUI.this, "Invalid input! Please enter valid numbers.");
                }
                dispose();
            }
        });


        panel.add(addButton);

        add(panel);
    }
}