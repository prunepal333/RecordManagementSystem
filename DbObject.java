import java.io.*;
import java.util.*;
abstract class DbObject
{
    //object for reading from stdin console
    Scanner sc = new Scanner(System.in);
    public void writeString(String s, RandomAccessFile out) throws IOException{
        out.writeChars(s);
    }
    public String readString(int len, RandomAccessFile in) throws IOException{
        StringBuffer s = new StringBuffer(len);
        for(int i = 0; i < len; ++i)
        {
            s.append(in.readChar());
        }
        return s.toString();
    }
    abstract public void writeToFile(RandomAccessFile out) throws IOException;
    abstract public void readFromFile(RandomAccessFile in) throws IOException;
    abstract public void readFromConsole() throws IOException;
    abstract public int getUID();
    abstract public void display();
    abstract public long size();
}