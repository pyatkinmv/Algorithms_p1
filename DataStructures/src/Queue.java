import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private int size = 0;

    private Node<Item> head = null;
    private Node<Item> tail = null;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public boolean isEmpty() { return head == null; }                 // is the deque empty?

    public void enqueue(Item item) {
        Node<Item> oldTail = tail;
        tail = new Node<>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) head = tail;
        else oldTail.next = tail;
        ++size;
    }

    public Item dequeue() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");

        Item item =  head.item;
        head = head.next;
        if (isEmpty()) tail = null;
        --size;
        return item;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements  Iterator<Item> {
        Node<Item> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("There is no next element in stack");

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
