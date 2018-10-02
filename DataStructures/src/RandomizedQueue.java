import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// A randomized queue is similar to a stack or queue, except that the item
// removed is chosen uniformly at random from items in the data structure.

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final double INCREASE_THRESHOLD = 0.75;
    private static final double DECREASE_THRESHOLD = 0.25;
    private static final int INITIAL_ARRAY_DIMENSION = 8;

    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new  Object[INITIAL_ARRAY_DIMENSION];
        size = 0;
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() { return size; }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("Argument of enqueue is null");

        arr[size++] = item;

        if (size() >= arr.length * INCREASE_THRESHOLD) resize(2 * arr.length);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Randomized Queue is empty");

        int rand = StdRandom.uniform(size());
        Item item = arr[rand];
        arr[rand] = arr[--size];
        arr[size] = null;
        if (size() > 0 && size() <= DECREASE_THRESHOLD * arr.length) resize(arr.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Randomized Queue is empty");

        int rand = StdRandom.uniform(size());
        return arr[rand];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] order;

        private int i;

        public RandomizedQueueIterator()
        {
            i = 0;
            order = new int[size()];
            for (int j = 0; j < size(); ++j)
                order[j] = j;
            StdRandom.shuffle(order, 0, size());
        }

        @Override
        public boolean hasNext() {
            return i < size();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no next element in Randomized Queue");

            return arr[order[i++]];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Removing elements is forbidden while iterating");
        }
    }
}