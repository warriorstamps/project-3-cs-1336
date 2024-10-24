/*Main class */
import java.util.Scanner;
import java.io.*;
public class Main
{
    public static void main(String [] args)
    {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the database file: "); //for database file
        String databaseName = scnr.next();
        System.out.print("Enter the batch file: "); 
        String batchName = scnr.next();
        scnr.close();
        //create binary tree
        BinTree<Game> tree = new BinTree<Game>(); //create an empty tree
        //open database file
        try
        {
            scnr = new Scanner(new File(databaseName));
            //store every game into a BST

            while (scnr.hasNextLine())
            {
                String line = scnr.nextLine();
                String arr[] = line.split(", "); //split by comma + space
                //name, highscore, initals, plays
                Game newGame = new Game(arr[0], Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3]));
                tree.insert(newGame); //insert a game into a tree
            }
            scnr.close();
            //tree.inOrderTransversal();            
            //open batch file
            try
            {
                scnr = new Scanner(new File(batchName));
                while(scnr.hasNextLine())
                {
                    String line = scnr.nextLine();
                    String num = line.substring(0,  1); //get first number 
                    if(num.equals("1")) //add a record
                    {
                        int beginName = line.indexOf("\"");
                        int endName = line.indexOf("\"", beginName + 1);
                        String name = line.substring(beginName+1, endName);
                        String arr[] = line.substring(endName + 2).split(" ");
                        Game newGame = new Game(name, Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
                        tree.insert(newGame);
                        System.out.println("RECORD ADDED");
                        System.out.println("Name: " + name);
                        System.out.println("High Score: " + arr[0]);
                        System.out.println("Initials: " + arr[1]);
                        System.out.println("Plays: " + arr[2]);
                        System.out.println("Revenue: " + arr[3]);
                        System.out.println(); //newline
                    } 
                    else if(num.equals("2")) //search for a record
                    {
                        String searchTerm = line.substring(2);
                        Game searchGame  = new Game(searchTerm, 0, "", 0);
                        Node<Game> check = tree.search(searchGame);
                        if(check == null) //null
                        {
                            System.out.println(searchTerm + " NOT FOUND"); //not found message
                            System.out.println();
                        }
                        else
                        {
                            System.out.println(searchTerm + " FOUND"); //found message 
                            //print contents of game
                            //System.out.println("Name: " + check.getPayload().getName());
                            System.out.println("High Score: " + check.getPayload().getHighScore());
                            System.out.println("Initials: " + check.getPayload().getInitals());
                            System.out.println("Plays: " + check.getPayload().getPlays());
                            System.out.printf("Revenue: $%.2f\n", check.getPayload().getRevenue());
                            System.out.println();
                        }
                    }
                    else //invalid input
                    {
                        scnr.nextLine();
                    }
                }
                tree.inOrderTransversal();
                //write to ciderCade.dat

                //close database and batch files
                scnr.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.print("Batch file not found");
                //close database file
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("Database File not found");
        }
    }
}