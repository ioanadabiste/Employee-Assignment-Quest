package mainClasses;

import java.io.Serializable;
import dataAccess.SerializationUtil;
public class Employee implements Serializable {
    private static final long serialVersionUID = 5L; // Adaugă un UID explicit
    private int idEmployee;
    private String name;

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    // Getters și setters
    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                '}';
    }
}