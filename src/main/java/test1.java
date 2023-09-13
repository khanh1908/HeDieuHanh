/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pkhanh
 */
import java.io.File;  
import java.io.RandomAccessFile;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;

public class test1 
{
    public static void DeleteFile(File file) throws IOException 
    {
        if (file.exists()) 
        {
            Double length = Math.ceil(file.length()/512.0); // tính số sector của file
            SecureRandom random = new SecureRandom();
            RandomAccessFile raf = new RandomAccessFile(file, "rws");
            raf.seek(0);
            raf.getFilePointer();
            byte[] data = new byte[64];
            int location = 0;
            while (location < length) 
            {
                random.nextBytes(data);
                raf.write(data);
                location += data.length;
            }
            raf.setLength(0);
            raf.close(); 
            
            Path Path= Paths.get(file.getAbsolutePath());
                    
            Calendar time = Calendar.getInstance();
            time.set(2001, Calendar.AUGUST,1);
            Files.setLastModifiedTime(Path, FileTime.fromMillis(time.getTimeInMillis()));
            Files.setAttribute(Path,"creationTime", FileTime.fromMillis(time.getTimeInMillis()));
            Files.setAttribute(Path,"lastAccessTime", FileTime.fromMillis(time.getTimeInMillis()));
            System.out.println(Path);
            Files.delete(Path);                 
        }
    }
    public static void main(String[] args) throws IOException 
    {
      try  
        {   
            File f = new File("E:\\snake.png");
            if(f.exists())
            {
                DeleteFile(f);
                System.out.println("Success!");
            }
            else 
            {
                System.out.println("File doesn't exist");
            }
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }
    } 
}
