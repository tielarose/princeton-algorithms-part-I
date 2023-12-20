import java.util.Iterator;

public class StackIteratorArray<Item> 
{
    private Item[] s;
    private int N = 0;

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            /* not supported */}

        public Item next() {
            return s[--i];}
    }

    public StackIteratorArray(int capacity)
    { s = (Item[]) new Object[capacity]; }

    public Boolean isEmpty()
    { return N == 0;}

    public void push(Item item)
    {
        s[N++] = item;
    }

    public Item pop()
    {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

}