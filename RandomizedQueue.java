import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // first node
    private Node<Item> head;

    // final node
    private Node<Item> tail;

    // num of nodes
    private int size;


    // iterator from LinkedStack.java given in assignment
    private class RQueueIterator implements Iterator<Item> {

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

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {

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

    // remove and return a random item
    public Item dequeue() {

        // throw exception if queue is empty
        if (size == 0) {
            throw new NoSuchElementException();
        }

        // randomly generate index of node to be removed
        int randomNum = StdRandom.uniformInt(size);

        // declare node to reference node to be removed
        Node<Item> nodeToRemove;
        Item rItem;

        // if head is to be removed
        if (randomNum == 0) {
            nodeToRemove = head;
            rItem = nodeToRemove.data;
            if (nodeToRemove.next != null) {
                nodeToRemove.next.before = null;
            }
            head = head.next;

        }

        else if (randomNum == size - 1) {
            nodeToRemove = tail;
            rItem = nodeToRemove.data;
            if (nodeToRemove.before != null) {
                nodeToRemove.before.next = null;
            }
            tail = tail.before;
        }

        else {

            // initialize counter to track number of iterations
            int i = 0;

            // initialize node to reference node to be removed
            nodeToRemove = head;

            // before desired index has been reached
            while (i < randomNum) {
                // increment nodeToRemove by 1 node
                nodeToRemove = nodeToRemove.next;
                // increment counter
                i++;
            }

            // save item to be returned
            rItem = nodeToRemove.data;

            // set subsequent node to reference previous node
            nodeToRemove.next.before = nodeToRemove.before;

            // set previous node to reference subsequent node
            nodeToRemove.before.next = nodeToRemove.next;

        }

        size--;

        return rItem;

    }

    // return a random item (but do not remove it)
    public Item sample() {

        // throw exception if queue is empty
        if (size == 0) {
            throw new NoSuchElementException();
        }

        // randomly generate index of node to be removed
        int randomNum = StdRandom.uniformInt(size);

        // declare node to reference node to be removed
        Node<Item> nodeToSample;

        // in case head is chosen randomly
        if (randomNum == 0) {
            nodeToSample = head;
        }

        // in case tail is chosen randomly
        else if (randomNum == size - 1) {
            nodeToSample = tail;
        }

        else {
            // declare counter
            int i = 0;

            // start at head
            nodeToSample = head;

            while (i < randomNum) {
                // increment sample node tracker by one node
                nodeToSample = nodeToSample.next;
                // increment counter by 1
                i++;
            }


        }

        return nodeToSample.data;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();

        // add "A," "B," "C" to queue
        rQueue.enqueue("A");
        rQueue.enqueue("B");
        rQueue.enqueue("C");

        // print items
        for (String item : rQueue) {
            System.out.println(item);
        }
        System.out.println();


        // remove an item at random
        rQueue.dequeue();

        // print items
        for (String item : rQueue) {
            System.out.println(item);
        }
        System.out.println();

        // sample, print item
        System.out.println(rQueue.sample());

        // remove remaining items
        rQueue.dequeue();
        rQueue.dequeue();

        // ensure all items are removed
        for (String item : rQueue) {
            System.out.println(item);
        }
        System.out.println();


    }

}
