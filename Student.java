import java.io.*;
class Student extends Personal
{
    protected int facultyLen = 20;
    protected String faculty;
    public Student()
    {
        super();
    }
    public Student(int ssn, String n, String c, short yob, double sal, String faculty)
    {
        super(ssn, n, c, yob, sal);
        this.faculty = faculty;
        this.faculty = this.faculty.substring(0, facultyLen);
    }
    public void readFromConsole() throws IOException
    {
        super.readFromConsole();
        System.out.print("Enter faculty: ");
        this.faculty = sc.next();
        sc.nextLine();
        for(int i = this.faculty.length(); i < facultyLen; i++)
        {
            this.faculty += " ";   
        }
    }
    public void readFromFile(RandomAccessFile in) throws IOException
    {
        super.readFromFile(in);
        this.faculty = readString(facultyLen, in);
    }
    public void writeToFile(RandomAccessFile out) throws IOException
    {
        super.writeToFile(out);
        writeString(faculty, out);
    }
    public void display()
    {
        super.display();
        System.out.print(", Faculty: " + faculty.trim());
    }
    public long size()
    {
        return super.size() + 2 * facultyLen;
    }
    
    // public int getUID()
    // {
    //     return SSN;
    // }
}