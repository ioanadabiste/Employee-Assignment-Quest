package GUI.components;

import mainClasses.Employee;
import BusinessModel.TaskManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEmployeeGUI extends JFrame {
    private TaskManagement taskManagement;
    private JTextField idField;
    private JTextField nameField;

    public AddEmployeeGUI(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
        setTitle("Add Employee");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                Employee employee = new Employee(id, name);
                taskManagement.getMap().put(employee, new ArrayList<>());
                JOptionPane.showMessageDialog(AddEmployeeGUI.this, "Employee added successfully!");
                dispose();
            }
        });

        panel.add(addButton);

        add(panel);
    }
}