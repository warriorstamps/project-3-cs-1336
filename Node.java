/*The Node class creates a node to be used as a binary
 * search tree (BST) full of game objects
*/
public class Node <T extends Comparable<T>> implements Comparable<Node<T>>
{
    //variables 
    T Left; //left pointer
	T Right; //right pointer
	T payload;

	//constructors
	public Node(T payload)
    {
	    this.payload = new payload<T>(); 
    }   
	//accessors
	public T getNode()
	{
		return payload; 
	}
	//mutators
	public void setNode(T payload)
	{
		this.payload = payload;
	}

    @Override
    public int compareTo(Node<T> o) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    @Override 
    public String toString()
    { 
        return Game.toString(); 
    }

}
