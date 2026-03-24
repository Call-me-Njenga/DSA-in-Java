package Queue;

public class Towerofhanoi {
    // Move n disks from 'from' rod to 'to' rod using 'aux' as helper
    // The entire algorithm is just 3 steps:
    //   1. Move top n-1 disks from 'from' to 'aux' (out of the way)
    //   2. Move the biggest disk from 'from' to 'to'
    //   3. Move the n-1 disks from 'aux' to 'to' (on top of biggest)
    static void solve(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " → " + to);
            return;
        }
        solve(n - 1, from, aux, to); // step 1 — move n-1 disks to aux
        System.out.println("Move disk " + n + " from " + from + " → " + to); // step 2
        solve(n - 1, aux, to, from); // step 3 — move n-1 disks to destination
    }

    public static void main(String[] args) {

        int disks = 3;
        System.out.println("Tower of Hanoi with " + disks + " disks:");
        System.out.println("Total moves needed: " + ((int)Math.pow(2, disks) - 1));
        System.out.println("-----------------------------");
        solve(disks, 'A', 'C', 'B');

        System.out.println("\nWith 4 disks: " + ((int)Math.pow(2,4)-1) + " moves needed");
        System.out.println("With 10 disks: " + ((int)Math.pow(2,10)-1) + " moves needed");
        System.out.println("With 64 disks: " + ((long)Math.pow(2,64)-1) + " moves (takes ~585 billion years!)");
    }
}
