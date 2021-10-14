import java.time.LocalDate;
import java.util.*;

public class Student {

    public int id;
    String fName;
    String lName;
    StudentDate birthDay;
    private double gpa;

    public static int StudCount = 0;

    

    public Student(int id ,  String fName, String lName, StudentDate birthDay, double gpa) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.birthDay = birthDay;
        this.gpa = gpa;
        StudCount++;
    }

    

    double getGpa() {
        return this.gpa;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        return today.getYear() - birthDay.getYear();
    }

    public Student() {
        StudCount++;
    };

    void UpdateGPA(double gpa){
        this.gpa = (this.gpa + gpa) / 2 ; 
    }

    public String getName(){
        return this.fName.trim() + " " + this.lName.trim() ; 
    }


}

class StudentDate {
    int day;
    int month;
    int year;

    StudentDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }

}
