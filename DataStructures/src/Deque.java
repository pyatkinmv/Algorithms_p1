//import java.util.Iterator;
//
public class Deque<Item> {}
//
//    private Node first = null;
//
//    private class Node<Item> {
//        Item item;
//        Node next;
//    }
//
//    public Deque()    {}                       // construct an empty deque
//    public boolean isEmpty() {return true;}                 // is the deque empty?
//    public int size()            { return 1;}             // return the number of items on the deque
//    public void addFirst(Item item)         {}  // add the item to the front
//    public void addLast(Item item)   {}        // add the item to the end
//
//    public Item removeFirst()     {return new Item();}           // remove and return the item from the front
//    public Item removeLast()    {return new Item();}             // remove and return the item from the end
//    public Iterator<Item> iterator()    {return new Iterator<Item>() {
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public Item next() {
//            return null;
//        }
//    };
//    }     // return an iterator over items in order from front to end
//
//    public static void main(String[] args){
//        System.out.println("Test_run");
//    }   // unit testing (optional)
//}