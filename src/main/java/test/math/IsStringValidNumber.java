package test.math;


/**
 * Given an input string, determine if it makes a valid number or not.
 */
public class IsStringValidNumber {

    enum State {
        START, INTEGER, DECIMAL, UNKNOWN, AFTERDECIMAL
    }

    // Implements the state machine that decides the next state
    // based on the current state and the current character of the string
    static State getNextState(State currentState, char ch) {
        switch (currentState) {
            case START:
            case INTEGER:
                // Handle the possible inputs for the Start and Integer states
                // if the current character is the decimal point, return current state as Decimal
                if (ch == '.') {
                    return State.DECIMAL;
                }
                // if current character is an integer, return current state as Integer
                else if (ch >= '0' && ch <= '9') {
                    return State.INTEGER;
                }
                // if any other character is encountered, return the Unknown state
                else {
                    return State.UNKNOWN;
                }

            case DECIMAL:
            case AFTERDECIMAL:
                // and the current character is a digit, the state remains the same
                if (ch >= '0' && ch <= '9') {
                    return State.AFTERDECIMAL;
                } else {
                    return State.UNKNOWN;
                }

            default:
                // if decimal or integer not encountered at all,
                return State.UNKNOWN;
        }
    }

    static boolean isNumberValid(String s) {
        // return true if length of string is 0
        if (s.isEmpty()) {
            return true;
        }

        int i = 0;

        // if start of string is '+' or '-' ,
        // string will be started after that
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }

        State currentState = State.START;

        for (; i < s.length(); i++) {
            // gets next state of string
            currentState = getNextState(currentState, s.charAt(i));

            // return false if current state is unknown
            if (currentState == State.UNKNOWN) {
                return false;
            }
        }

        // return false if current state is Decimal
        if (currentState == State.DECIMAL)
            return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is the number valid 4.325?\t" + (isNumberValid("4.325") ? "Yes" : "No"));
        System.out.println("Is the number valid 1.1.1?\t" + (isNumberValid("1.1.1") ? "Yes" : "No"));
        System.out.println("Is the number valid 222?\t" + (isNumberValid("222") ? "Yes" : "No"));
        System.out.println("Is the number valid 22.?\t" + (isNumberValid("22.") ? "Yes" : "No"));
        System.out.println("Is the number valid 0.1?\t" + (isNumberValid("0.1") ? "Yes" : "No"));
        System.out.println("Is the number valid 22.22.?\t" + (isNumberValid("22.22.") ? "Yes" : "No"));
        System.out.println("Is the number valid 1.?\t\t" + (isNumberValid("1.") ? "Yes" : "No"));
    }
}
