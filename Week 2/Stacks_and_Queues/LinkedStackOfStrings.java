public class LinkedStackOfStrings {         // 16 bytes (object overhead)
    
    private Node first = null;

    private class Node {                    // 8 bytes (inner class extra overhead)
        String item;                        // 8 bytes (reference to String)
        Node next;                          // 8 bytes (reference to object (Node))
                                            /**
                                             * 40 bytes per stack node
                                             */
        Node(String item) {
            this.item = item;
            next = null;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push (String item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            return;
        }
        newNode.next = first;
        first = newNode;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

}
