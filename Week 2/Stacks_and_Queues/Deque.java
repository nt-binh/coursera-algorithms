/**
 * Deque
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

 public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node{
        private Item item;
        private Node next;
        private Node previous;
        Node(Item item){
            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }

    private class DequeIterator implements Iterator<Item>{
        private Node temp = first;

        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Item next(){
            Item item = temp.item;
            temp = temp.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public Deque(){
        size = 0;
        first = last = null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void addFirst(Item item){
        if (item == null){throw new IllegalArgumentException();}
        Node newNode = new Node(item);
        if (isEmpty()){
            first = last = newNode;
            size++;
            return;
        }
        newNode.next = first;
        first.previous = newNode;
        first = newNode;
        size++;
    }

    public void addLast(Item item){
        if (item == null){throw new IllegalArgumentException();}
        Node newNode = new Node(item);
        if (isEmpty()){
            first = last = newNode;
            size++;
            return;
        }
        newNode.previous = last;
        last.next = newNode;
        last = newNode;
        size++;
    }

    public Item removeFirst(){
        if (isEmpty()){throw new NoSuchElementException();}
        Item item = first.item;
        if (size() == 1){
            first = last = null;
        } else{
            first = first.next;
            first.previous = null;
        }
        size--;
        return item;
    }

    public Item removeLast(){
        if (isEmpty()){throw new NoSuchElementException();}
        Item item = last.item;
        if (size() == 1){
            first = last = null;
        } else{
            last = last.previous;
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(7);
        deque.addLast(4);
        deque.addLast(9);
        deque.addFirst(5);
        deque.addLast(8);
        deque.removeFirst();
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("Size is: " + deque.size());
    }
}