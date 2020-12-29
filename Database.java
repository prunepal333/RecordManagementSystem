import java.io.*;
import java.util.Scanner;

public class Database
{
    private RandomAccessFile db;
    private Scanner sc = new Scanner(System.in);
    private String filename;

    public Database(String filename)
    {
        this.filename = filename;
    }
    public void add(DbObject record) throws IOException
    {
        db = new RandomAccessFile(filename, "rw");
        db.seek(db.length());
        record.writeToFile(db);
        db.close();
    }
    public void update(int id, DbObject newRecord) throws IOException
    {
        DbObject record = newRecord instanceof Student ? (new Student()) : (new Personal());
        db = new RandomAccessFile(filename, "rw");
        db.seek(0);
        while(db.getFilePointer() < db.length()){
            record.readFromFile(db);
            if(record.getUID() == id)
            {
                db.seek(db.getFilePointer() - record.size());
                newRecord.writeToFile(db);
            }
        }
        System.err.println("Record to modify not found");
    }
    //Search Operation
    public boolean search(int id, boolean isStudent) throws IOException,EOFException
    {
        DbObject record;
        if(isStudent) record = new Student();
        else    record = new Personal();
        db = new RandomAccessFile(filename, "r");
        db.seek(0);
        while(db.getFilePointer() < db.length()){
            record.readFromFile(db);
            if(record.getUID() == id)
            {
                return true;
            }
        }
        return false;
    }
    public DbObject retrieve(int id, boolean isStudent) throws IOException,EOFException
    {
        DbObject record;
        if(isStudent) record = new Student();
        else    record = new Personal();
        db = new RandomAccessFile(filename, "r");
        db.seek(0);
        while(db.getFilePointer() < db.length() - 1)
        {
            System.out.println(db.getFilePointer() + ", " + db.length());
            record.readFromFile(db);
            System.out.println("Size: " + record.size());
            if(record.getUID() == id){
                db.close();
                return record;
            }
        }
        db.close();
        return record;
    }
    
    public void delete(int id, boolean isStudent) throws IOException, EOFException
    {
        DbObject record;
        record = isStudent? new Student() : new Personal();
        db = new RandomAccessFile(filename, "rw");
        db.seek(0);
        while(db.getFilePointer() < db.length() - 1)
        {
            record.readFromFile(db);
            if(record.getUID() == id)
            {
                do{
                    db.seek(db.getFilePointer() - 2 * record.size());
                    record.writeToFile(db);
                    db.seek(db.getFilePointer() + record.size());
                    record.readFromFile(db);
                }while(db.getFilePointer() < db.length() - 1);
            }
        }
    }
    public void run(DbObject record) throws IOException
    {
        boolean isStudent  = record instanceof Student;
        mainMenu();
        int choice;
        int id;
        choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                record.readFromConsole();
                add(record);
                break;
            case 2:
                System.out.println("Enter the id of record to be retrieved: ");
                id = sc.nextInt();
                sc.nextLine();
                record = retrieve(id, isStudent);
                record.display();
                break;
            case 3:
                System.out.println("Enter the id of the record to be modified: ");
                id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter new record: ");
                record.readFromConsole();
                update(id, record);
                break;
            case 4:
                System.out.println("Enter the id of the record to be modified: ");
                id = sc.nextInt();
                sc.nextLine();
                delete(id, isStudent);
            default:
                System.out.println("Invalid option!\n");
                break;
        }
    }
    public void mainMenu()
    {
        System.out.println("1. Create a record\n" + 
                "2. Retrieve a record\n" + 
                "3. Update a record\n" + 
                "4. Delete a record"
        );
    }
}