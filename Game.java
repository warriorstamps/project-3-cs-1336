/*The game class stores the contents of each game */
public class Game implements Comparable<Game>

{
    //variables 
    private String name;
    private int highscore; //(could be int if they don't allow doubles)
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
    public double getRevenue() {return plays * 0.25;} //may need this for edit

    //mutators
    public void setName(String newName) {name = newName;}
    public void setHighScore(int newHighScore) {highscore = newHighScore;}
    public void setInitals(String newInitals) {initials = newInitals;}
    public void setPlays(int newPlays) {plays = newPlays;}

    //toString method for game formatted
    @Override
    public String toString()
    {
        return name + ", " + highscore + ", " + initials + ", " + plays + ", $" + getRevenue();
    }

    //compareTo method for names, used in search? and delete?? or sort??
    public int compareTo(String targetName) 
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

    //compareTo method for names, used in search? and delete?? or sort??
    @Override
    public int compareTo(Game targetGame) 
    {
        return this.compareTo(targetGame.getName());
    }
}
