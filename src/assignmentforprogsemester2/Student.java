package assignmentforprogsemester2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Student {

    static JOptionPane jop = new JOptionPane();
    static public ArrayList<Students> studentDetails = new ArrayList<>();
    private static int menuOption = 0;

    public static void main(String[] args) {

        boolean stillGo = true;
        int test = 1;

        populateArrayList();

        String launch = jop.showInputDialog("STUDENT MANAGMENT APPLICATION\n" + "*************************************\n" + "Enter (1) to launch menu or any other key to exit");

        if (launch.equals("1")) {
            
            while (stillGo) {

                menuOption = launchMenu();

                switch (menuOption) {
                    case 1:
                        captureAStudent();
                        break;
                    case 2:
                        studentSearch(test);
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        studentReport();
                        break;
                    case 5:
                        exitStudentApplication();
                    default:
                        jop.showMessageDialog(null, "Inlaid input please try again");

                }
            }
        } else {
            exitStudentApplication();
        }

//gonna have a swutch break here for each diffrent menu option
    }

    public static void populateArrayList() {

        try {
            File read = new File("students.txt");
            if (read.exists()) {
                ArrayList<Students> temp;
                try ( FileInputStream fileIn = new FileInputStream("students.txt");  ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                    temp = (ArrayList<Students>) objectIn.readObject();
                    System.out.println("intial read in ");
                }
                studentDetails.addAll(temp);
                System.out.println("intial file test existense and read from worked");
                System.out.println(studentDetails.toString() + "\n");
            } else {
                System.out.println("students.txt does not exist, creating a new file.");
                try {
                    File create = new File("students.txt");
                    create.createNewFile();
                    System.out.println("bad loop");
                    populateArrayList();
                    System.out.println("bad loop");
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

    public static void endOfFunction() {
        String str = jop.showInputDialog("Enter (1) to launch the menu or any other key to exit");

        if (str != null && str.equals("1")) {
            // launchMenu();
        } else {
            exitStudentApplication();
        }
    }

    public static int launchMenu() {
        int i = 0;
        String str = "";
        boolean check = false;

        while (!check) {
            str = jop.showInputDialog("Please select one of the following items\n" + "(1) Capture a new student.\n" + "(2) Search for a student.\n" + "(3) Delete a student.\n" + "(4) Print student report.\n" + "(5) Exit application");

            if (str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5")) {
                check = true;
            } else {
                jop.showMessageDialog(null, "Please only enter a prescribed above action!");
            }

        }
        i = Integer.parseInt(str);
        return i;
    }

    public static void exitStudentApplication() {
        System.exit(0);
    }

    public static void captureAStudent() {
        System.out.println("does this run at all");
        String id = "";
        String name = "";
        String age = "";
        String email = "";
        String course = "";
        String str1 = "";

        System.out.println("breaks here 1 ?");

        Students s = new Students(id, name, age, email, course);

        System.out.println("breaks here 2 ?");

        s.setId(id);
        System.out.println("1");
        s.setName(name);
        System.out.println("2");
        s.setAge(age);
        System.out.println("3");
        s.setEmail(email);
        System.out.println("4");
        s.setCourse(course);

        System.out.println("cap student after object intializATION worked");

        //s = new Students(id, name, age, email, course);
        System.out.println("cap student making the student didnt work");

        studentDetails.add(s);

        str1 = s.toString();
        jop.showMessageDialog(null, str1);
        saveToFile();

        endOfFunction();
    }

    public static void saveToFile() {
        try {
            try ( FileOutputStream fileOut = new FileOutputStream("students.txt");  ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                objectOut.writeObject(studentDetails);
                System.out.println("save worked");
            }
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("saveg isntr working");
        }

    }

    public static Students studentSearch(int split) {
        String id = "";
        String name = "";
        String age = "";
        String email = "";
        String course = "";

        Students searchedStudent = new Students(id, name, age, email, course);
        String str;

        if (split == 1) {
            str = jop.showInputDialog("Enter the student id to search").trim();
        } else {
            str = jop.showInputDialog("Please enter the student id you wold like to delete").trim();
        }

        List<Students> searchResult;
        System.out.println("this doesnt work 1");
        searchResult = studentDetails.stream()
                .filter(student -> student.getId().equals(str))
                .collect(Collectors.toList());
        System.out.println("this doesnt work 2");
        System.out.println(searchResult.toString());
        if (searchResult.isEmpty()) {
            jop.showMessageDialog(null, "Student with Id: " + str + " was not found!");
            String options = jop.showInputDialog("Enter (1) to launch menu or any other key to exit");

            if (options.equals("1")) {

                launchMenu();
            } else {

                exitStudentApplication();
            }
        } else {

            searchedStudent = searchResult.get(0);

            System.out.println(searchedStudent.toString());

            endOfFunction();
        }
        return searchedStudent;
    }

    public static void deleteStudent() {
        /*
        gotta make sure that the user is asked to double check
         */

        boolean surity = true;
        int y = 0;

        Students deleteThem = studentSearch(y);

        String str = jop.showInputDialog("Are you sure you want to delete Student " + deleteThem.getId() + "? This will remove them permanantly!" + "\nPlease answer with either a y or n").trim();

        if (str.equals("y")) {
            surity = false;
            if (!surity) {
                boolean remove = studentDetails.remove(deleteThem);
                if (remove) {
                    System.out.println("good delete");
                    saveToFile();
                } else {
                    System.out.println("bad delete");
                }
            }
        }
        if (str.equals("n")) {
            endOfFunction();
        }
    }

    public static void studentReport() {
        int i = 0;
        String str = "";
        
        for (Iterator<Students> display = studentDetails.iterator(); display.hasNext();) {
            Students s = display.next();
            str = str + ("STUDENT " + i + "\n" + s.toString() + "----------------------------------------------------\n");
            i++;
        }

    }

}
