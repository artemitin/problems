package test.strings;

/*Given two strings s and t, return true if s is a subsequence of t, or false otherwise.*/
public class Subsequence {
    public static void main(String[] args) {
        Object result = new Subsequence().isSubsequence("abc", "ahbgdc");
        System.out.println(result);
    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        int is = 0;
        int it = 0;
        while (is < sc.length && it < tc.length) {
            if (sc[is] == tc[it]) {
                is++;
            }
            it++;
        }

        return is == sc.length;
    }
}
