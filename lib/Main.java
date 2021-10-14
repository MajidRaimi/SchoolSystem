import java.io.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> studentsArr = new ArrayList<Student>();

    public static void main(String[] args) throws IOException {

        String path = "Data.csv" ; 

        readFile(path);

        while (true) {
            clrscr();
            System.out.println("   ---  Menu  ---   ");
            System.out.println("\n1.   Add Student ");
            System.out.println("2.   Update Student");
            System.out.println("3.   Remove Student");
            System.out.println("4.   Display Student");
            System.out.println("5.   Exit");
            System.out.print("\nPlease Enter Your Option : ");
            int option = scanner.nextInt();

            if ((studentsArr.size() == 0 && option != 1)) {
                clrscr();
                System.out.println("No Data");
                break;
            }

            switch (option) {
                case 1:
                    clrscr();
                    addStudent();
                    break;
                case 2:
                    clrscr();
                    updateStudent();
                    break;
                case 3:
                    clrscr();
                    removeStudent();
                    break;
                case 4:
                    clrscr();
                    displayStudent();
                    break;
                case 5:
                    clrscr();
                    System.out.println("Thank You For Using Our Program");
                    option = -1;
                    break;
                default:
                    option = -1;
                    clrscr();
                    System.out.println("Wrong Input");
            }

            if (option == -1) {
                break;
            }

        }

        writeFile(path);


    }

    public static void clrscr() {
        // Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static void addStudent() {
        System.out.print("Please Enter Students Full Name : ");
        String fName = scanner.next();
        String lName = scanner.next();

        System.out.print("Please Enter Students Id : ");
        int id = scanner.nextInt();

        System.out.print("Please Enter Students BirthDay ( dd/mm/yyyy ): ");
        String birthDayInput = scanner.next();

        String[] dayArr = birthDayInput.split("/");

        StudentDate birthDay = new StudentDate(Integer.parseInt(dayArr[0]), Integer.parseInt(dayArr[1]),
                Integer.parseInt(dayArr[2]));

        System.out.print("Please Enter Students  GPA : ");
        double gpa = scanner.nextDouble();

        Student newStudent = new Student(id, fName, lName, birthDay, gpa);

        studentsArr.add(newStudent);
        clrscr();
    }

    public static void updateStudent() {
        System.out.print("Please Enter Student's Index : ");
        int i = scanner.nextInt();
        double newGPA = 0.0 ; 
        do{
            System.out.print("Please Enter The New GPA : ");
            newGPA = scanner.nextDouble();
            if(0 <= newGPA && newGPA <= 5){
                break ; 
            }
            System.out.println("Wrong Input Please Trt Again !");
        } while ( true ) ; 
       
        studentsArr.get(i).UpdateGPA(newGPA);
        clrscr();
    }

    public static void removeStudent() {
        System.out.print("Please Enter Student's Index : ");
        int index = scanner.nextInt();
        studentsArr.remove(index);
        clrscr();
    }

    public static void displayStudent() {
        System.out.print("Please Enter Student's IndeX : ");
        int index = scanner.nextInt();
        System.out.println("Student Full Name : " + studentsArr.get(index).getName());
        System.out.println("Current Age : " + studentsArr.get(index).getAge());
        System.out.println("Current GPA : " + studentsArr.get(index).getGpa());
        System.out.print("Press Enter To Contiunue ...");
        scanner.nextLine();
        scanner.nextLine();
        clrscr();

    }

    public static void readFile(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] value = line.trim().split(",");
            StudentDate birthDay = new StudentDate(Integer.parseInt(value[3]) , Integer.parseInt(value[4]) , Integer.parseInt(value[5])) ; 
            Student newStudent = new Student(Integer.parseInt(value[2]) , value[0] , value[1] , birthDay , Double.parseDouble(value[6]));
            // Student newStudent = new Student(id, fName, lName, birthDay, gpa);
            studentsArr.add(newStudent) ; 
        }

    }


    public static void writeFile(String path) throws IOException {
        PrintWriter pw = new PrintWriter(new File(path));
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < studentsArr.size() ; i++){
            //Majid,Saleh,4948,12,15,2001,4.9
            sb.append(studentsArr.get(i).fName) ; 
            sb.append(",") ; 
            sb.append(studentsArr.get(i).lName) ;
            sb.append(",") ; 
            sb.append(studentsArr.get(i).id) ; 
            sb.append(",") ; 
            sb.append(studentsArr.get(i).birthDay.day) ; 
            sb.append(",") ; 
            sb.append(studentsArr.get(i).birthDay.month) ; 
            sb.append(",") ; 
            sb.append(studentsArr.get(i).birthDay.year) ; 
            sb.append(",") ;
            sb.append(studentsArr.get(i).getGpa()) ;
            sb.append("\n") ; 
        }
        
        // Majid  ,Saleh,4948,12,15,2001,4.9

        pw.write(sb.toString());
        pw.close();
        

    }

}