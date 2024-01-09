// Class representing a student
import java.util.Scanner;

class Student {
    String name;
    int numSubjects;
    int[] marks;

    // Constructor to initialize a student with a name and the number of subjects
    public Student(String name, int numSubjects) {
        this.name = name;
        this.numSubjects = numSubjects;
        this.marks = new int[numSubjects];
    }

    public Student() {
        
    }

    // Method to input marks for each subject
    public void inputMarks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter marks for each subject (out of 100):");
    
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                try {
                    System.out.print("Subject " + (i + 1) + ": ");
                    String input = scanner.nextLine();
    
                    if (input.isEmpty()) {
                        throw new NumberFormatException("Empty input. Please enter an integer.");
                    }
    
                    marks[i] = Integer.parseInt(input);
                    break; // Break the loop if input is successfully converted to an integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        }
    
        scanner.close();
    }

    // Method to calculate total marks
    public int calculateTotalMarks() {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    // Method to calculate average percentage
    public double calculateAveragePercentage() {
        int totalMarks = calculateTotalMarks();
        return (double) totalMarks / numSubjects;
    }

    // Method to determine the grade based on the average percentage
    public String calculateGrade() {
        double averagePercentage = calculateAveragePercentage();
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to display results
    public void displayResults() {
        int totalMarks = calculateTotalMarks();
        double averagePercentage = calculateAveragePercentage();
        String grade = calculateGrade();

        System.out.println("\nResults for " + name + ":");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }
    //method to get student name
     public String getStudentName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Invalid input. Please enter a valid name.");
            }
        }
    }

    // Method to get the number of subjects
    public int getNumberOfSubjects(Scanner scanner) {
        int subjects;
        while (true) {
            try {
                System.out.print("Enter number of subjects: ");
                String input = scanner.nextLine();
    
                if (input.isEmpty()) {
                    throw new NumberFormatException("Empty input. Please enter an integer.");
                }
    
                subjects = Integer.parseInt(input);
                break; // Break the loop if input is successfully converted to an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return subjects;
    }
    
    
}

