package dataAccess;

import BusinessModel.TaskManagement;

import java.io.*;

public class SerializationUtil {
    // Salvează obiectul TaskManagement într-un fișier
    public static void serialize(TaskManagement taskManagement) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("taskManagement.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(taskManagement);
            System.out.println("Datele au fost serializate cu succes.");
        }
    }

    // Încarcă obiectul TaskManagement dintr-un fișier
    public static TaskManagement deserialize() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream("taskManagement.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            TaskManagement taskManagement = (TaskManagement) objectInputStream.readObject();
            System.out.println("Datele au fost deserializate cu succes.");
            return taskManagement;
        }
    }
}

