import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    private int size = 0;

    private Node<Item> first = null;

    public boolean isEmpty() { return first == null; }                 // is the deque empty?

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        ++size;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");

        Item item =  first.item;
        first = first.next;
        --size;
        return item;
    }

    public Item peek() {
        return first.item;
    }

    public int size() {
        return size;
    }

    private class Node<Item> {
        Item item;

        Node<Item> next;

    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements  Iterator<Item> {
        Node<Item> current = first;

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

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i += 2)
            queue.enqueue(i);

        queue.enqueue(null);
        queue.enqueue(10);
        for(Integer i: queue)
            System.out.println(i);



    }
}
