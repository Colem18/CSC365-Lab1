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
        printAllEntries(entries);

        

    }
    public static void getStudentEntries(ArrayList<Entry> entries, File studentsFile) throws FileNotFoundException{
        Scanner students = new Scanner(studentsFile);
        String studentLine = "";
        while(students.hasNextLine()){
            studentLine = students.nextLine();
            String[] tokens = studentLine.split(",");
            Entry studentEntry = new Entry();
            studentEntry.stFirstName = tokens[0];
            studentEntry.stLastName = tokens[1];
            studentEntry.grade = Integer.parseInt(tokens[2]);
            studentEntry.classroom = Integer.parseInt(tokens[3]);
            studentEntry.bus = Integer.parseInt(tokens[4]);
            studentEntry.gpa = tokens[5];
            studentEntry.tLastName = tokens[6];
            studentEntry.tFirstName = tokens[7];
            entries.add(studentEntry);
        }
        students.close(); 
    }

    public static void printAllEntries(ArrayList<Entry> entries){
        for(int i = 0; i <entries.size(); i++){
            System.out.println(entries.get(i).stFirstName + " " +entries.get(i).stLastName );
        }
    }

}
