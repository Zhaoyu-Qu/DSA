import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This algorithm counts the total number of inversions in a given array with a run time of O(nlog(n))
 * The idea is to do a merge sort on the array and keep track of the inversions found
 * 
 * What is an inversion?
 * Suppose if the given array is [1, 2, 3, 4], we say there is no inversion because all numbers are arranged in ascending order
 * Now if the array is [1, 4, 2, 3], then there are two inversions, namely [4, 2] and [4, 3]
 * Similarly, array [4, 3, 2, 1] has 6 inversions, i.e. [4, 3], [4, 2], [4, 1], [3, 2], [3, 1], [2, 1]
 * 
 * Instinctively, one may be tempted to use a nested loop which results in a big O of n squared. By contrast, the algorithm
 * implemented in this class adopts a divide and conquer approach and achieves a run time of nlog(n)
 */

 public class CountingInversions {
    private static int numberOfInversions = 0;
    private static ArrayList<int[]> inversions = new ArrayList<>();
    public static void main(String[] args) {
        //obtain an integer array from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a space delimited array of integers and end with a new line.");
        System.out.println("For example, \"1 2 3 4\":");
        String[] userInput = scanner.nextLine().split(" ");
        int[] array = new int[userInput.length];
        try {
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(userInput[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("You must only enter integers!");
            System.exit(1);
        }

        mergeSortAndCountInversions(array);

        //output results to the console
        if (numberOfInversions == 0) {
            System.out.println("No inversions found!");
        } else {
            System.out.printf("%d inversion(s) found as follows:\n", numberOfInversions);
            for (int[] inversion : inversions) {
                System.out.printf("[%d, %d]\n", inversion[0], inversion[1]);
            }
        }
    }

    public static int[] mergeSortAndCountInversions(int[] array) {
        //base case
        if (array.length == 1) {
            return array;
        }

        //recursion
        int[] sortedLeftArray = mergeSortAndCountInversions(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] sortedRightArray = mergeSortAndCountInversions(Arrays.copyOfRange(array, array.length / 2, array.length));

        //merge in ascending order
        //modify the running total of numberOfInversions and update the inversion record where applicable
        int[] mergedArray = new int[sortedLeftArray.length + sortedRightArray.length];
        for (int i = 0, j = 0; i + j < mergedArray.length;) {
            if (i == sortedLeftArray.length || (j != sortedRightArray.length && sortedLeftArray[i] > sortedRightArray[j])) {
                mergedArray[i + j] = sortedRightArray[j];
                numberOfInversions += sortedLeftArray.length - i;
                //The step below updates the record of inversions and is not considered a part of the algorithm
                //Making this comment in case you wonder if it affects the runtime
                for (int k = i; k < sortedLeftArray.length; k++) {
                    inversions.add(new int[]{sortedLeftArray[k], sortedRightArray[j]});
                }
                j++;
            } else if (j == sortedRightArray.length || sortedLeftArray[i] <= sortedRightArray[j]) {
                mergedArray[i + j] = sortedLeftArray[i];
                i++;
            }
        }
        return mergedArray;
    }
 }