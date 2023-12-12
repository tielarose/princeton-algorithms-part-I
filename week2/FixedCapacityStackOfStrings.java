package week2;

public class FixedCapacityStackOfStrings 
{
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity)
    { s = new String[capacity]; }

    public Boolean isEmpty()
    { return N == 0;}

    public void push(String item)
    {
        s[N++] = item;
    }

    public String pop()
    {
        String item = s[--N];
        s[N] = null;
        return item;
    }

}