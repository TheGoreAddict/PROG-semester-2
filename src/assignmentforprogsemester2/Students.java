package assignmentforprogsemester2;

import static assignmentforprogsemester2.Student.jop;
import java.io.Serializable;
import java.util.ArrayList;

public class Students implements Serializable {

    //for serialization do this so it can read old ones and not remake each time?
    private static final long serialVersionUID = 7934225719615820579L;

    private String id;
    private String name;
    private String age;
    private String email;
    private String course;

    public Students(String id, String name, String age, String email, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public void setId(String id, ArrayList<Students> studentDetails) {
        /*
        gets id imput and checks it hasnt been done before 
         */
        boolean check = false;

        while (!check) {

            id = jop.showInputDialog("Enter the student ID:").trim();
            if (!id.isEmpty() && id.matches("\\d+")) {
                if (studentDetails.contains(id)) {
                    jop.showMessageDialog(null, "This id already exists please choose another");
                } else {
                    this.id = id;
                    check = true;
                }

            } else {
                jop.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        }

    }

    public void setName(String name) {
        /*
        gets name and checks that you arent entering a not name
        */
        boolean check = false;

        while (!check) {
            name = jop.showInputDialog("Enter the student name:").trim();
            if (!name.isEmpty() && name.matches("[a-zA-Z]+")) {
                this.name = name;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid name with no numbers or special characters.");
            }
        }

    }

    public void setAge(String age) {
        /*
        gets age and makes sure it isnt a not integer and is above 16 or 16
        */
        boolean check = false;

        while (!check) {
            age = jop.showInputDialog("Enter the student age:").trim();
            try {
                int i = Integer.parseInt(age);
                if (i >= 16) {

                    this.age = age;
                    check = true;
                } else {
                    jop.showMessageDialog(null, "Please enter a valid age (at least 16).");
                }
            } catch (NumberFormatException ex) {
                jop.showMessageDialog(null, "Please enter a valid age (at least 16).");
            }
        }

    }

    public void setEmail(String email) {
        /*
        checks that it has the @ and . in an email and that there are letters for writing 
        */
        boolean check = false;

        while (!check) {
            email = jop.showInputDialog("Enter the student email:").trim();
            if (!email.isEmpty() && email.contains("@") && email.contains(".") && email.matches("[a-zA-Z]+")) {
                this.email = email;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid email address.");
            }
        }

    }

    public void setCourse(String course) {
        /*
        checks course is letters 
        
        would have been a list but wasnt cause no list of courses provided so checking there are words is whats happening
        */
        boolean check = false;

        while (!check) {
            course = jop.showInputDialog("Enter the student course:").trim();
            if (!course.isEmpty() && course.matches("[a-zA-Z]+")) {
                this.course = course;
                check = true;
            } else {
                jop.showMessageDialog(null, "Please enter a valid course name.");
            }
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        /*
        use of formating 
        */
        return String.format("Student Id: %s  \nStudent Name: %s  \nStudent Age: %s  \nStudent Email: %s  \nStudent Course: %s\n", id, name, age, email, course);
    }

}
