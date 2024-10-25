/*The BinTree class creates a binary tree and performs
 * commands to interact with the tree. Includes
 * adding, searching, deleting and editing a node
 * within the tree
 */
//import java.util.Queue; //quene class 
public class BinTree<T extends Game> 
{
    private Node<T> root; //root pointer 

    //constructurs
    public BinTree() {root = null;}

	//mutators
    public void insert(T payload) //insert/add a node to the BST
    {
        Node<T> newNode = new Node<T>(payload);
        if(root == null) //root is null
        {
            root = newNode;
        }
        else
        {
            addRecursive(root, newNode);
        }
    }

    //used to recursive call insert
    private void addRecursive(Node<T> curr, Node<T> newNode) 
    {
        if (newNode.compareTo(curr) < 0) //new node is less than current node, left path 
        {
            if(curr.getLeftNode() == null) //add if null
            {
                curr.setLeftNode(newNode);
            }   
            else //right path 
            {
                addRecursive(curr.getLeftNode(), newNode);
            }
        }
        else //new node is greater than current node, right path
        {
            if(curr.getRightNode() == null) //add if null
            {
                curr.setRightNode(newNode);
            }   
            else //right path 
            {
                addRecursive(curr.getRightNode(), newNode);
            }
        }
    }

    public Node<T> search(T targetPayload) //search the BST for a specific node, says a not found message if node isnt found
    {
        Node<T> targetNode = new Node<T>(targetPayload);
        //call recursive funcrion
        return searchRecursive(root, targetNode);
    }

    // public Node<T> search(Node<T> targetNode) //search the BST for a specific node, says a not found message if node isnt found
    // {
    //     //call recursive funcrion
    //     return searchRecursive(root, targetNode);
    // }

    private Node<T> searchRecursive(Node<T> curr, Node<T> targetNode)
    {
        if (curr == null) //null current node, nothing 
        {
            return null;
        }
        if (curr.compareTo(targetNode) > 0) //left path
        {
            return searchRecursive(curr.getLeftNode(), targetNode);
        }
        else if (curr.compareTo(targetNode) < 0) //right path
        {
            return searchRecursive(curr.getRightNode(), targetNode);
        }
        else
        {
            return curr;
        }
    }
    /* 
    public void Edit(tree, target, whatToEdit) //edit a node in the BST for its particular contents
    {

    }
    public void Delete(tree, deleteNode) //remove a node from the BST
    {

    }
     */
     //sort the tree in alphabeticly order.
    public void sort()
    {
        inOrderRec(root);
    }
    
    public void inOrderRec(Node<T> currNode)
    {
        if (currNode != null)
        {
            inOrderRec(currNode.getLeftNode());    
            System.out.println(currNode.toString());            
            inOrderRec(currNode.getRightNode()); 
        }
        
    }
    /*
    public void breathTransversal()
    {

    }
    */
}
