import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        // store parameter for number of terms to permute
        int k = StdIn.readInt();

        // create RandomizedQueue to store input strings
        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();

        // add input strings to RandomizedQueue
        while (!StdIn.isEmpty()) {
            String term = StdIn.readString();
            rQueue.enqueue(term);
        }

        // pick, remove, print items at random (to prevent repeats)
        for (int i = 0; i < k; i++) {
            StdOut.println(rQueue.dequeue());
        }

    }
}
