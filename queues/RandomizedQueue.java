package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int indOfItemToRemove = StdRandom.uniformInt(size);
        Node curr = first;
        if (indOfItemToRemove == 0) {
            first = first.next;
        }
        else {
            Node prev = new Node();
            prev.next = first;
            int i = 0;
            while (i < indOfItemToRemove) {
                prev = curr;
                curr = curr.next;
                i++;
            }
            prev.next = curr.next;
        }
        return curr.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int indOfItemToRemove = StdRandom.uniformInt(size);
        Node curr = first;
        int i = 0;
        while (i < indOfItemToRemove) {
            curr = curr.next;
            i++;
        }
        return curr.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQ = new RandomizedQueue<Integer>();
        System.out.println(String.format("isEmpty -> true. isEmpty() = %s", randQ.isEmpty())) ;
        System.out.println(String.format("size --> 0. size = %s", randQ.size()));
        System.out.println("Adding 4 items: 0, 1, 2, 3");
        randQ.enqueue(0);
        randQ.enqueue(1);
        randQ.enqueue(2);
        randQ.enqueue(3);
        System.out.println(String.format("random item sample: %s", randQ.sample()));
        System.out.println(String.format("another random item sample: %s", randQ.sample()));
        System.out.println(String.format("remove item %s", randQ.dequeue()));
        Iterator<Integer> iter = randQ.iterator();
        System.out.println("Printing items in a random order:");
        while (iter.hasNext()) {
            System.out.println(String.format("item %s", iter.next()));
        }
    }

}