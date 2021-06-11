import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randString = new RandomizedQueue<String>();
        int k = Integer.valueOf(args[0]);
        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            randString.enqueue(str);
        }
        while (k > 0){
            StdOut.println(randString.dequeue());
            k--;
        }
    }
}