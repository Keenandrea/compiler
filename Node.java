public class Node<AnyType>
{
    public AnyType data;
    public Node<AnyType> left, right;
    
    public Node(AnyType data)
    {
        this.data = data;
    }
}