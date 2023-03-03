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
    public static long karatsubaMultiply(long operand1, long operand2) {
        //recall n is the number of digits of the larger operand
        int n = max(getIntLength(operand1), getIntLength(operand2));
        //base case
        if (n == 1) {
            return operand1 * operand2;
        }

        //work out a, b, c and d
        long[] abcd = decomposeOperands(operand1, operand2);
        long a = abcd[0];
        long b = abcd[1];
        long c = abcd[2];
        long d = abcd[3];

        //recursion
        long ac = karatsubaMultiply(a, c);
        long bd = karatsubaMultiply(b, d);
        long adPlusBc = karatsubaMultiply(a + b, c + d) - ac - bd;

        //recall x * y == ac*10^n + (ad+bc)*10^(n/2)+bd
        return (long) (Math.pow(10, n) * ac + Math.pow(10, n / 2) * adPlusBc + bd);
    }

    public static int getIntLength(long integer) {
        //return the number of digits of the given integer
        int length = 0;
        do {
            length++;
            integer /= 10;
        } while (integer >= 1);
        return length;
    }

    public static long requestOperand(String message) {
        //prompt user with the argument message for a integer
        //keep doing so until the user complies
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(e);
                continue;
            }
        }
    }

    public static long[] decomposeOperands(long operand1, long operand2) {
        //dissect the given multiplicand and multiplier
        //into a, b, c and d, which are then used for further calculations
        //return a, b, c and d and an array of integers
        int numberOfDigits = max(getIntLength(operand1), getIntLength(operand2));
        int midDigit = numberOfDigits / 2;
        long b = 0;
        long d = 0;
        long remainingOp1 = operand1;
        long remainingOp2 = operand2;
        for (int i = 0; i < midDigit; i++) {
            long rightmostDigit1 = remainingOp1 % 10;
            remainingOp1 /= 10;
            b += rightmostDigit1 * Math.pow(10, i);

            long rightmostDigit2 = remainingOp2 % 10;
            remainingOp2 /= 10;
            d += rightmostDigit2 * Math.pow(10, i);
        }
        long a = remainingOp1;
        long c = remainingOp2;

        return new long[]{a, b, c, d};
    }

    public static void main(String[] args) {
        long multiplicand = requestOperand("Please enter an integer as the multiplicand:");
        long multiplier = requestOperand("Please enter an integer as multiplier:");
        long product = karatsubaMultiply(multiplicand, multiplier);
        System.out.println("Product: " + product);
    }
}