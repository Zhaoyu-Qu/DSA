import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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
 * implemented in this class adopted a divide and conquer approach and achieves a run time of nlog(n)
 */

 public class CountingInversions {
    private static int numberOfInversions = 0;
    private static ArrayList<int[]> inversions = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an array of integers and end with any non-integer character:");
        ArrayList<Integer> array = new ArrayList<>();
        try {
            while (true) {
                array.add(scanner.nextInt());
            }
        } catch (InputMismatchException e) {
            ;
        }

        int[] intArray = array.stream().mapToInt(Integer::intValue).toArray();
        countInversions(intArray);
        System.out.println(numberOfInversions);
    }

    public static int[] countInversions(int[] array) {
        //base case
        if (array.length == 2 && array[0] > array[1]) {
            numberOfInversions++;
            inversions.add(new int[]{array[0], array[1]});
            return new int[] {array[1], array[0]};
        } else if (array.length <= 2) {
            return array;
        }

        //recursion
        int[] sortedLeftArray = countInversions(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] sortedRightArray = countInversions(Arrays.copyOfRange(array, array.length / 2, array.length));

        //merge in ascending order and modify the running total of numberOfInversions where applicable
        int[] mergedArray = new int[sortedLeftArray.length + sortedRightArray.length];
        for (int i = 0, j = 0; i + j < mergedArray.length;) {
            if (j != sortedRightArray.length && (i == sortedLeftArray.length || sortedLeftArray[i] > sortedRightArray[j])) {
                mergedArray[i + j] = sortedRightArray[j];
                j++;
                numberOfInversions += sortedLeftArray.length - i;
                // for (int a = 0; a )
            } else if (j == sortedRightArray.length || sortedLeftArray[i] <= sortedRightArray[j]) {
                mergedArray[i + j] = sortedLeftArray[i];
                i++;
            }
        }
        return mergedArray;
    }
 }