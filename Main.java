/* Michael Han 
 * meh220003
 * This program reads from a batch file and proccesses some commands in the batch file
 * It uses a binary search tree to store the data in memory. 
 * after all the commands are used, it uses a breath-first transversal to write to
 * a user inputed database file.
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Main
{
    public static void main(String [] args)
    {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the database file: "); //database file
        String databaseName = scnr.next();
        System.out.print("Enter the batch file: "); //batch file
        String batchName = scnr.next();
        scnr.close();
        //create binary tree
        BinTree<Game> tree = new BinTree<Game>(); //create an empty tree
        //open batch file
        try
        {
            scnr = new Scanner(new File(batchName)); //open batch file
            while(scnr.hasNextLine())
            {
                String line = scnr.nextLine();
                String num = line.substring(0,  1); //get first number 
                if(num.equals("1")) //add a record
                {
                    int beginName = line.indexOf("\""); //find first quotation mark
                    int endName = line.indexOf("\"", beginName + 1); //find second quotation mark
                    String name = line.substring(beginName+1, endName); //get the name
                    String arr[] = line.substring(endName + 2).split(" "); //split based on space
                    Game newGame = new Game(name, Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2])); //create new game obj
                    tree.insert(newGame); //insert it to the BST
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
                    String searchTerm = line.substring(2); //get the search term
                    Game searchGame  = new Game(searchTerm, 0, "", 0); //create new "game" to search for it
                    Node<Game> check = tree.search(searchGame); //stores the node if game is found
                    if(check == null) //null
                    {
                        System.out.println(searchTerm + " NOT FOUND"); //not found message
                        System.out.println();
                    }
                    else
                    {
                        System.out.println(searchTerm + " FOUND"); //found message 
                        //print contents of game
                        System.out.println("High Score: " + check.getPayload().getHighScore());
                        System.out.println("Initials: " + check.getPayload().getInitals());
                        System.out.println("Plays: " + check.getPayload().getPlays());
                        System.out.printf("Revenue: $%.2f\n", check.getPayload().getRevenue());
                        System.out.println();
                    }
                }
                /*implement edit/delete later */
                else if(num.equals("5")) //sort 
                {
                    System.out.println("RECORDS SORTED ASCENDING");
                    tree.sort();
                    System.out.println();
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

        // write contents from bintree to database file using breath-first transversal
        try
        {
            FileWriter writer = new FileWriter(databaseName); //create a fileWriter
            ArrayList<Game> games = tree.breathTransversal(); //create a arrayList of games to be written on
            for (Game game : games) //for each game object on the list
            {
                writer.write(game.toString() + "\n"); //write to the database file
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.print("Database File not found");
        }
    }
}