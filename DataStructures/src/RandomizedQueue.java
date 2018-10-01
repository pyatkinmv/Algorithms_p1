

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

//    A randomized queue is similar to a stack or
//    queue, except that the item removed is chosen
//    uniformly at random from items in the data structure.
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final double INCREASE_THRESHOLD = 0.75;
    private static final double DECREASE_THRESHOLD = 0.25;


    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new  Object[8];
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
        System.out.println("RESIZE, capacity = " + capacity);
    }

    public void enqueue(Item item) {
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
    }          // remove and return a random item

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Randomized Queue is empty");

        int rand = StdRandom.uniform(size());
        return arr[rand];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return arr[i] != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("There is no next element in Randomized Queue");

                return arr[i++];
            }
        };
    }


    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 16; ++i)
            rq.enqueue(i);
        for(Integer i: rq)
            System.out.println(i);

//        for (int i = 0; i < 64; ++i)
//            rq.dequeue();
//
//        for (int i = 0; i < 32; ++i)
//            rq.enqueue(i);
//
//        for (int i = 0; i < 64; ++i)
//            rq.dequeue();
//
//        //for (int i = 0; i < 32; ++i)
//            rq.dequeue();
//
//        for(Integer i: rq)
//            System.out.println(i);

    }   // unit testing (optional)

}