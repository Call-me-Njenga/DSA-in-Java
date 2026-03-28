package Arrays;

public class MatrixRotation {

    // Rotate matrix 90° clockwise IN PLACE — O(n²) time, O(1) space
    // Step 1: Transpose — swap matrix[i][j] with matrix[j][i]
    // Step 2: Reverse each row
    static void rotate90(int[][] matrix) {
        int n = matrix.length;

        // Step 1 — Transpose (flip along the main diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp       = matrix[i][j];
                matrix[i][j]   = matrix[j][i];
                matrix[j][i]   = temp;
            }
        }

        // Step 2 — Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp          = matrix[i][left];
                matrix[i][left]   = matrix[i][right];
                matrix[i][right]  = temp;
                left++;
                right--;
            }
        }
    }

    static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int v : row) System.out.printf("%3d", v);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Original:");
        print(matrix);

        rotate90(matrix);

        System.out.println("\nRotated 90° clockwise:");
        print(matrix);

        // Expected:
        // 7  4  1
        // 8  5  2
        // 9  6  3
    }
}
