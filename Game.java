/* Michael Han 
 * meh220003
 * The game class stores the games data
*/
public class Game implements Comparable<Game>
{
    //variables 
    private String name;
    private int highscore; 
    private String initials;
    private int plays;

    //constructors
    public Game(String n, int h, String i, int p)
    {
        name = n;
        highscore = h;
        initials = i;
        plays = p;
    }

    //accessors
    public String getName() {return name;}
    public int getHighScore() {return highscore;}
    public String getInitals() {return initials;}
    public int getPlays() {return plays;}
    public double getRevenue() {return plays * 0.25;} //edit will auto update if changed

    //mutators
    public void setName(String newName) {name = newName;}
    public void setHighScore(int newHighScore) {highscore = newHighScore;}
    public void setInitals(String newInitals) {initials = newInitals;}
    public void setPlays(int newPlays) {plays = newPlays;}

    //toString method for game formatted
    @Override
    public String toString()
    {
        return name + ", " + highscore + ", " + initials + ", " + plays + ", $" + String.format("%.2f", getRevenue());
    }

    //compareTo method for names, used in search, edit, and delete
    private int compareTo(String targetName) 
    {
        //work in progress
        if(name.compareTo(targetName) > 0) //name greater than object (B > A)
        {
            return 1;
        }
        else if(name.compareTo(targetName) < 0) //name less than object (A < B)
        {
            return -1;
        }
        else //equal
        {
            return 0;
        }
    }

    //compareTo method for names, used in search, edit, and delete
    @Override
    public int compareTo(Game targetGame) 
    {
        return this.compareTo(targetGame.getName());
    }
}
