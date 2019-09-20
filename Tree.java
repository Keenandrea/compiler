import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void printFstChar(String fileName, String[] treeDataStr, Integer[] nodeDepthInt) throws IOException
    {
        String indent = " ";
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
        {
            for(int i = 0; i < treeDataStr.length; i++)
            {
                for(int j = 0; j < nodeDepthInt[i]; j++)
                {
                    writer.write(indent);
                }
                writer.write(treeDataStr[i]);

                
                writer.newLine();
            }


            writer.close();
        }
    }

    public void printIndent(String fileName, List<AnyType> treeDataList)
    {
        List<Integer> nodeDepthList = new ArrayList<Integer>();   
        Object[] treeDataArr = treeDataList.toArray();
        String[] treeDataStr = Arrays.copyOf(treeDataArr, treeDataArr.length, String[].class);
        for(AnyType treeData : treeDataList)
        {
            Integer nodeDepth = depth(treeData); 
            nodeDepthList.add(nodeDepth);
        }

        Object[] nodeDepthObj = nodeDepthList.toArray();
		Integer[] nodeDepthIntArr = new Integer[nodeDepthObj.length];
		for (int i = 0; i < nodeDepthObj.length; i++)
        {
			nodeDepthIntArr[i] = (Integer)nodeDepthObj[i];
        }
        
        try     
        {
            printFstChar(fileName, treeDataStr, nodeDepthIntArr);        
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void printHelperPreorder(String fileName)
    { 
        List<AnyType> treeDataList = new ArrayList<AnyType>();
        
        System.out.print("\nWriting traversal data to file " + fileName + "...");
        treeDataList = printPreorder();
        
        System.out.println("Printing preorder traversal to file " + fileName + "...");     
        printIndent(fileName, treeDataList);
    }

    public List<AnyType> printPreorder() {
        ArrayList<AnyType> list = new ArrayList<AnyType>();
        if(root != null) 
        {
            list.add(root.getData());
            printPreorder(list, root);
        }
        return list;
    }

    public void printPreorder(List<AnyType> list, Node<AnyType> root) {
        if(root.getLeft() != null) 
        {
            list.add(root.getLeft().getData());
            printPreorder(list, root.getLeft());
        }
        if(root.getRight() != null) 
		{
            list.add(root.getRight().getData());
            printPreorder(list, root.getRight());
        }
    }

    // public void printHelperPostorder(String fileName) throws IOException
    // { 
    //     List<AnyType> treeDataList = new ArrayList<AnyType>();
    //     Path path = Paths.get(fileName);
    //     try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
    //     {
    //         System.out.print("Writing traversal data to file " + fileName + "...");
    //         treeDataList = printPostorder();
            
    //         Object[] treeDataArr = treeDataList.toArray();
    //         String[] treeDataStr = Arrays.copyOf(treeDataArr, 
    //                                              treeDataArr.length,
    //                                              String[].class);
    //         System.out.print("Printing postorder traversal to file " + fileName + "...");
    //         for(String treeData : treeDataStr)
    //         {
    //             writer.write(treeData);
    //             writer.newLine();
    //         }
            
    //         writer.close();
    //     }
    // }
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
    //     }
    //     if(root.getRight() != null) 
	// 	{
    //         printPostorder(list, root.getRight());
    //     }
    //     list.add(root.getData());
    // }

    // public void printHelperInorder(String fileName) throws IOException
    // { 
    //     List<AnyType> treeDataList = new ArrayList<AnyType>();
    //     Path path = Paths.get(fileName);
    //     try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
    //     {
    //         System.out.print("Writing traversal data to file " + fileName + "...");
    //         treeDataList = printInorder();
            
    //         Object[] treeDataArr = treeDataList.toArray();
    //         String[] treeDataStr = Arrays.copyOf(treeDataArr, 
    //                                              treeDataArr.length,
    //                                              String[].class);

    //         System.out.print("Printing inorder traversal to file " + fileName + "...");
    //         for(String treeData : treeDataStr)
    //         {
    //             writer.write(treeData);
    //             writer.newLine();
    //         }
            
    //         writer.close();
    //     }
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
    //     }
    //     list.add(root.getData());
    //     if(root.getRight() != null) 
	// 	{
    //         printInorder(list, root.getRight());
    //     }
    // }

    public int depth(AnyType data) {
        if(data == null) 
        {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return findDepth(data, root, 1);
    }

    private int findDepth(AnyType data, Node<AnyType> rootrentNode, int rootrentNodeDepth) {
        int comparison = data.compareTo(rootrentNode.getData());
        if(comparison > 0) 
        {
            if(rootrentNode.getRight() == null) 
            {
                return -1;
            } 
            else 
            {
                return findDepth(data, rootrentNode.getRight(), rootrentNodeDepth + 1);
            }
        } 
        else if(comparison < 0) 
        {
            if(rootrentNode.getLeft() == null) 
            {
                return -1;
            } 
            else 
            {
                return findDepth(data, rootrentNode.getLeft(), rootrentNodeDepth + 1);
            }
        } 
        else 
        {
            return rootrentNodeDepth;
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