

import java.util.HashMap;

public class StringAlgorithms {

    // -------------------------------------------------------
    // 1. REVERSE A STRING — O(n)
    // -------------------------------------------------------
    static String reverse(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;

        while (left < right) {
            char temp   = chars[left];
            chars[left] = chars[right];
            chars[right]= temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // -------------------------------------------------------
    // 2. CHECK PALINDROME — O(n)
    // A palindrome reads the same forwards and backwards
    // e.g. "racecar", "madam", "level"
    // -------------------------------------------------------
    static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]",""); // clean string
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // -------------------------------------------------------
    // 3. CHECK ANAGRAM — O(n)
    // Two strings are anagrams if they contain the same letters
    // e.g. "listen" and "silent", "triangle" and "integral"
    // -------------------------------------------------------
    static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        HashMap<Character, Integer> map = new HashMap<>();

        // Count characters in s1
        for (char c : s1.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        // Subtract characters in s2
        for (char c : s2.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) < 0) return false;
        }

        return true;
    }

    // -------------------------------------------------------
    // 4. LONGEST COMMON PREFIX — O(n × m)
    // Find the longest string that is a prefix of all strings
    // -------------------------------------------------------
    static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println("=== Reverse ===");
        System.out.println(reverse("hello"));
        System.out.println(reverse("java"));

        System.out.println("\n=== Palindrome ===");
        System.out.println("racecar: " + isPalindrome("racecar"));
        System.out.println("hello:   " + isPalindrome("hello"));
        System.out.println("A man a plan a canal Panama: " + isPalindrome("A man a plan a canal Panama"));

        System.out.println("\n=== Anagram ===");
        System.out.println("listen/silent:   " + isAnagram("listen", "silent"));
        System.out.println("hello/world:     " + isAnagram("hello", "world"));
        System.out.println("triangle/integral: " + isAnagram("triangle", "integral"));

        System.out.println("\n=== Longest Common Prefix ===");
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
