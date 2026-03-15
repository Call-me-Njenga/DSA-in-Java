package DataStructure;

public class BinarySearch {

    public static int search(int[] list, int target) {

        int left = 0;
        int right = list.length - 1;

        while (left <= right) {

            int middle = (left + right) / 2;

            if (target < list[middle])       // target is too LOW — search left half
            {
                right = middle - 1;
            }
            else if (target > list[middle])  // target is too HIGH — search right half
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

