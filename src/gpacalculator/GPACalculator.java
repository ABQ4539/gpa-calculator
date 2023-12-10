/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package gpacalculator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GPACalculator {
        
        static String courseName;
        static int courseUnit;
        static double courseScore;
        static String courseGrade;
        static double gradeUnit;

    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        List<CourseEntry> courseEntries = new ArrayList<>();
        
        while(true){
            System.out.println("Enter Course Name & Course Code in the form 'MAT101' OR type 'DONE' to exit the system: ");
            courseName = userInput.next();
            
            if ("DONE".equals(courseName.toUpperCase())){
                break;
            }

            System.out.print("Enter Course Unit: ");
            courseUnit = userInput.nextInt();
            
            System.out.print("Enter Course Score: ");
            courseScore = userInput.nextDouble();

            CourseEntry entry = new CourseEntry(courseName, courseUnit, courseScore, courseGrade);
            courseEntries.add(entry);
            
        }

        System.out.println("|---------------|---------------|-------|---------------|");
        System.out.println("| COURSE & CODE | COURSE UNIT   | GRADE | GRADE-UNIT    |");
        System.out.println("|---------------|---------------|-------|---------------|");

        for (CourseEntry entry : courseEntries) {
            System.out.println(entry);
        }

        double GPA = calculateGPA(courseEntries);
        System.out.println("|-------------------------------------------------------|");
        System.out.println("");
        System.out.printf("Your GPA is = %.2f to 2 decimal places", GPA);
    }
        

    private static double calculateGPA(List<CourseEntry> courseEntries) {
        int gradeUnitSum = 0;
        double qualityPointSum = 0;

        for (CourseEntry entry : courseEntries) {
            gradeUnitSum += entry.getCourseUnit();
            qualityPointSum += (entry.getCourseUnit() * entry.getGradeUnit());
        }

        return qualityPointSum / gradeUnitSum;
    }
    
    private static String scoreToGrade(double score){
        if(score >= 70 && score <= 100){
            return "A";
        } else if(score >= 60){
            return "B";
        } else if(score >= 50){
            return "C";
        } else if(score >= 45){
            return "D";
        } else if(score >= 40){
            return "E";
        } else{
            return "F";
        }
    }
    
    private static double calculateGradeUnit(String grade){
        switch (grade.toUpperCase()) {
            case "A" -> {
                return 5;
            }
            case "B" -> {
                return 4;
            }
            case "C" -> {
                return 3;
            }
            case "D" -> {
                return 2;
            }
            case "E" -> {
                return 1;
            }
            case "F" -> {
                return 0;
            }
            default -> {
                System.out.println("Invalid grade, grade assumed to be 0");
                return 0;
            }
        }
    }
    
    private static class CourseEntry {
        private String courseName;
        private int courseUnit;
        private double courseScore;
        private String courseGrade;

        public CourseEntry(String courseName, int courseUnit, double courseScore, String courseGrade) {
            this.courseName = courseName;
            this.courseUnit = courseUnit;
            this.courseScore = courseScore;
            this.courseGrade = scoreToGrade(courseScore);
        }

        public int getCourseUnit() {
            return courseUnit;
        }

        public double getGradeUnit() {
            return calculateGradeUnit(courseGrade);
        }

        @Override
        public String toString() {
            return "|" + courseName + "\t\t|" + courseUnit + "\t\t|" + courseGrade + "\t|" + getGradeUnit() + "\t\t|";
        }
    }

}
