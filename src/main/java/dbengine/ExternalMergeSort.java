package dbengine;

import java.util.*;

public class ExternalMergeSort {

    // Phase 1: Split data into chunks and sort each
    public static List<List<Integer>> createRuns(int[] data, int size) {
        List<List<Integer>> runs = new ArrayList<>();

        for (int i = 0; i < data.length; i += size) {
            int end = Math.min(i + size, data.length);

            List<Integer> run = new ArrayList<>();
            for (int j = i; j < end; j++) {
                run.add(data[j]);
            }

            Collections.sort(run); // sort chunk
            runs.add(run);
        }

        return runs;
    }

    // Phase 2: Merge all sorted runs
    public static List<Integer> mergeRuns(List<List<Integer>> runs) {

        PriorityQueue<int[]> heap =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // add first element of each run
        for (int i = 0; i < runs.size(); i++) {
            if (!runs.get(i).isEmpty()) {
                heap.add(new int[]{runs.get(i).get(0), i, 0});
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int value = top[0];
            int runIndex = top[1];
            int pos = top[2];

            result.add(value);

            // move to next element in same run
            if (pos + 1 < runs.get(runIndex).size()) {
                int next = runs.get(runIndex).get(pos + 1);
                heap.add(new int[]{next, runIndex, pos + 1});
            }
        }

        return result;
    }

    // Full sort
    public static List<Integer> sort(int[] data, int bufferSize) {
        List<List<Integer>> runs = createRuns(data, bufferSize);
        return mergeRuns(runs);
    }

    // Test
    public static void main(String[] args) {
        int[] data = {72, 45, 91, 38, 60, 55, 83, 29};
        int bufferSize = 3;

        List<Integer> sorted = sort(data, bufferSize);
        System.out.println(sorted);
    }
}
