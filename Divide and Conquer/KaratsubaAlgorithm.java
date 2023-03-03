/**
 * The program requests for two integer operands and calculate the product using the Karatsuba Algorithm
 * Here is how the algorithm works. Suppose the provided two operands are x == 1234, y == 5678
 * then a == 12, b == 34, c == 56, d == 78
 * We now claim the product of x and y, i.e. x * y == ac*10^n + (ad+bc)*10^(n/2)+bd
 * where n is the number of digits of the larger operand
 */

import java.util.Scanner;
import static java.lang.Math.max;

public class KaratsubaAlgorithm {
    public static int karatsubaMultiply(int operand1, int operand2) {
        //recall n is the number of digits of the larger operand
        int n = max(getIntLength(operand1), getIntLength(operand2));
        //base case
        if (n == 1) {
            return operand1 * operand2;
        }

        //work out a, b, c and d
        int[] abcd = decomposeOperands(operand1, operand2);
        int a = abcd[0];
        int b = abcd[1];
        int c = abcd[2];
        int d = abcd[3];

        //recursion
        int ac = karatsubaMultiply(a, c);
        int bd = karatsubaMultiply(b, d);
        int adPlusBc = karatsubaMultiply(a + b, c + d) - ac - bd;

        //recall x * y == ac*10^n + (ad+bc)*10^(n/2)+bd
        return (int) (Math.pow(10, n) * ac + Math.pow(10, n / 2) * adPlusBc + bd);
    }

    public static int getIntLength(int integer) {
        //return the number of digits of the given integer
        int length = 0;
        do {
            length++;
            integer /= 10;
        } while (integer >= 1);
        return length;
    }

    public static int requestOperand(String message) {
        //prompt user with the argument message for a integer
        //keep doing so until the user complies
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    public static int[] decomposeOperands(int operand1, int operand2) {
        //dissect the given multiplicand and multiplier
        //into a, b, c and d, where are then used for further calculations
        //return a, b, c and d and an array of integers
        int numberOfDigits = max(getIntLength(operand1), getIntLength(operand2));
        int midDigit = numberOfDigits / 2;
        int b = 0;
        int d = 0;
        int remainingOp1 = operand1;
        int remainingOp2 = operand2;
        for (int i = 0; i < midDigit; i++) {
            int rightmostDigit1 = remainingOp1 % 10;
            remainingOp1 /= 10;
            b += rightmostDigit1 * Math.pow(10, i);

            int rightmostDigit2 = remainingOp2 % 10;
            remainingOp2 /= 10;
            d += rightmostDigit2 * Math.pow(10, i);
        }
        int a = remainingOp1;
        int c = remainingOp2;

        return new int[]{a, b, c, d};
    }

    public static void main(String[] args) {
        //request two numbers
        int operand1 = requestOperand("Please enter an integer as operand 1:");
        int operand2 = requestOperand("Please enter an integer as operand 2:");
        int product = karatsubaMultiply(operand1, operand2);
        System.out.println("Product: " + product);
    }
}