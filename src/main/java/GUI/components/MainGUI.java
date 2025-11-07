package GUI.components;

import dataAccess.SerializationUtil;
import BusinessModel.TaskManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI extends JFrame {
    private TaskManagement taskManagement = new TaskManagement();

    public MainGUI() {
        setTitle("Task Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton addTaskButton = new JButton("Add Task");
        JButton assignTaskButton = new JButton("Assign Task to Employee");
        JButton viewStatisticsButton = new JButton("View Statistics");
        JButton modifyTaskStatusButton = new JButton("Modify Task Status");


        panel.add(addEmployeeButton);
        panel.add(addTaskButton);
        panel.add(assignTaskButton);
        panel.add(viewStatisticsButton);
        panel.add(modifyTaskStatusButton);


        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeGUI(taskManagement).setVisible(true);
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTaskGUI(taskManagement).setVisible(true);
            }
        });

        assignTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignTaskGUI(taskManagement).setVisible(true);
            }
        });

        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStatisticsGUI(taskManagement).setVisible(true);
            }
        });

        // Salvare date

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println("Se salvează datele înainte de închidere...");
                    SerializationUtil.serialize(taskManagement);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        modifyTaskStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyTaskStatusGUI(taskManagement).setVisible(true);
            }
        });


        add(panel);
    }
}
