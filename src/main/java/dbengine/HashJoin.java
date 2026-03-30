package dbengine;

import java.util.*;

public class HashJoin {

    // Simple row (key + value)
    static class Row {
        int key;
        String value;

        Row(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Result of joining two rows
    static class JoinedRow {
        Row r, s;

        JoinedRow(Row r, Row s) {
            this.r = r;
            this.s = s;
        }
    }

    // Hash Join function
    public static List<JoinedRow> join(List<Row> R, List<Row> S) {

        // Build phase
        Map<Integer, List<Row>> map = new HashMap<>();
        for (Row r : R) {
            map.computeIfAbsent(r.key, k -> new ArrayList<>()).add(r);
        }

        // Probe phase
        List<JoinedRow> result = new ArrayList<>();
        for (Row s : S) {
            if (map.containsKey(s.key)) {
                for (Row r : map.get(s.key)) {
                    result.add(new JoinedRow(r, s));
                }
            }
        }

        return result;
    }

    // Test
    public static void main(String[] args) {

        List<Row> R = Arrays.asList(
                new Row(1, "Alice"),
                new Row(2, "Bob"),
                new Row(2, "Eve")
        );

        List<Row> S = Arrays.asList(
                new Row(1, "Engineering"),
                new Row(2, "Marketing")
        );

        List<JoinedRow> result = join(R, S);

        for (JoinedRow jr : result) {
            System.out.println(jr.r.value + " -> " + jr.s.value);
        }
    }
}