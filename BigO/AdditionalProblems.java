package BigO;

public class AdditionalProblems {
    int numChars = 26;

    void printSortedStrings(int remain) {
        printSortedStrings(remain, "");
    }

    private void printSortedStrings(int remain, String prefix) {
        if (remain == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < numChars; i++) {
                printSortedStrings(remain - 1, prefix + ithLetter(i));
            }
        }
    }

    private boolean isInOrder(String prefix) {
        for (int i = 1; i < prefix.length(); i++) {
            if(ithLetter(prefix.charAt(i - 1)) > ithLetter(prefix.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private char ithLetter(int i) {
        return (char) (((int) 'a') + i);
    }
}
