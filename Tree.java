import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Tree<AnyType extends Comparable<AnyType>>
{   
    final static Charset ENCODING = StandardCharsets.UTF_8;
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

    public void printHelper(String fileName) throws IOException
    { 
        List<AnyType> treeDataList = new ArrayList<AnyType>();
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
        {
            System.out.print("Writing traversal data to file " + fileName + "...");
            treeDataList = printPreorder();
            
            Object[] treeDataArr = treeDataList.toArray();
            // for(AnyType treeData : treeDataList)
            // {
            //     writer.write(treeData);
            //     writer.newLine();
            // }
            
            // CHARARRAYWRITER
            // writer.close();
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

    // public List<AnyType> printPostorder() {
    //     ArrayList<AnyType> list = new ArrayList<AnyType>();
    //     if(root != null) 
	// 	{
    //         printPostorder(list, root);
    //     }
    //     return list;
    // }
    // public void printPostorder(List<AnyType> list, Node<AnyType> root) {
    //     if(root.getLeft() != null) 
	// 	{
    //         printPostorder(list, root.getLeft());
    //         System.out.println(list);
    //     }
    //     if(root.getRight() != null) 
	// 	{
    //         printPostorder(list, root.getRight());
    //         System.out.println(list);
    //     }
    //     list.add(root.getData());
    // }

    // public List<AnyType> printInorder() {
    //     ArrayList<AnyType> list = new ArrayList<AnyType>();
    //     if(root != null) {
    //         printInorder(list, root);
    //     }
    //     return list;
    // }
    // private void printInorder(List<AnyType> list, Node<AnyType> root) {
    //     if(root.getLeft() != null) 
	// 	{
    //         printInorder(list, root.getLeft());
    //         System.out.println(list);
    //     }
    //     list.add(root.getData());
    //     if(root.getRight() != null) 
	// 	{
    //         printInorder(list, root.getRight());
    //         System.out.println(list);
    //     }
    // }

    public int depth(AnyType data) {
        if(data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return findDepth(data, root, 1);
    }

    private int findDepth(AnyType data, Node<AnyType> currentNode, int currentNodeDepth) {
        int comparison = data.compareTo(currentNode.getData());
        if(comparison > 0) {
            if(currentNode.getRight() == null) {
                return -1;
            } else {
                return findDepth(data, currentNode.getRight(), currentNodeDepth + 1);
            }
        } else if(comparison < 0) {
            if(currentNode.getLeft() == null) {
                return -1;
            } else {
                return findDepth(data, currentNode.getLeft(), currentNodeDepth + 1);
            }
        } else {
            return currentNodeDepth;
        }
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