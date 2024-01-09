import java.util.Scanner;


public class App {
    public static void main(String[] args) {
         try (Scanner scanner = new Scanner(System.in)) {
            // Get student details
            Student student = new Student();
            String name = student.getStudentName(scanner);
            int numSubjects = student.getNumberOfSubjects(scanner);

            Student studentUpdated = new Student(name, numSubjects);

            // Input marks and display results
            studentUpdated.inputMarks();
            studentUpdated.displayResults();
        }
    }
}
