/*Main class */
import java.util.Scanner;
import java.io.*;

public class Main
{
    public static void main(String [] args)
    {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter the database file: "); //for database file
        scnr.next();
        //open database file
        try
        {
            System.out.print("Enter the batch file"); //for command file
            scnr.next();
    
            //open batch file
            try
            {
                while(scnr.hasNextLine())
                {
                    int num = scnr.subString(0, 2); //get first number 
                    if(num == 1) //add a record
                    {
    
                    }
                    else if(num == 2) //search for a record
                    {
    
                    }
                    else if(num == 3) //edit a record
                    {
    
                    }
                    else if(num == 4) //delete a record
                    {
    
                    }
                    else if(num == 5) //sort the records (accending alphabeticly), implement later
                    {
                        
                    }
                    else //invalid input
                    {
                        scnr.nextLine();
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.print("Batch file not found");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("Database File not found");
        }
    }
}