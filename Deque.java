import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // first node
    private Node<Item> head;

    // final node
    private Node<Item> tail;

    // num of nodes
    private int size;

    // iterator from LinkedStack.java given in assignment
    private class DequeIterator implements Iterator<Item> {

        // track the current node
        private Node<Item> current = head;

        // check whether the current node exists
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }

    }

    private class Node<Item> {
        // generic data
        private Item data;

        // pointer to next node
        private Node<Item> before;

        // pointer to next node
        private Node<Item> next;

        // constructor to initialize data
        Node(Item input) {
            data = input;
        }

    }

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add an item to the front
    public void addFirst(Item item) {

        // throw exception if no input data
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // new node to hold input data item
        Node<Item> newNode = new Node<Item>(item);

        // in case deque is empty and head is tail
        if (this.isEmpty()) {
            tail = newNode;
        }
        else {
            head.before = newNode;
        }

        // put new node at front
        newNode.next = head;

        // assign new node as first node
        head = newNode;

        // record added node
        size++;

    }

    // add an item to the back
    public void addLast(Item item) {

        // throw exception if no input data
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // new node to hold input data item
        Node<Item> newNode = new Node<Item>(item);

        // in case deque is empty and tail is head
        if (this.isEmpty()) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }

        // put new node at end
        newNode.before = tail;

        // assign new node as last node
        tail = newNode;


        // record added node
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {

        // make sure there is a node to be removed
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // store item to be returned
        Item itemVal = head.data;

        // remove first node
        head = head.next;

        if (size == 1) {
            tail = null;
        }

        else {

            // remove reference of deleted node
            head.before = null;
        }

        // decrement size
        size--;

        // return item
        return itemVal;

    }

    // remove and return the item from the back
    public Item removeLast() {

        // make sure there is a node to be removed
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // store item to be returned
        Item itemVal = tail.data;

        // remove last node
        tail = tail.before;

        if (size == 1) {
            head = null;
        }
        else {
            // remove reference of deleted node
            tail.next = null;
        }

        // decrement size
        size--;

        // return item
        return itemVal;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        // initialize new Deque
        Deque<String> testDeque = new Deque<String>();

        // Check if empty
        System.out.println(testDeque.isEmpty());

        // add items (letters)
        testDeque.addLast("C");
        testDeque.addFirst("B");
        testDeque.addFirst("A");

        // Check if empty
        System.out.println(testDeque.isEmpty());

        // print items
        for (String item : testDeque) {
            System.out.println(item);
        }

        // remove items
        testDeque.removeFirst();
        testDeque.removeLast();
        testDeque.removeLast();

        // Check if empty
        System.out.println(testDeque.isEmpty());

        // print items
        for (String item : testDeque) {
            System.out.println(item);
        }

    }

}
