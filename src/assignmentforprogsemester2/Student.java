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
        /*
        main
        - just gonna be menu swaping 
            - use switch case
        
         */
        boolean stillGo = true;
        int test = 1;

        populateArrayList();

        String launch = jop.showInputDialog("STUDENT MANAGMENT APPLICATION\n" + "*************************************\n" + "Enter (1) to launch menu or any other key to exit");

        if (launch.equals("1")) {

            while (stillGo) {

                menuOption = launchMenu();

                switch (menuOption) {
                    case 1 ->
                        captureAStudent();
                    case 2 ->
                        studentSearch(test);
                    case 3 ->
                        deleteStudent();
                    case 4 ->
                        studentReport();
                    case 5 ->
                        exitStudentApplication();
                    default ->
                        jop.showMessageDialog(null, "Inlaid input please try again");

                }
            }
        } else {
            exitStudentApplication();
        }

//gonna have a swutch break here for each diffrent menu option
    }

    public static void populateArrayList() {
        /*
        sets up initial arraylist with any posiibly saved objects from any previos instances 
        
        stream filter makes life easy try using that. i dont think even bufferd writer would work here
        
        dont forget to take out debugging sout's
        
         */

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
                    if (create.createNewFile()) {
                        System.out.println("File created successfully.");
                    } else {
                        System.out.println("File already exists.");
                    }
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

        /*
        have to use this a lot so made it a method 
        
        was gonna take out the // launch menu thats commented out but it makes it easier to read coiuse it helps you understand what happens without it makign to many menus appear(why its commented out) same for the rest of them
         */
        String str = jop.showInputDialog("Enter (1) to launch the menu or any other key to exit");

        if (str != null && str.equals("1")) {
            // launchMenu();
        } else {
            exitStudentApplication();
        }
    }

    public static void saveToFile() {
        /*
        to save objects specifically use the filout/inputStream 
        -works kind of like a fileread or write in principle but it serializes the information. ive come to understand now that supposedly serialization isnt that great in terms of security but its super effienct so i thought proper security is a bit much
        
        saves studentDetails at any point in time as StudentDetails is a class variable so its always constant
        
        also doeant append. So it doesnt add to the file it jsut rewrites the file eniterly as the data is kept on hand in the studentDetails arraylist and its savbed so often cause there are a lot of chamnces to leave the program without something being saved
        
         */
        try {
            try ( FileOutputStream fileOut = new FileOutputStream("students.txt");  ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                objectOut.writeObject(studentDetails);
                System.out.println("save worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("saveg isntr working");
        }

    }

    public static int launchMenu() {
        /*
        basic number return nothing fancy 
        easy validation check
         */
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
        /*
        i thought this was nifty i didbnt know about it before this 
         */
        System.exit(0);
    }

    public static void captureAStudent() {
        /*
        
        the actual grunt work of this is done in the Students object class
        
        just build an object and then add append it the constant studentDetails ArrayList(which when declared at the top was declared as a ArrayList<Students> so it can even hold the objects)
        
        alwaswy need to save to file to keep updated persistant records
        
         */
        
        String id = "";
        String name = "";
        String age = "";
        String email = "";
        String course = "";
        String str1 = "";

        Students s = new Students(id, name, age, email, course);

        s.setId(id, studentDetails);
        
        s.setName(name);
        
        s.setAge(age);
        
        s.setEmail(email);
        
        s.setCourse(course);

        studentDetails.add(s);

        str1 = s.toString();
        jop.showMessageDialog(null, str1);
        saveToFile();

        endOfFunction();
    }

    public static Students studentSearch(int split) {
        /*
        
        i use this class for the delete student just to make sure the student is the right one chosen
        
        the if alows for multi use diffrent functions 
        
        the stream is super useful for this.
        -the null pointers are important as they allow for a catch sort of for any nulls cause the normal filter part in the lambda(student.getId().equals(str)) that doesnt do null and throws a null pointer exception but a try catch didnt work
        
        the two similar ifs at the bottom is also for multi use case also with the delete method
        */
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
        
        int i = Integer.parseInt(str);

        List<Students> searchResult;

        searchResult = studentDetails.stream()
                .filter(student -> student != null && student.getId() != null && student.getId().equals(str))
                .collect(Collectors.toList());

        System.out.println(searchResult.toString());

        if (searchResult.isEmpty()) {
            jop.showMessageDialog(null, "Student with Id: " + str + " was not found!");

            String options = jop.showInputDialog("Enter (1) to launch menu or any other key to exit");

            if (options.equals("1")) {

                //launchMenu();
            } else {

                exitStudentApplication();
            }
        } else {
            if (split == 1) {

                searchedStudent = searchResult.get(0);
                jop.showMessageDialog(null, searchedStudent.toString());
                endOfFunction();
            }
            if (split == 0) {
                searchedStudent = searchResult.get(0);
                jop.showMessageDialog(null, searchedStudent.toString());
            }

        }
        return searchedStudent;
    }

    public static void deleteStudent() {
        /*
        pretty basic the work is done by the .remove function
        
        uses the student search instead of making redundent code
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
        /*
        simple string building but used a for itarater as it allowed for more effienct coming through of each object than a for each 
        */
         
        int i = 1;
        String str = "";

        for (Iterator<Students> display = studentDetails.iterator(); display.hasNext();) {
            Students s = display.next();
            str = str + ("STUDENT " + i + "\n" + "----------------------------------------------------\n" + s.toString() + "----------------------------------------------------\n");
            i++;
        }
        jop.showMessageDialog(null, str);
    }

}
