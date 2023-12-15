package week2;

public class GenericStack<Item>
{
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }
    
    public Boolean isEmpty()
    {
        return first == null;
    }
    
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
}