package org.example;

import BusinessModel.TaskManagement;
import GUI.components.MainGUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// ne tb serializare
        TaskManagement taskManagement = new TaskManagement();

//        // Crearea unor angajați și sarcini
//        Employee emp1 = new Employee(1, "John Doe");
//        Employee emp2 = new Employee(2, "Jane Smith");
//
//        SimpleTask task1 = new SimpleTask(1, "Uncompleted");
//        task1.estimateDuration();  // Calcularea duratei
//
//        taskManagement.assignTaskToEmployee(emp1.getId(), task1);
//        taskManagement.assignTaskToEmployee(emp2.getId(), task1);
        new MainGUI().setVisible(true);
    }
}