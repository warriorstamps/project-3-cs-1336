/* Michael Han 
 * meh220003
 * The BinTree class creates a binary tree and performs
 * commands to interact with the tree. Includes
 * adding, searching, deleting and editing a node
 * within the tree
 */
import java.util.Queue; //queue class 
import java.util.LinkedList; //linked list class, used for queue
import java.util.ArrayList; //arrayList class, used in breathTransversal function to return a list of games

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

    //function to recursive call search 
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
    
    public T edit(T targetPayload, T newPayload) //edit a node in the BST for its particular contents
    {
        //call search function to return the node
        Node<T> nodeToEdit = search(targetPayload);
        //check if it is found
        if(nodeToEdit != null) 
        {
            nodeToEdit.setPayload(newPayload); //set new payload  
        }
        //return the edited node's payload
        return nodeToEdit.getPayload(); 
    }
    /*TO DO */
    public T delete(T deleteNode) //remove a node from the BST
    {
        Node<T> isValid = search(deleteNode); //search the node to be deleted
        if(isValid != null)
        {
            //call deleteRecursive method
            deleteRecursive(root, deleteNode);
            return deleteNode;
        }
        else
        {
            System.out.println("Node is not in list");
            return null; 
        }
        
    }

    //helper to recursivly delete a node
    public T deleteRecursive(Node<T> root, T deleteNode)
    {
        //0 children (leaf node)
        if (root.getLeftNode() == null && root.getRightNode() == null) 
        {
            return deleteNode;
        }
        //1 children
        else if ((root.getLeftNode() == null && root.getRightNode() != null) || (root.getLeftNode() != null && root.getRightNode() == null)) 
        {
            if(root.getLeftNode() != null) //left child
            {
                return root.getLeftNode();
            }
            else //right child
            {
                return root.getRightNode();
            }
        }
        //2 children 
        else
        {
            return delete(null); 
        }
    }

    //sort the tree in alphabetically order.
    public void sort()
    {
        inOrderRec(root);
    }
    
    //inOrder transversal 
    public void inOrderRec(Node<T> currNode)
    {
        if (currNode != null)
        {
            inOrderRec(currNode.getLeftNode());    
            System.out.println(currNode.toString());            
            inOrderRec(currNode.getRightNode()); 
        }
    }
    
    /*Breath-first transversal */
    public ArrayList<T> breathTransversal()
    {
        //create arrayList to return game objects
        ArrayList<T> list = new ArrayList<T>(); 
        //create queue
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root); //enqueue root
        //first add the root to the quene
        while (!queue.isEmpty())
        {
            Node<T> curr = queue.poll(); //dequeue the first node in list
            if (curr != null)
            {
                list.add(curr.getPayload()); //add game to arrayList
                if (curr.getLeftNode() != null) //left node isn't null
                {
                    //add to quene
                    queue.add(curr.getLeftNode());
                }
                if (curr.getRightNode() != null) //right node isn't null
                {
                    //add to quene
                    queue.add(curr.getRightNode());
                }
            }
        }
        return list;
    }
}
