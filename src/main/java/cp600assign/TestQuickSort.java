package cp600assign;

import java.util.Arrays;

public class TestQuickSort {
    private static int partition(int[] arr, int low, int high) {
        // Choosing the pivot element
        int pivot = arr[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Method to implement quicksort
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partitioning index
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Main method to test the sorting algorithm
    public static void main(String[] args) {
        int[] arr = { 10, 7, 8, 9, 1, 5, 11, 20,100, 99, 30, 85, 77, 15, 19, 24 };
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(arr));
    }
}
