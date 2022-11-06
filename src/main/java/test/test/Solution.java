package test.test;

import java.util.HashSet;
import java.util.Set;

class Solution {

    public static void main(String[] args) throws Exception {
        System.out.println(lengthOfLongestSubstring("aaa"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        }
        Set<Character> occured = new HashSet<>();
        int maxCount = 0;
//abcaaadefghi
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            int count = 0;
            while (j < s.length()) {
                char c = s.charAt(j);
                if (occured.contains(c)) {
                    occured.clear();
                    break;
                } else {
                    count++;
                    occured.add(c);
                }
                j++;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }


        return maxCount;
    }
}