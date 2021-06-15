public class FixedCapacityStackOfStrings {
    
    private String[] stackOfStrings;
    private int size = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        stackOfStrings = new String[capacity];
    }

    public boolean isEmpty() { return size == 0; }

    public void push (String item) {
        stackOfStrings[size++] = item;
    }

    public String pop () {
        return stackOfStrings[--size];
    }

}
