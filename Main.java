import java.io.*;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Database db = null;
        System.out.println("*****************************Database Record Options*******************************");
        System.out.println("Record of 1. Student or, 2. Personal record");
        System.out.println("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        DbObject record = null;
        switch(choice)
        {
            case 1:
                record = new Student();
                db = new Database("student.txt");
                break;
            case 2:
                record = new Personal();
                db = new Database("personal.txt");
                break;
            default:
                break;
        }
        try{
            db.run(record);
        }catch(IOException e)
        {
            System.err.println("IOException encountered!");
        }
    }
}