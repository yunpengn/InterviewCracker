package BigO;

/**
 * Contains source code to all examples in the chapter for Big O notation.
 *
 * @author yunpengn
 */
public class Examples {
    private static int count = 0;

    public static void main(String[] args) {
        Examples example = new Examples();
        example.permutation("1234");
        System.out.println(String.format("The method has been evaluated for %1$d times.", count));
        System.out.println(example.allFibonacciBetter(5));
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

    /**
     * Example 3: O(n^2) time
     */
    void printUnorderedPairs(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println(i + ", " + j);
            }
        }
    }

    /**
     * Example 4: O(m * n) time
     */
    void printUnorderedDoublePairs(int[] arrA, int[] arrB) {
        for (int i = 0; i < arrA.length; i++) {
            for (int j = 0; j < arrB.length; j++) {
                System.out.println(i + ", " + j);
            }
        }
    }

    /**
     * Example 5: O(m * n) time
     */
    void printUnorderedDoublePairsManyTimes(int[] arrA, int[] arrB) {
        for (int i = 0; i < arrA.length; i++) {
            for (int j = 0; j < arrB.length; j++) {
                for (int k = 0; k < 1000; k++) {
                    System.out.println(i + ", " + j + " for the " + k + "th times.");
                }
            }
        }
    }

    /**
     * Example 6: O(n) time
     */
    void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int other = arr.length - i - 1;
            int temp = arr[i];
            arr[i] = arr[other];
            arr[other] = temp;
        }
    }

    /**
     * Example 9: O(n) time if n is the number of nodes in the BST.
     */
    int sum(Node node) {
        return node == null ? 0 : sum(node.left) + sum(node.right);
    }

    /**
     * Example 10: O(sqrt(n)) time
     */
    boolean isPrime(int n) {
        for (int x = 2; x * x <= n; x++) {
            if (n % x == 0 ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Example 11: O(n) time
     */
    int factorial(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Example 12: O(n^2 * n!) time
     */
    void permutation(String str) {
        permutation(str, "");
    }

    private void permutation(String str, String prefix) {
        count++;
        if (str.length() == 0) {
        } else {
            for(int i = 0; i < str.length(); i++) {
                String remove = str.substring(0, i) + str.substring(i + 1);
                permutation(remove, prefix + str.charAt(i));
            }
        }
    }

    /**
     * Example 13: O(2^n) time
     */
    int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * Example 14: O(2^n) time
     */
    int allFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else {
            return fibonacci(n) + allFibonacci(n - 1);
        }
    }

    /**
     * Example 15: O(n) time
     */
    int allFibonacciBetter(int n) {
        // Notice: Java will automatically initialize all elements of an integer array to zeros.
        int[] memo = new int[n + 1];
        int result = 0;

        for (int i = 0; i <= n; i++) {
            result += betterFibonacci(i, memo);
        }

        return result;
    }

    private int betterFibonacci(int n, int[] memo) {
        if (n <= 1) {
            return n;
        } else if (memo[n] > 0) {
            return memo[n];
        }

        memo[n] = betterFibonacci(n - 1, memo) + betterFibonacci(n - 2, memo);
        return memo[n];
    }

    /**
     * Example 15: O(log(n)) time
     */
    int powersOfTwo(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            int previous = powersOfTwo(n / 2);
            int current = previous * 2;
            System.out.println(current);
            return current;
        }
    }
}
