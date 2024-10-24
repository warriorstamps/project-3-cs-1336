/*The Node class creates a node to be used as a binary
 * search tree (BST) full of game objects
*/
public class Node<T extends Game> implements Comparable<Node<T>>
{
    //variables 
    private Node<T> left; //left pointer
	private Node<T> right; //right pointer
	private T payload;

	//constructors
	public Node(T payload)
    {
	    this.payload = payload; 
    }   

	//accessors
	public T getPayload()
	{
		return payload; 
	}

    public Node<T> getLeftNode()
    {
        return left;
    }

    public Node<T> getRightNode()
    {
        return right;
    }

	//mutators
	public void setPayload(T payload)
	{
		this.payload = payload;
	}

    public void setLeftNode(Node<T> left)
    {
        this.left = left;
    }

    public void setRightNode(Node<T> right)
    {
        this.right = right;
    }

    @Override
    public int compareTo(Node<T> targetNode) 
    {
        return this.payload.compareTo(targetNode.payload);
    }

    //uses game toString
    @Override 
    public String toString()
    { 
        return this.payload.toString(); 
    }

}
