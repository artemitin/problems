package test.strings;

/*
Given a sentence (an array of characters),
reverse the order of its words without affecting the order of letters within a given word.
All operations must be done in place.
 */
public class ReverseWords {

    public static void main(String[] args) {
        String result = reverseWords("Hello test world.".toCharArray());
        System.out.println(result);
    }

    public static String reverseWords(char[] sentence) {
        reverse(sentence, 0, sentence.length);

        System.out.println(sentence);

        int wordLength = 0;
        int wordStart = 0;
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i] == ' ') {
                if (wordLength > 0){
                    reverse(sentence, wordStart, wordLength);
                }
                wordStart = 0;
                wordLength = 0;
                continue;
            }
            if (wordLength == 0) {
                wordStart = i;
            }
            wordLength++;
        }

        if (wordLength > 0){
            reverse(sentence, wordStart, wordLength);
        }

        return String.valueOf(sentence);
    }

    public static void reverse(char[] sentence, int start, int length) {
        int end = start + length - 1;
        while (start < end) {
            char temp = sentence[end];
            sentence[end] = sentence[start];
            sentence[start] = temp;
            start++;
            end--;
        }
    }
}
