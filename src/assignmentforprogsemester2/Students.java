package assignmentforprogsemester2;

import static assignmentforprogsemester2.AssignmentForPROGSemester2.jop;
import java.io.Serializable;

public class Students implements Serializable {
    
    //for serialization do this so it can read old ones
    private static final long serialVersionUID = 7934225719615820579L;
    
    static private String id;
    static private String name;
    static private String age;
    static private String email;
    static private String course;

    public Students (String id, String name, String age, String email, String course) {
        Students.id = id;
        Students.name = name;
        Students.age = age;
        Students.email = email;
        Students.course = course;
    }

    public String setId(String id) {
        
        boolean check = false;
        
        while (!check) {
            
            //check for pre existing id 
            
            String str = jop.showInputDialog("Enter the student ID:");
            if (!str.isEmpty() && str.matches("\\d+")) {
                id = str;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        }
        return id;
    }

    public static String setName(String name) {
        
        boolean check = false;
        
        while (!check) {
            String str = jop.showInputDialog("Enter the student name:");
            if (!str.isEmpty() && str.matches("[a-zA-Z]+")) {
                name = str;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid name with no numbers or special characters.");
            }
        }
        return name;
    }

    public static String setAge(String age) {
        
        boolean check = false;
        
        while (!check) {
            String str = jop.showInputDialog("Enter the student age:");
            try {
                int i = Integer.parseInt(str);
                if (i >= 16) {
                    age = String.valueOf(i);
                    check = true;
                } else {
                    jop.showMessageDialog(null, "Please enter a valid age (at least 16).");
                }
            } catch (NumberFormatException ex) {
                jop.showMessageDialog(null, "Please enter a valid age (at least 16).");
            }
        }
        return age;
    }

    public static String setEmail(String email) {
        
        boolean check = false;
        
         while (!check) {
            String str = jop.showInputDialog("Enter the student email:");
            if (!str.isEmpty() && str.contains("@") && str.contains(".")) {
                email = str;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid email address.");
            }
        }
         return email;
    }

    public static String setCourse(String course) {
        
        boolean check = false;
        
        while (!check) {
            String str = jop.showInputDialog("Enter the student course:");
            if (!str.isEmpty() && str.matches("[a-zA-Z]+")) {
                course = str;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid course name.");
            }
        }
        return course;
    }
    
    
    
    public String getId() {
        return id;
    }

   @Override
    public String toString() {
        return String.format("Student Id: %s , Student Name: %s , Student Age: %s , Student Email: %s , Student Course: %s", id , name , age , email , course);
    
        
    }
    
    
    
   

}
