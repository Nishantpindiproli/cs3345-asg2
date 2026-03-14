import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements QueueInterface<E> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException, NullPointerException {
        if (e == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node(e);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E data = head.data;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size--;
        return data;
    }

    @Override
    public E dequeue(int index) throws NoSuchElementException {

        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        if (current == head) {
            return dequeue();
        }

        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return current.data;
    }

    @Override
    public void removeAll() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}