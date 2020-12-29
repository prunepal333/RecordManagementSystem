import java.io.*;
class Personal extends DbObject
{
    protected final int nameLen = 50;
    protected final int cityLen = 20;
    protected int SSN;
    protected String name;
    protected String city;
    protected short yearOfBirth;
    protected double salary;
    Personal()
    {
        //no-arg constructor, as intended
    }
    Personal(int SSN, String name, String city, short yearOfBirth, double salary)
    {
        this.SSN = SSN;
        this.name = name;
        this.city = city;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }

    public void readFromConsole() throws IOException
    {
        System.out.println("Fetching information from console...");
        System.out.print("Enter the ID: ");
        this.SSN = sc.nextInt();
        System.out.print("Enter the name: ");
        
        this.name = sc.next();
        sc.nextLine();
        for(int i = this.name.length(); i < nameLen; i++)
        {
            this.name += " ";
        }
        this.name = this.name.substring(0,nameLen);
        System.out.print("Enter the city: ");
        
        this.city = sc.next();
        sc.nextLine();
        for(int i = this.city.length(); i < cityLen; i++)
        {
            this.city += " ";
        }
        this.city = this.city.substring(0, cityLen);
        System.out.print("Enter the year of birth: ");
        this.yearOfBirth = sc.nextShort();
        System.out.println("Enter the salary: ");
        this.salary = sc.nextDouble();
    }
    public void readFromFile(RandomAccessFile in) throws IOException
    {
        this.SSN = in.readInt();
        this.name = readString(nameLen, in);
        this.city = readString(cityLen, in);
        this.yearOfBirth = in.readShort();
        this.salary = in.readDouble();
    }
    public void writeToFile(RandomAccessFile out) throws IOException
    {
        out.writeInt(this.SSN);
        writeString(this.name, out);
        writeString(this.city, out);
        out.writeShort(this.yearOfBirth);
        out.writeDouble(this.salary);  
    }
    public int getUID()
    {
        return this.SSN;
    }
    public void display()
    {
        System.out.print("SSN: " + SSN + ", Name: " + name.trim() + ", City: " + city.trim() + ", Year of Birth: " + yearOfBirth + ", Salary: " + salary);
    }
    public long size()
    {
        return 14 + 2 * nameLen + 2 * cityLen;
    }
}