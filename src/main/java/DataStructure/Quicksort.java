package DataStructure;

public class Quicksort {

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);   // sort left side
            quickSort(arr, pivot + 1, high);  // sort right side
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // pick last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // place pivot in correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int arr[] = {5, 3, 8, 1, 9, 2};

        System.out.print("Before sorting: ");
        for (int n : arr)
            System.out.print(n + " ");

        quickSort(arr, 0, arr.length - 1);

        System.out.print("\nAfter sorting: ");
        for (int n : arr)
            System.out.print(n + " ");
    }
}
