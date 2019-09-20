public class Node<AnyType extends Comparable<AnyType>>
{
    public AnyType data;
    public Node<AnyType> left; 
    public Node<AnyType> right;

    public Node(AnyType d) 
    {
        data = d;
    }
    public AnyType getData() 
    {
        return data;
    }
    public void setData(AnyType data) 
    {
        this.data = data;
    }
    public Node<AnyType> getLeft() 
    {
        return left;
    }
    public void setLeft(Node<AnyType> left) 
    {
        this.left = left;
    }
    public Node<AnyType> getRight() 
    {
        return right;
    }
    public void setRight(Node<AnyType> right) 
    {
        this.right = right;
    }
}