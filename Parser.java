import java.util.Scanner;

class ProductionRule {
    String left;
    String right;

    ProductionRule(String left, String right) {
        this.left = left;
        this.right = right;
    }
}

public class Parser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input, stack = "", temp;
        String[] token;
        char[] ch;
        int i, j, stackLength, substringLength, stackTop, ruleCount;

        System.out.println("Enter the number of production rules:");
        ruleCount = scanner.nextInt();
        scanner.nextLine(); // consume newline

        ProductionRule[] rules = new ProductionRule[ruleCount];

        System.out.println("Enter the production rules (in the form 'left->right'):");
        for (i = 0; i < ruleCount; i++) {
            temp = scanner.nextLine();
            token = temp.split("->");
            rules[i] = new ProductionRule(token[0], token[1]);
        }

        System.out.println("Enter the input string:");
        input = scanner.nextLine();

        i = 0;
        while (true) {
            if (i < input.length()) {
                stack += input.charAt(i);
                i++;
                System.out.print(stack + "\t");
                System.out.print(input.substring(i) + "\tShift " + stack.charAt(stack.length() - 1) + "\n");
            }

            for (j = 0; j < ruleCount; j++) {
                if (stack.endsWith(rules[j].right)) {
                    stackLength = stack.length();
                    substringLength = rules[j].right.length();
                    stackTop = stackLength - substringLength;
                    stack = stack.substring(0, stackTop) + rules[j].left;
                    System.out.print(stack + "\t");
                    System.out.print(input.substring(i) + "\tReduce " + rules[j].left + "->" + rules[j].right + "\n");
                    j = -1; // Restart the loop
                }
            }

            if (stack.equals(rules[0].left) && i == input.length()) {
                System.out.println("Accepted");
                break;
            }

            if (i == input.length()) {
                System.out.println("Not Accepted");
                break;
            }
        }

        scanner.close();
    }
}
