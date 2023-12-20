public class QueueOfStrings
{
    private Node first, last;
    // private int size = 0;
    
    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }
    public void enqueue(String item){
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else 
            oldlast.next = last;
        // size++;
    }

    public String dequeue(){
        String item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        // size--;
        return item;
    }
}