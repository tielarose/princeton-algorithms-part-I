public class GenericStackArray<Item> 
{
    private Item[] s;
    private int N = 0;

    public GenericStackArray(int capacity)
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