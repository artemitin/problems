package test.strings;

/*
Given a string of characters, find and return the longest palindromic substring within the input string.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"aasdf", "aasfgg"};
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String temp = strs[0];
        for (String s : strs) {
            while (!s.startsWith(temp)) {
                temp = temp.substring(0, temp.length() - 1);
            }
            if (temp.length() == 0) {
                return "";
            }
        }
        return temp;
    }
}
