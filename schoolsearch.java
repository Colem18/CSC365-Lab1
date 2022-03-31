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
            if(tokens[0].equals("T:") || tokens[0].equals("Teacher:")){
                printStudentsByTeacher(entries, tokens[1]);
            }
            if(tokens.length == 1){
                if(tokens[0].equals("Q") || tokens[0].equals("Quit")){
                    quitFlag = 1;
                }
            }
        }
    }

    public static void printStudentsByTeacher(ArrayList<Entry> entries, String teacherLName){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).tLastName.equals(teacherLName.toUpperCase())){
                System.out.println(entries.get(i).stLastName + ", " + entries.get(i).stFirstName);
            }
        }
    }

    public static void studentBus(ArrayList<Entry> entries, String lastName){
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i).stLastName.equals(lastName.toUpperCase())){
                System.out.println(entries.get(i).stLastName+", "+entries.get(i).stFirstName+", "+entries.get(i).bus);
            }
        }
    }

    public static void student(ArrayList<Entry> entries, String lastName){
        for(int i = 0; i <entries.size(); i++){
            if(entries.get(i).stLastName.equals(lastName.toUpperCase())){
                System.out.println(entries.get(i).stLastName+", "+entries.get(i).stFirstName+", "+entries.get(i).grade+
                ", "+entries.get(i).classroom+", "+entries.get(i).tLastName+", "+entries.get(i).tFirstName);
            }
        }
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
        for(int i = 0; i <entries.size(); i++){
            System.out.println(entries.get(i).stFirstName + " " +entries.get(i).stLastName);
            System.out.println("\tGrade: " + entries.get(i).grade);
            System.out.println("\tClassroom: " + entries.get(i).classroom);
            System.out.println("\tBus: " + entries.get(i).bus);
            System.out.println("\tGPA: " + entries.get(i).gpa);
            System.out.println("\tTeacher: " + entries.get(i).tFirstName + " " + entries.get(i).tLastName);
        }
    }

}
