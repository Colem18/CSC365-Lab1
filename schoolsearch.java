import java.io.*;
import java.util.*;
public class schoolsearch {
    public static void main(String[] args) throws FileNotFoundException{
        File studentsFile = new File("students.txt");
        if(!studentsFile.exists()){
            System.out.println("Cannot find file students.txt");
            return;
        }

        ArrayList<Entry> entries = new ArrayList<Entry>();
        getStudentEntries(entries, studentsFile);
        //printAllEntries(entries);

        getCommands(entries);
        

    }

    public static void getCommands(ArrayList<Entry> entries){
        Scanner command = new Scanner(System.in);
        int quitFlag = 0;
        while(quitFlag == 0){
            String userCommand = command.nextLine();
            String[] tokens = userCommand.split("\\s+");
            //System.out.println(userCommand);
            //System.out.println(tokens[0]);
            if(tokens[0].equals("S:") || tokens[0].equals("Student:")){
                if(tokens.length == 3){
                    if(tokens[2].equals("B") || tokens[2].equals("Bus")){
                        studentBus(entries, tokens[1]);
                    }
                }
                if(tokens.length == 2){
                    student(entries,tokens[1]);
                }
            }
            if(tokens[0].equals("G:") || tokens[0].equals("Grade:")){
                if(tokens.length == 3){
                    if(tokens[2].equals("H") || tokens[2].equals("High")){
                        printStudentByGradeHiLo(entries, Integer.parseInt(tokens[1]), true);
                    }else if(tokens[2].equals("L") || tokens[2].equals("Low")){
                        printStudentByGradeHiLo(entries, Integer.parseInt(tokens[1]), false);
                    }
                }else if(tokens.length == 2){
                    printStudentsByGrade(entries, Integer.parseInt(tokens[1]));
                }
            }
            if(tokens.length == 2){
                if(tokens[0].equals("T:") || tokens[0].equals("Teacher:")){
                    printStudentsByTeacher(entries, tokens[1]);
                }else if(tokens[0].equals("B:") || tokens[0].equals("Bus:")){
                    printStudentsByBus(entries, Integer.parseInt(tokens[1]));
                }else if(tokens[0].equals("A:") || tokens[0].equals("Average:")){
                    printAverageGPA(entries, Integer.parseInt(tokens[1]));
                }
            }
            if(tokens.length == 1){
                if(tokens[0].equals("Q") || tokens[0].equals("Quit")){
                    quitFlag = 1;
                }
                if(tokens[0].equals("I") || tokens[0].equals("Info")){
                    info(entries);
                }
            }
        }
    }

    public static void student(ArrayList<Entry> entries, String lastName){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).stLastName.equals(lastName.toUpperCase())){
                System.out.println("Student Name: " + entries.get(i).stLastName + ", " + entries.get(i).stFirstName 
                + ", Grade: " + entries.get(i).grade + ", Classroom: " + entries.get(i).classroom 
                + ", Teacher Name: " + entries.get(i).tLastName + ", " + entries.get(i).tFirstName);
            }
        }
    }
    
    public static void studentBus(ArrayList<Entry> entries, String lastName){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).stLastName.equals(lastName.toUpperCase())){
                System.out.println("Student Name: " + entries.get(i).stLastName + ", " + entries.get(i).stFirstName + ", Bus: " + entries.get(i).bus);
            }
        }
    }
    
    public static void printStudentsByTeacher(ArrayList<Entry> entries, String teacherLName){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).tLastName.equals(teacherLName.toUpperCase())){
                System.out.println("Student Name: " + entries.get(i).stLastName + ", " + entries.get(i).stFirstName);
            }
        }
    }
    
    public static void printStudentsByGrade(ArrayList<Entry> entries, int inputGrade){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).grade == inputGrade){
                System.out.println("Student Name: " + entries.get(i).stLastName + ", " + entries.get(i).stFirstName);
            }
        }
    }
    
    public static void printStudentByGradeHiLo(ArrayList<Entry> entries, int inputGrade, boolean high){
        Entry temp = null;
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).grade == inputGrade){
                if(temp == null){
                    temp = entries.get(i);
                }else if(high == true){
                    if(entries.get(i).gpa > temp.gpa){
                        temp = entries.get(i);
                    }
                }else{
                    if(entries.get(i).gpa < temp.gpa){
                        temp = entries.get(i);
                    }
                }
            }
        }
        if(high == true){
            System.out.println("Student with target grade and highest GPA: "
            + temp.stLastName + ", " + temp.stFirstName + ", GPA: " + temp.gpa
            + ", Teacher: " + temp.tLastName + ", " + temp.tFirstName + ", Bus route: " + temp.bus);
        }else{
            System.out.println("Student with target grade and lowest GPA: "
            + temp.stLastName + ", " + temp.stFirstName + ", GPA: " + temp.gpa
            + ", Teacher: " + temp.tLastName + ", " + temp.tFirstName + ", Bus route: " + temp.bus);
        }
    }
    
    public static void printStudentsByBus(ArrayList<Entry> entries, int busNum){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).bus == busNum){
                System.out.println("Student Name: " + entries.get(i).stLastName + ", " + entries.get(i).stFirstName
                + ", grade: " + entries.get(i).grade + ", classroom: " + entries.get(i).classroom);
            }
        }
    }
    
    public static void printAverageGPA(ArrayList<Entry> entries, int targetGrade){
        double total = 0.0;
        int count = 0;
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).grade == targetGrade){
                total += entries.get(i).gpa;
                count++;
            }
        }
        System.out.print("For grade " + targetGrade + ", the average GPA is: ");
        System.out.format("%.2f", total/count);
        System.out.println();
    }

    public static void info(ArrayList<Entry> entries){
        for(int i = 0; i < 7; i++){
            int numberOfStudents = studentsInGrade(entries, i);
            System.out.println("<" + i + ">:" + numberOfStudents);
        }
    }

    public static int studentsInGrade(ArrayList<Entry> entries, int grade){
        int studentCounter = 0;
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).grade == grade){
                studentCounter++;
            }
        }
        return studentCounter;
    }

    

    public static void getStudentEntries(ArrayList<Entry> entries, File studentsFile) throws FileNotFoundException{
        Scanner students = new Scanner(studentsFile);
        String studentLine = "";
        while(students.hasNextLine()){
            studentLine = students.nextLine();
            String[] tokens = studentLine.split(",");
            Entry studentEntry = new Entry();
            studentEntry.stFirstName = tokens[1];
            studentEntry.stLastName = tokens[0];
            studentEntry.grade = Integer.parseInt(tokens[2]);
            studentEntry.classroom = Integer.parseInt(tokens[3]);
            studentEntry.bus = Integer.parseInt(tokens[4]);
            studentEntry.gpa = Double.parseDouble(tokens[5]);
            studentEntry.tLastName = tokens[6];
            studentEntry.tFirstName = tokens[7];
            entries.add(studentEntry);
        }
        students.close(); 
    }

    public static void printAllEntries(ArrayList<Entry> entries){
        for(int i = 0; i < entries.size(); i++){
            System.out.println(entries.get(i).stFirstName + " " +entries.get(i).stLastName);
            System.out.println("\tGrade: " + entries.get(i).grade);
            System.out.println("\tClassroom: " + entries.get(i).classroom);
            System.out.println("\tBus: " + entries.get(i).bus);
            System.out.println("\tGPA: " + entries.get(i).gpa);
            System.out.println("\tTeacher: " + entries.get(i).tFirstName + " " + entries.get(i).tLastName);
        }
    }

}
