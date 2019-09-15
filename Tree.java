import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class Tree<AnyType extends Comparable<AnyType>>
{   
    private Node<AnyType> root;
	private int size;

	public Tree() 
	{ 
		size = 0;
	}

	public void treeHelper(List<AnyType> data)
    {
        if(data == null)
        {
            throw new IllegalArgumentException("Error: Data cannot be null.");
        }
        for(AnyType element : data)
        {
            if(element == null)
            {
                throw new IllegalArgumentException("Error: Data Element cannot be null.");
            }
            buildTree(element);
        }
    }

    public void buildTree(AnyType data)
    {
		if(data == null)
		{
			throw new IllegalArgumentException("Error: Data cannot be null.");
		}
		if(root == null)
		{
			root = new Node<AnyType>(data);
		}
		else
		{
        	buildTree(data, root);
		}
    }
    private void buildTree(AnyType data, Node<AnyType> root)
    {
		int comparison = data.compareTo(root.getData());
        if(comparison > 0) 
		{
            if(root.getRight() == null) 
			{
                root.setRight(new Node<AnyType>(data));
            } 
			else 
			{
                buildTree(data, root.getRight());
            }
        } 
		else if(comparison < 0) 
		{
            if(root.getLeft() == null) 
			{
                root.setLeft(new Node<AnyType>(data));
            } 
			else 
			{
                buildTree(data, root.getLeft());
            }
        }
    }

    public List<AnyType> printPreorder() {
        ArrayList<AnyType> list = new ArrayList<AnyType>();
        if(root != null) {
            list.add(root.getData());
            printPreorder(list, root);
        }
        return list;
    }
    public void printPreorder(List<AnyType> list, Node<AnyType> root) {
        if(root.getLeft() != null) {
            list.add(root.getLeft().getData());
            printPreorder(list, root.getLeft());
        }
        if(root.getRight() != null) 
		{
            list.add(root.getRight().getData());
            printPreorder(list, root.getRight());
        }
    }

    public List<AnyType> printPostorder() {
        ArrayList<AnyType> list = new ArrayList<AnyType>();
        if(root != null) 
		{
            printPostorder(list, root);
        }
        return list;
    }
    public void printPostorder(List<AnyType> list, Node<AnyType> root) {
        if(root.getLeft() != null) 
		{
            printPostorder(list, root.getLeft());
        }
        if(root.getRight() != null) 
		{
            printPostorder(list, root.getRight());
        }
        list.add(root.getData());
    }

    public List<AnyType> printInorder() {
        ArrayList<AnyType> list = new ArrayList<AnyType>();
        if(root != null) {
            printInorder(list, root);
        }
        return list;
    }
    private void printInorder(List<AnyType> list, Node<AnyType> root) {
        if(root.getLeft() != null) 
		{
            printInorder(list, root.getLeft());
        }
        list.add(root.getData());
        if(root.getRight() != null) 
		{
            printInorder(list, root.getRight());
        }
    }

    // public void printInorder()
	// {
	// 	System.out.print("In-order Traversal: ");
	// 	printInorder(root);
	// 	System.out.println();
	// }
	// private void printInorder(Node<AnyType> root)
	// {
	// 	if(root == null)
	// 	{
	// 		throw new NullPointerException("Error: Parameter 'root' was null " +
	// 									   "inside function 'printInorder'.");
	// 	}

	// 	printInorder(root.left);
	// 	System.out.print(" " + root.data);
	// 	printInorder(root.right);
	// }

    // public void printPreorder()
	// {
	// 	System.out.print("Pre-order Traversal: ");
	// 	printPreorder(root);
	// 	System.out.println();
	// }
	// private void printPreorder(Node<AnyType> root)
	// {
	// 	if(root == null)
	// 	{
	// 		throw new NullPointerException("Error: Parameter 'root' was null " +
	// 									   "inside function 'printPreorder'.");
	// 	}

	// 	System.out.print(" " + root.data);
	// 	printPreorder(root.left);
	// 	printPreorder(root.right);
	// }

    // public void printPostorder()
	// {
	// 	System.out.print("Post-order Traversal: ");
	// 	printPostorder(root);
	// 	System.out.println();
	// }

	// private void printPostorder(Node<AnyType> root)
	// {
	// 	if(root == null)
	// 	{
	// 		throw new NullPointerException("Error: Parameter 'root' was null " +
	// 									   "inside function 'printPostorder'.");
	// 	}

	// 	printPostorder(root.left);
	// 	printPostorder(root.right);
	// 	System.out.print(" " + root.data);
	// }

	// Helper method for the spacing.
    // private String printSpaces(int level) {
    //     String spaces = "";
    //     for (int i = 0; i < level; i++) {
    //         spaces += " ";
    //     }
    //     return spaces;
    // }
	//firstLetter = example.substring(0, 1);
}