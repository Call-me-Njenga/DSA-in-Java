package Searching;

public class BinarySearch {

    public static int search(int[] list, int target) {

        int left = 0;
        int right = list.length - 1; //This sets the search range.


//This loop continues until the search space disappears.
        while (left <= right) {

            int middle = (left + right) / 2; //finding the middle.

            if (target < list[middle])       // If the target is too smaller— search left half
            {
                right = middle - 1;
            }
            else if (target > list[middle])  // If the target is too bigger — search right half
            {
                left = middle + 1;
            }
            else                             // just right — found it!
            {
                return middle;
            }
        }
        return -1; // target not found
    }

    public static void main(String[] args) {
        int[] numbers = {2, 5, 8, 12, 16, 23, 38, 45};

        System.out.println(search(numbers, 23));
        System.out.println(search(numbers, 10));
    }
}

