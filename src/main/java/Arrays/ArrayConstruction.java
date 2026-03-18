package Arrays;

import java.util.Arrays;

public class ArrayConstruction {

    // -------------------------------------------------------
    // 1. FILTER EVEN — build new array with only even numbers
    //    Two passes needed:
    //    Pass 1 → count how many pass the condition (to size the new array)
    //    Pass 2 → fill the new array with passing elements
    // -------------------------------------------------------
    static int[] filterEven(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) count++;
        }

        int[] result = new int[count];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                result[j] = arr[i];
                j++;
            }
        }
        return result;
    }

    // -------------------------------------------------------
    // 2. FILTER GREATER THAN — build new array with values
    //    above a given threshold
    //    Example: filterGreaterThan(arr, 50) keeps only values > 50
    // -------------------------------------------------------
    static int[] filterGreaterThan(int[] arr, int threshold) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > threshold) count++;
        }

        int[] result = new int[count];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > threshold) {
                result[j] = arr[i];
                j++;
            }
        }
        return result;
    }

    // -------------------------------------------------------
    // 3. DOUBLE VALUES — build new array where every element
    //    is multiplied by 2
    //    Same size as original — every element is transformed
    // -------------------------------------------------------
    static int[] doubleValues(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] * 2;
        }
        return result;
    }

    // -------------------------------------------------------
    // 4. SQUARE VALUES — build new array where every element
    //    is multiplied by itself
    //    arr[i] * arr[i] = arr[i] squared
    // -------------------------------------------------------
    static int[] squareValues(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] * arr[i];
        }
        return result;
    }

    // -------------------------------------------------------
    // 5. REVERSE ARRAY — build new array in reverse order
    //    First element of result = last element of source
    // -------------------------------------------------------
    static int[] reverseArray(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }

    // -------------------------------------------------------
    // 6. MERGE TWO ARRAYS — combine two arrays into one
    //    Copy first array then copy second array after it
    // -------------------------------------------------------
    static int[] mergeTwoArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }

    // -------------------------------------------------------
    // MAIN — demonstrates all constructions
    // -------------------------------------------------------
    public static void main(String[] args) {

        int[] marks = {12, 37, 8, 55, 24, 91, 6, 43, 70, 18};

        System.out.println("Source:              " + Arrays.toString(marks));
        System.out.println("---------------------------------------------");

        System.out.println("Filter even:         " + Arrays.toString(filterEven(marks)));
        System.out.println("Filter > 50:         " + Arrays.toString(filterGreaterThan(marks, 50)));
        System.out.println("Double values:       " + Arrays.toString(doubleValues(marks)));
        System.out.println("Square values:       " + Arrays.toString(squareValues(marks)));
        System.out.println("Reverse:             " + Arrays.toString(reverseArray(marks)));

        int[] extra = {100, 200, 300};
        System.out.println("Merged with [100,200,300]: " + Arrays.toString(mergeTwoArrays(marks, extra)));
    }
}