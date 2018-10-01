// A double-ended queue or deque (pronounced “deck”)
// is a generalization of a stack and a queue that supports
// adding and removing items from either the front or the back of the data structure.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 0;

    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;
    }

    public Deque() {
        first = null;
        last = null;
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() { return size; }

    public void addFirst(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = null;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.previous = first;
            first.next = oldFirst;
        }
        ++size;
    }

    public void addLast(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.previous = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        ++size;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = first.item;
        first = first.next;
        --size;
        if (isEmpty()) last = null;
        else first.previous = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = last.item;
        last = last.previous;
        --size;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("There is no next element in queue");

                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10; ++i)
            deque.addLast(i);

        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; ++i)
            queue.enqueue(i);

        for(Integer i: deque)
            System.out.println(i);

        System.out.println("\n ____________________ \n");

        for(Integer i: queue)
            System.out.println(i);


    }   // unit testing (optional)
}