/*The game class stores the contents of each game */
public class Game implements Comparable<Game>

{
    //variables 
    public String Name;
    public double highscore; //(could be int if they don't allow doubles)
    public String initials;
    public int plays;
    //constructors
    public Game(String n, double h, String i, int p)
    {
        Name = n;
        highscore = h;
        initials = i;
        plays = p;
    }
    //accessors
    public String getName() {return Name;}
    public double getHighScore() {return highscore;}
    public String getInitals() {return initials;}
    public int getPlays() {return plays;}
    public double getRevenue(){return plays * 0.25;} //may need this??
    //mutators
    public void setName(String newName) {Name = newName;}
    public void setHighScore(double newHighScore) {highscore = newHighScore;}
    public void setInitals(String newInitals){initials = newInitals;}
    public void setPlays(int newPlays) {plays = newPlays;}

    @Override
    public String toString()
    {
        return Name + ", " + highscore + ", " + initials + ", " + plays + ", $" + getRevenue();
    }

    @Override
    public int compareTo(Game object) 
    {
        //work in progress
        if(Name.compareTo(object.getName()) > 0) //name greater than object (B > A)
        {
            return 1;
        }
        else if(Name.compareTo(object.getName()) < 0) //name less than object (A < B)
        {
            return -1;
        }
        else //equal
        {
            return 0;
        }
    }
    
}
