/*Main class */
import java.util.Scanner;
import java.io.*;

public class Main
{
    public static <T> void main(String [] args)
    {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter the database file: "); //for database file
        String databaseName = scnr.next();
        scnr = new Scanner(new File(databaseName));
        //open database file
        try
        {
            System.out.print("Enter the batch file"); //for command file
            String batchName = scnr.next();
            scnr = new Scanner(new File(batchName));
            //open batch file
            try
            {
                while(scnr.hasNextLine())
                {
                    String line = scnr.nextLine();
                    String num = line.substring(0, 2); //get first number 
                    if(num == "1") //add a record
                    {
                        T payload = (T)line.substring(3);
                        insert(payload);
                    }
                    else if(num == "2") //search for a record
                    {
                        String searchTerm = line.substring(3);
                        Node<T> check = (Node<T>)search(searchTerm);
                        if(check == null) //null
                        {
                            System.out.println(searchTerm + " NOT FOUND"); //not found message 
                        }
                        else
                        {
                            System.out.println(searchTerm + " FOUND"); //found message 
                            //print contents of game
                            System.out.println("Name: " + node.getName());
                            System.out.println("Highscore: " + node.getHighScore());
                            System.out.println("Initals: " + node.getInitals());
                            System.out.println( "Plays: " + node.getPlays());
                            System.out.println( "Revenue: " + node.getRevenue());
                        }
                    }
                    /* 
                    else if(num == "3") //edit a record
                    {
    
                    }
                    else if(num == "4") //delete a record
                    {
                        if() //null
                        {
                            //not found message here
                        }
                        else
                        {
                            //print cont.
                        }
                    }
                    else if(num == "5") //sort the records (accending alphabeticly), implement later
                    {
                        
                    }
                    */
                    else //invalid input
                    {
                        scnr.nextLine();
                    }
                }
                //write to ciderCade.dat

                //close database and batch files
                batchName.close();
                databaseName.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.print("Batch file not found");
                //close database file
                databaseName.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("Database File not found");
        }
    }
}