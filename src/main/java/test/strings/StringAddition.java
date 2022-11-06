package test.strings;

/*
Given a sentence (an array of characters),
reverse the order of its words without affecting the order of letters within a given word.
All operations must be done in place.
 */
public class StringAddition {

    public static void main(String[] args) {
        String result = addStrings("22", "188");
        System.out.println(result);
    }

    public static String addStrings(String num1, String num2) {
        // Initialize an empty list
        StringBuilder res = new StringBuilder();

        // Initialize carry to 0
        int carry = 0;

        // Initialize the pointers to the ends of each operand.
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;

        while (p1 >= 0 || p2 >= 0) {
            int x1 = 0;
            int x2 = 0;

            if (p1 >= 0) {
                x1 = num1.charAt(p1--) - '0';
            }
            if (p2 >= 0) {
                x2 = num2.charAt(p2--) - '0';
            }

            int sum = x1 + x2 + carry;
            carry = sum / 10;
            int value = sum % 10;

            res.append(value);
        }

        if (carry != 0)
            res.append(carry);

        return res.reverse().toString();
    }
}
