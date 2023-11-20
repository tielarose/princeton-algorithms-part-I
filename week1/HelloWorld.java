// public class HelloWorld {
//     public static void main(String[] args) {
//         System.out.println("Hello, World");
//     }
// }

import java.util.Arrays;

public class HelloWorld {
    private boolean[][] grid;

    public HelloWorld(int n) {
        grid = new boolean[n][n];
    }

    // public HelloWorld() {
    //     int x = 5;
    //     grid = new boolean[x][x];
    // }

    public static void main(String[] args) {
        System.out.println("This prints first");
        HelloWorld sampleObj = new HelloWorld(5);
        for (boolean[] arr : sampleObj.grid) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("This prints after");
    }
}