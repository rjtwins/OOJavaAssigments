
import java.util.Scanner;

/**
 * @author J.Vedder S4379101
 * Main class gets called on startup, contains a scanner for input reading
 * contains a list of students empty at start.
 */
public class Manager {

    /**
     * Constructor starts up the program and makes an instance of the StudentManager to manage the students.
     * @param args the command line arguments
     */
    private Scanner scan = new Scanner(System.in);
    private Student[] students;
    public static void main(String[] args) {
        Manager m = new Manager();
        m.manager();
    }
    
    /**
     * Manages user in and output and runs appropriate functions.
     * Contains the main loop for the program and manages its flow.
     */
    public void manager(){
        System.out.println("Welcome to the student management system, how many students do you wan't to manage?");
        int i = scan.nextInt();
        
        System.out.println("Generating " + i + " students.");
        
        System.out.println("Please input a student number (no s) a first and a last name each followed by a return until all slots are filled.");
        this.students = new Student[i];
        for(int index=0; index < i; index++){
            System.out.println("Student: " + (index+1));
            students[index] = makeStudent();
        }
        printAllStudents();
        
        boolean stop = false;
        while(!stop){
            System.out.println("Please input a student number (no s) to change that students name.");
            System.out.println("Input a negative number to exit the program.");

            int number = scan.nextInt();
            if(number < 0){
                return;
            }
            Student to_alter = findStudent(number);
            if(to_alter != null){
                alterStudent(to_alter);
                printAllStudents();
            }else{
                System.out.println("The student you are looking for does not excist in the database, please try again.");
            }
            
        }
        
    }
    
    /**
     * Makes a student based on user input
     */    
    private Student makeStudent(){
        int number = scan.nextInt();
        String first_name = scan.next();
        String last_name = scan.next();
        
        return new Student(number, first_name, last_name);
    }
    
    /**
     * prints all students currently in the database.
     */
    public void printAllStudents(){
        System.out.println("The group now contains:");
        for(Student s : this.students){
            System.out.println(s.toString());
        }
    }
    
    /**
     * Finds a students based on its number and returns it
     * @param number
     * @return Student or null
     */
    private Student findStudent(int number){
        for(Student s : this.students){
            if(s.getNumber() == number){
                return s;
            }
        }
        return null;
    }
    
    /**
     * Alters a given students name based on user input.
     * @param Student
     */
    private void alterStudent(Student s){
        System.out.println("Please enter a first and last name each followed by a return.");
        s.change_first_name(scan.next());
        s.change_last_name(scan.next());
    }
    
}