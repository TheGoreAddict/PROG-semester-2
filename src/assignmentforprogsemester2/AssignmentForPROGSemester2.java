package assignmentforprogsemester2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class AssignmentForPROGSemester2 {

    static JOptionPane jop = new JOptionPane();
    static public ArrayList<Students> studentDetails = new ArrayList<>();
    private static int menuOption = 0;

    public static void main(String[] args) {

        boolean stillGo = true;

        populateArrayList();

        String launch = jop.showInputDialog("STUDENT MANAGMENT APPLICATION\n" + "*************************************\n" + "Enter (1) to launch menu or any other key to exit");

        if (launch.equals("1")) {
            menuOption = launchMenu();
        } else {
            exitStudentApplication();
        }

//gonna have a swutch break here for each diffrent menu option
        while (stillGo) {

            switch (menuOption) {
                case 1:
                    captureAStudent();
                    break;
                case 2:
                    studentSearch();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exitStudentApplication();
                default:
                    jop.showMessageDialog(null, "Inlaid input please try again");

            }
        }

    }

    public static void populateArrayList() {

        try {
            File read = new File("students.txt");
            if (read.exists()) {
                ArrayList<Students> temp;
                try ( FileInputStream fileIn = new FileInputStream("students.txt");  ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                    temp = (ArrayList<Students>) objectIn.readObject();
                }

                studentDetails.addAll(temp);
                System.out.println("intial file test existense and read from worked");
                System.out.println(studentDetails.toString() + "\n");
            } else {
                System.out.println("students.txt does not exist, creating a new file.");
                try {
                    File create = new File("students.txt");
                    create.createNewFile();

                } catch (IOException ex2) {
                    System.out.println(ex2);
                    System.out.println("something wrong with file create");
                }

            }

        } catch (IOException | ClassNotFoundException ex) {

            System.out.println(ex);

            System.out.println("intial file test exsitense and read from failed");

        }
    }

    public static int launchMenu() {
        int i = 0;
        String str = "";

        try {

            str = jop.showInputDialog("Please select one of the following items\n" + "(1) Capture a new student.\n" + "(2) Search for a student.\n" + "(3) Delete a student.\n" + "(4) Print student report.\n" + "(5) Exit application");

        } catch (NumberFormatException ex) {
            exitStudentApplication();
        }
        i = Integer.parseInt(str);
        return i;
    }

    public static void exitStudentApplication() {
        System.exit(0);
    }

    public static void captureAStudent() {

        String id = "";
        String name = "";
        String age = "";
        String email = "";
        String course = "";

        Students s = null;

        id = s.setId(id);
        name = s.setName(name);
        age = s.setAge(age);
        email = s.setEmail(email);
        course = s.setCourse(course);

        s = new Students(id, name, age, email, course);

        studentDetails.add(s);

        String str1 = String.format("Student ID: %s, \nStudent Name: %s, \nStudent Age: %s, \nStudent Email: %s, \nStudent Course: %s", id, name, age, email, course);
        jop.showMessageDialog(null, str1);
        saveToFile();

        String str = jop.showInputDialog("Enter (1) to launch the menu or any other key to exit");

        if (str != null && str.equals("1")) {
            launchMenu();
        } else {
            exitStudentApplication();
        }
    }

    public static void saveToFile() {
        try {
            try ( FileOutputStream fileOut = new FileOutputStream("students.txt");  ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                objectOut.writeObject(studentDetails);

            }
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("saveg isntr working");
        }

    }

    public static void studentSearch() {
        String str = jop.showInputDialog("Enter the student id to search").trim();

        List<Students> searchResult;
        searchResult = studentDetails.stream()
                .filter(student -> student.getId().equals(str))
                .collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            jop.showMessageDialog(null, "Student with Id: " + str + " was not found!");
            String options = jop.showInputDialog("Enter (1) to launch menu or any other key to exit");

            if (options.equals("1")) {

                launchMenu();
            } else {

                exitStudentApplication();
            }
        } else {

            Students searchedStudent = searchResult.get(0);

            System.out.println(searchedStudent.toString());

            String options = jop.showInputDialog("Enter (1) to launch menu or any other key to exit");

            if (options.equals("1")) {

                launchMenu();
            } else {

                exitStudentApplication();
            }
        }
    }
}
