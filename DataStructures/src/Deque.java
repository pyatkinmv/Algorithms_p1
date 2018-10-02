import java.util.Iterator;
import java.util.NoSuchElementException;

// A double-ended queue or deque (pronounced “deck”)
// is a generalization of a stack and a queue that supports
// adding and removing items from either the front or the back of the data structure.

public class Deque<Item> implements Iterable<Item> {

    private int size;

    private Node<Item> first;
    private Node<Item> last;

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;

    }
    public boolean isEmpty() { return size() == 0; }

    public int size() { return size; }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("Argument of addFirst is null");

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
        if (item == null) throw new java.lang.IllegalArgumentException("Argument of addLast is null");

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

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

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

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Removing elements is forbidden while iterating");
        }
    }
}