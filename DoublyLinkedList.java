import java.util.Iterator;

public class DoublyLinkedList<T> implements List<T>, Iterable<T> {

    private Node head, tail;
    private int numberOfElements;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        numberOfElements = 0;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
        numberOfElements++;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        numberOfElements++;
    }

    @Override
    public T get(int position) {
        if (position < 0 || position >= numberOfElements) {
            return null;
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public void printBackwards() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.previous;
        }
        System.out.println();
    }

    @Override
    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }

        Node current = head;

        while (current != null) {
            if (current.data.equals(item)) {

                // remove head
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.previous = null;
                    } else {
                        tail = null; // list became empty
                    }
                }
                // remove tail
                else if (current == tail) {
                    tail = tail.previous;
                    tail.next = null;
                }
                // remove middle
                else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }

                numberOfElements--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public int getLength() {
        return numberOfElements;
    }

    /* =======================
       Inner Node class
       ======================= */
    private class Node {
        private T data;
        private Node next, previous;

        private Node(T data) {
            this(data, null, null);
        }

        private Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.previous = prev;
        }
    }

    /* =======================
       Iterator implementation
       ======================= */
    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<T> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}