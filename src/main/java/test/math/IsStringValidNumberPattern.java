package test.math;


/**
 * Given an input string, determine if it makes a valid number or not.
 */
public class IsStringValidNumberPattern {

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

        State currentState = new StartState();

        for (; i < s.length(); i++) {
            // gets next state of string
            currentState = currentState.getNextState(s.charAt(i));

            // return false if current state is unknown
            if (currentState instanceof UnknownState) {
                return false;
            }
        }

        // return false if current state is Decimal
        if (currentState.getClass().isAssignableFrom(DecimalState.class))
            return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(Number.class.isAssignableFrom(Integer.class));//true
        System.out.println(Integer.class.isAssignableFrom(Number.class));//false
//        Is the number valid 4.325?	Yes
//        Is the number valid 1.1.1?	No
//        Is the number valid 222?	Yes
//        Is the number valid 22.?	No
//        Is the number valid 0.1?	Yes
//        Is the number valid 22.22.?	No
//        Is the number valid 1.?		No
        System.out.println("Is the number valid 4.325?\t" + (isNumberValid("4.325") ? "Yes" : "No"));
        System.out.println("Is the number valid 1.1.1?\t" + (isNumberValid("1.1.1") ? "Yes" : "No"));
        System.out.println("Is the number valid 222?\t" + (isNumberValid("222") ? "Yes" : "No"));
        System.out.println("Is the number valid 22.?\t" + (isNumberValid("22.") ? "Yes" : "No"));
        System.out.println("Is the number valid 0.1?\t" + (isNumberValid("0.1") ? "Yes" : "No"));
        System.out.println("Is the number valid 22.22.?\t" + (isNumberValid("22.22.") ? "Yes" : "No"));
        System.out.println("Is the number valid 1.?\t\t" + (isNumberValid("1.") ? "Yes" : "No"));
    }
}

// Implements the state machine that decides the next state
// based on the current state and the current character of the string
interface State {
    State getNextState(char ch);
}

class StartState implements State {
    @Override
    public State getNextState(char ch) {
        // Handle the possible inputs for the Start and Integer states
        // if the current character is the decimal point, return current state as Decimal
        if (ch == '.') {
            return new DecimalState();
        }
        // if current character is an integer, return current state as Integer
        else if (ch >= '0' && ch <= '9') {
            return new IntegerState();
        }
        // if any other character is encountered, return the Unknown state
        else {
            return new UnknownState();
        }
    }
}

class IntegerState extends StartState {
}

class DecimalState implements State {
    @Override
    public State getNextState(char ch) {
        // and the current character is a digit, the state remains the same
        if (ch >= '0' && ch <= '9') {
            return new AfterDecimalState();
        } else {
            return new UnknownState();
        }
    }
}

class AfterDecimalState extends DecimalState {
}

class UnknownState implements State {
    @Override
    public State getNextState(char ch) {
        return this;
    }
}

