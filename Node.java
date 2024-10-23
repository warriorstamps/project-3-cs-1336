/*The Node class creates a node to be used as a binary
 * search tree (BST) full of game objects
*/
public class Node<T extends Game> implements Comparable<Node<T>>
{
    //variables 
    Node<T> left; //left pointer
	Node<T> right; //right pointer
	T payload;

	//constructors
	public Node(T payload)
    {
	    this.payload = payload; 
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

    //uses game CompareTo
    public int compareTo(String targetName) 
    {
        return this.payload.compareTo(targetName);
    }

    @Override
    public int compareTo(Node<T> targetNode) 
    {
        return this.compareTo(targetNode);
    }

    //uses game toString
    @Override 
    public String toString()
    { 
        return this.payload.toString(); 
    }

}
