import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        queue = (Item[]) new Object[1];

    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = n;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; j++) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return queue[order[--i]];
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == queue.length) {
            resize(2 * queue.length);
        }
        queue[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randInd = StdRandom.uniformInt(n);
        Item item = queue[randInd];
        queue[randInd] = queue[n - 1];
        queue[n - 1] = null;
        n--;
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randInd = StdRandom.uniformInt(n);
        return queue[randInd];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQ = new RandomizedQueue<Integer>();
        System.out.println(String.format("isEmpty -> true. isEmpty() = %s", randQ.isEmpty()));
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