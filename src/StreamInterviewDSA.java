import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/*
 * ==============================================================
 * StreamInterviewDSA.java
 * ==============================================================
 *
 * Purpose:
 * --------
 * Contains the MOST USED / MOST ASKED Java Stream problems
 * in Interviews, Backend Development, and DSA-style questions.
 *
 * Covers:
 * -------
 * 1. Filtering & Mapping
 * 2. Duplicate detection & frequency counting
 * 3. Sorting & Top-K problems
 * 4. Grouping & partitioning
 * 5. Reduction & aggregation
 * 6. String-based stream questions (very common in interviews)
 *
 * Complexity:
 * -----------
 * Most operations → O(n)
 * Sorting based → O(n log n)
 * Space → O(n) when collecting results
 *
 * Java Version:
 * -------------
 * Java 8+ (Streams API)
 */

public class StreamInterviewDSA {

    public static void main(String[] args) {

        filterAndMapExample();
        findDuplicates();
        frequencyCount();
        sortNumbers();
        topKFrequent();
        groupingExample();
        partitionExample();
        sumAndAverage();
        firstNonRepeatingCharacter();
        longestString();
    }


    /* ==============================================================
     * 1️⃣ FILTER + MAP (Most basic interview question)
     * ==============================================================
     */
    public static void filterAndMapExample() {
        List<Integer> nums = List.of(1,2,3,4,5,6);

        List<Integer> result = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 10)
                .collect(Collectors.toList());

        System.out.println("Even numbers * 10 → " + result);
    }


    /* ==============================================================
     * 2️⃣ FIND DUPLICATES (VERY COMMON)
     * ==============================================================
     */
    public static void findDuplicates() {
        List<Integer> nums = List.of(1,2,3,2,4,5,1);

        Set<Integer> seen = new HashSet<>();

        List<Integer> duplicates = nums.stream()
                .filter(n -> !seen.add(n))
                .collect(Collectors.toList());

        System.out.println("Duplicates → " + duplicates);
    }


    /* ==============================================================
     * 3️⃣ FREQUENCY COUNT (Classic DSA)
     * ==============================================================
     */
    public static void frequencyCount() {
        List<String> names = List.of("Ram", "Shyam", "Ram", "Mohan");

        Map<String, Long> freq = names.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("Frequency → " + freq);
    }


    /* ==============================================================
     * 4️⃣ SORTING USING STREAMS
     * ==============================================================
     */
    public static void sortNumbers() {
        List<Integer> nums = List.of(5,1,9,2,7);

        List<Integer> asc = nums.stream()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> desc = nums.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Ascending → " + asc);
        System.out.println("Descending → " + desc);
    }


    /* ==============================================================
     * 5️⃣ TOP‑K FREQUENT ELEMENTS (VERY IMPORTANT DSA)
     * ==============================================================
     */
    public static void topKFrequent() {
        List<Integer> nums = List.of(1,1,1,2,2,3);
        int k = 2;

        List<Integer> topK = nums.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Top " + k + " frequent → " + topK);
    }


    /* ==============================================================
     * 6️⃣ GROUPING (Backend reporting style)
     * ==============================================================
     */
    public static void groupingExample() {
        List<String> words = List.of("cat", "dog", "tiger", "lion");

        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("Grouped by length → " + grouped);
    }


    /* ==============================================================
     * 7️⃣ PARTITIONING (TRUE/FALSE bucket split)
     * ==============================================================
     */
    public static void partitionExample() {
        List<Integer> nums = List.of(1,2,3,4,5,6);

        Map<Boolean, List<Integer>> partition = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Partition even/odd → " + partition);
    }


    /* ==============================================================
     * 8️⃣ SUM, AVG, STATISTICS
     * ==============================================================
     */
    public static void sumAndAverage() {
        List<Integer> nums = List.of(10,20,30,40);

        int sum = nums.stream().mapToInt(Integer::intValue).sum();
        double avg = nums.stream().mapToInt(Integer::intValue).average().orElse(0);

        System.out.println("Sum → " + sum);
        System.out.println("Average → " + avg);
    }


    /* ==============================================================
     * 9️⃣ FIRST NON‑REPEATING CHARACTER (STRING DSA FAVORITE)
     * ==============================================================
     */
    public static void firstNonRepeatingCharacter() {
        String input = "swiss";

        Character ch = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First non‑repeating char → " + ch);
    }


    /* ==============================================================
     * 🔟 LONGEST STRING IN LIST
     * ==============================================================
     */
    public static void longestString() {
        List<String> words = List.of("java", "springboot", "api", "microservices");

        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        System.out.println("Longest word → " + longest);
    }
}
