import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

 /**
   * Reads a sequence of words from standard input and prints one of those words uniformly at random
   *
   * @param args sequence of words
   */

public class RandomWord {
    public static void main(String args[]) {
        String championWord = "";
        double i = 1.0;

        while (!StdIn.isEmpty()) {
            String newWord = StdIn.readString();
            boolean replaceWord = StdRandom.bernoulli(1 / i);
            if (replaceWord) {
                championWord = newWord;
            }

            i += 1.0;
        }

        StdOut.println(championWord);

    }
}