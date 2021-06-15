import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size;
    private Item[] arr;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int n = size;

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            return arr[--n];
        }

        public void remove() {
            throw new NoSuchElementException();
        }
    }

    private void resize(int length){
        Item[] temp = (Item[]) new Object[length];
        for (int i = 0; i < size; i++){
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void enqueue(Item item){
        if (item == null) {throw new IllegalArgumentException();}
        if (size == arr.length) resize(2 * arr.length);
        arr[size++] = item;
    }

    public Item dequeue(){
        if (isEmpty()){throw new NoSuchElementException();}
        int randomIndex = StdRandom.uniform(size);
        Item item = arr[randomIndex];
        if (randomIndex != size - 1){
            arr[randomIndex] = arr[size - 1];
        }
        arr[size--] = null;
        if (size > 0 && size == arr.length/4) resize(arr.length / 2);
        return item;
    }

    public Item sample(){
        if (isEmpty()){throw new NoSuchElementException();}
        int randomIndex = StdRandom.uniform(size);
        return arr[randomIndex];
    }

    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<Integer>();
        randQueue.enqueue(1);
        randQueue.enqueue(7);
        randQueue.enqueue(4);
        Iterator<Integer> iter = randQueue.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("Remove random elements");
        randQueue.dequeue();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
