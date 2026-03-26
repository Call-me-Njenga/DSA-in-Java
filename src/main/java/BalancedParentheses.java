import java.util.Stack;

public class BalancedParentheses {

    // Check if a string of brackets is balanced
    // Push opening brackets, pop and match closing brackets
    static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            // Opening bracket — push onto stack
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);

                // Closing bracket — pop and check if it matches
            } else if (c == ')' || c == ']' || c == '}') {

                // Stack empty means no matching opener — invalid
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                // Check if popped opener matches this closer
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }

        // If stack is empty all brackets matched — valid
        // If stack still has items some openers were never closed — invalid
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] tests = {"({}[])", "([)]", "{{}",  "(())", "{{{}}}",  "((("};

        for (String test : tests) {
            System.out.println("\"" + test + "\" → " + (isBalanced(test) ? "VALID ✓" : "INVALID ✗"));
        }
    }
}
