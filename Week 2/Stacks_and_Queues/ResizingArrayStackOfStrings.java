public class ResizingArrayStackOfStrings {
    
    private String[] stackOfStrings;
    private int size;

    public ResizingArrayStackOfStrings() {
        stackOfStrings = new String[1];
        size = 0;
    }

    private void resize(int newSize) {
        String[] copy = new String[newSize];
        for (int i = 0; i < size; i++) {
            copy[i] = stackOfStrings[i];
        }
        stackOfStrings = copy;
    }

    public boolean isEmpty() { return size == 0; }

    public void push(String item) {
        if (size == stackOfStrings.length) {
            resize(2* stackOfStrings.length);
        }
        stackOfStrings[size++] = item;
    }

    public String pop() {
        String item = stackOfStrings[--size];
        stackOfStrings[size] = null;
        if (size > 0 && size == stackOfStrings.length/4) resize(stackOfStrings.length/2);
        return item;
    }

}
