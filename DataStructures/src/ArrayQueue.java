import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {

    private static final double INCREASE_THRESHOLD = 0.75;
    private static final double DECREASE_THRESHOLD = 0.25;

    private Item[] arr;
    private int head;
    private int tail;

    public ArrayQueue() {
        arr = (Item[]) new  Object[8];
        head = 0; // номер вхождения 1-го элемента очереди в массив
        tail = 0; // номер вхождения последнего элемента очереди в массив
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() {
        if (head >= tail) {
            if (arr[head] == null) return 0;
            else return 1;
        }
        return tail - head + 1;
    } // число элементов в очереди

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = head; i <= tail; ++i)
            copy[i - head] = arr[i];
        arr = copy;
        System.out.println("RESIZE, capacity = " + capacity);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Randomized Queue is empty");

        Item item = arr[tail];
        arr[tail--] = null;
        if (size() > 0 && size() <= DECREASE_THRESHOLD * arr.length) resize(arr.length/2);
        return item;
    }

    public void enqueue(Item item) {
        if (isEmpty()) arr[tail] = item;
        else {
            if (size() >= arr.length * INCREASE_THRESHOLD || tail >= arr.length - 1) resize(2 * size());
            arr[++tail] = item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = head;

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


}