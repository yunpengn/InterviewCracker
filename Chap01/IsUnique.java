package Chap01;

import java.util.Arrays;
import java.util.Collections;

public class IsUnique {
    public static void main(String[] args) {
        IsUnique checker = new IsUnique();
        System.out.println(checker.ifAllAscii("Java!"));
        System.out.println(checker.ifAllAscii("Ruby!"));

        System.out.println(checker.sortThem("Java!"));
        System.out.println(checker.sortThem("Ruby!"));
    }

	public boolean ifAllAscii(String str) {
		boolean[] flags = new boolean[128];
        char[] characters = str.toCharArray();

        for (char c : characters) {
            int charCode = (int) c;

            if (flags[charCode]) {
                return false;
            }

            flags[charCode] = true;
        }

        return true;
	}

	public boolean sortThem(String str) {
        char[] characters = str.toCharArray();
        Arrays.sort(characters);

        for (int i = 0; i < str.length() - 1; i++) {
            if (characters[i] == characters[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
