import java.lang.NullPointerException;

public class Tree<AnyType extends Comparable<AnyType>>
{
    
    private Node<AnyType> root;
	// private int size;

    public void buildTree(AnyType data)
    {
        root = buildTree(root, data);
		// size = 0;
    }
    private Node<AnyType> buildTree(Node<AnyType> root, AnyType data)
    {
        if(root == null)
        {
            return new Node<AnyType>(data);
        }
        else if(data.compareTo(root.data) < 0)
		{
			root.left = buildTree(root.left, data);
		}
        else if(data.compareTo(root.data) > 0)
		{
			root.right = buildTree(root.right, data);
		}
        else
        {
			System.out.print("Warning: Build attempted to insert " +
							 "duplicate " + data + ". Bypassed...");
        }
        return root;
    }

    public void printInorder()
	{
		System.out.print("In-order Traversal:");
		printInorder(root);
		System.out.println();
	}
	private void printInorder(Node<AnyType> root)
	{
		if(root == null)
		{
			throw new NullPointerException("Error: Parameter 'root' was null " +
										   "inside function 'printInorder'.");
		}

		printInorder(root.left);
		System.out.print(" " + root.data);
		printInorder(root.right);
	}

    public void printPreorder()
	{
		System.out.print("Pre-order Traversal:");
		printPreorder(root);
		System.out.println();
	}
	private void printPreorder(Node<AnyType> root)
	{
		if(root == null)
		{
			throw new NullPointerException("Error: Parameter 'root' was null " +
										   "inside function 'printPreorder'.");
		}

		System.out.print(" " + root.data);
		printPreorder(root.left);
		printPreorder(root.right);
	}

    public void printPostorder()
	{
		System.out.print("Post-order Traversal:");
		printPostorder(root);
		System.out.println();
	}

	private void printPostorder(Node<AnyType> root)
	{
		if(root == null)
		{
			throw new NullPointerException("Error: Parameter 'root' was null " +
										   "inside function 'printPostorder'.");
		}

		printPostorder(root.left);
		printPostorder(root.right);
		System.out.print(" " + root.data);
	}

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