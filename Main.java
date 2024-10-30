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
        System.out.print("Enter the input file (existing records): "); //database file
        String inputFileName = scnr.next();
        System.out.print("Enter the database (write to) file: "); //database file
        String databaseName = scnr.next();
        System.out.print("Enter the batch (command) file: "); //batch file
        String batchName = scnr.next();
        scnr.close();
        //create binary tree
        BinTree<Game> tree = new BinTree<Game>(); //create an empty tree
        //open database file
        try
        {
            scnr = new Scanner(new File(inputFileName)); //open input (given records) file
            while(scnr.hasNextLine())
            {
                String line = scnr.nextLine();
                String arr[] = line.split(", "); //split based on comma + space
                Game newGame = new Game(arr[0], Integer.parseInt(arr[1]), arr[2], Integer.parseInt(arr[3])); //create new game obj
                tree.insert(newGame); //insert it to the BST
            }
            scnr.close(); //close input file
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Input file not found");
        }
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
                else if(num.equals("3")) //edit record
                {
                    int beginName = line.indexOf("\""); //find first quotation mark
                    int endName = line.indexOf("\"", beginName + 1); //find second quotation mark
                    String name = line.substring(beginName+1, endName); //get the name
                    String arr[] = line.substring(endName + 2).split(" "); //split based on space, arr[0] = fieldnumber, arr[1] = value
                    Game searchGame  = new Game(name, 0, "", 0); //create new "game" to edit for it
                    Node<Game> targetNode = tree.search(searchGame); //stores the node if game is found
                    if(targetNode != null) //found game
                    {
                        Game payloadToUpdate = targetNode.getPayload();
                        String field = null;
                        if(arr[0].equals("1"))
                        {
                            payloadToUpdate.setHighScore(Integer.parseInt(arr[1]));
                            field = "UPDATE TO high score - VALUE " + arr[1];
                        }
                        else if(arr[0].equals("2"))
                        {
                            payloadToUpdate.setInitals(arr[1]);
                            field = "UPDATE TO initials - VALUE " + arr[1];
                        }
                        else if(arr[0].equals("3"))
                        {
                            payloadToUpdate.setPlays(Integer.parseInt(arr[1]));
                            field = "UPDATE TO plays - VALUE " + arr[1];
                        }
                        Game updatedGame = tree.edit(searchGame, payloadToUpdate); 
                        //print out contents of edited node
                        System.out.println(name + " UPDATED");
                        System.out.println(field);
                        System.out.println("High Score: " + updatedGame.getHighScore());
                        System.out.println("Initials: " + updatedGame.getInitals());
                        System.out.println("Plays: " + updatedGame.getPlays());
                        System.out.printf("Revenue: $%.2f\n", updatedGame.getRevenue());
                        System.out.println();
                    }
                    else
                    {
                        System.out.println(name + " not found");
                    }
                }
                else if(num.equals("4")) //delete a node
                {
                    String nameToDelete = line.substring(2);
                    Game deleteGame  = new Game(nameToDelete, 0, "", 0); //create new "game" to edit for it
                    Game deleteNode = tree.delete(deleteGame); //stores the node if game is found
                    if(deleteNode != null) //found
                    {
                        System.out.println("RECORD DELETED");
                        System.out.println("Name: " + deleteNode.getName());
                        System.out.println("RECORD DELETED");
                        System.out.println("RECORD DELETED");
                        System.out.println("RECORD DELETED");
                    }
                    else
                    {
                        System.out.println(nameToDelete + " NOT FOUND");
                    }
                }
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