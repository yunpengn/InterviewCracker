package BigO;

/**
 * Contains source code to all examples in the chapter for Big O notation.
 *
 * @author yunpengn
 */
public class Examples {
    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;

        new Examples().foo(arr);
    }

    /**
     * Example 1: O(n) time
     */
    void foo(int[] arr) {
        int sum = 0;
        int product = 1;

        for (int i : arr) {
            sum += arr[i];
        }

        for (int i : arr) {
            product *= i;
        }

        System.out.println(String.format("The sum of all elements in the array is %1$d", sum));
        System.out.println(String.format("The product of all elements in the array is %1$d", product));
    }

    /**
     * Example 2: O(n^2) time
     */
    void printPairs(int[] arr) {
        for (int i : arr) {
            for (int j : arr) {
                System.out.println(i + ", " + j);
            }
        }
    }
}
