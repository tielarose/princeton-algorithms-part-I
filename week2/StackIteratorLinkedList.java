import java.util.Iterator;

public class StackIteratorLinkedList<Item> implements Iterable<Item> {

     private Node first = null;
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
        
     private class Node {
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */}

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
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

}
