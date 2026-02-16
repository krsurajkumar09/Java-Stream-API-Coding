/*
 * Problem Statement:
 * Demonstrate all possible ways to create Java Streams and
 * showcase all built-in Stream API methods (Intermediate + Terminal).
 *
 * Approach:
 * 1. Show different Stream creation techniques.
 * 2. Demonstrate intermediate operations (filter, map, flatMap, sorted, etc.).
 * 3. Demonstrate terminal operations (collect, reduce, count, etc.).
 * 4. Demonstrate primitive stream operations (IntStream, LongStream, DoubleStream).
 *
 * Time Complexity:
 * Depends on operation used:
 * - filter/map → O(n)
 * - sorted → O(n log n)
 * - reduce → O(n)
 *
 * Space Complexity:
 * - O(n) for collecting operations
 * - O(1) for reduction operations (if not storing)
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamFullDemo {

    public static void main(String[] args) throws IOException {

        demonstrateStreamCreation();
        demonstrateIntermediateOperations();
        demonstrateTerminalOperations();
        demonstratePrimitiveStreams();
        demonstrateSpecialMethods();
    }

    /*
     * ==============================
     * 1️⃣ STREAM CREATION METHODS
     * ==============================
     */
    public static void demonstrateStreamCreation() {

        // From Collection
        List<String> list = List.of("A", "B", "C");
        list.stream().forEach(System.out::println);
        list.parallelStream().forEach(System.out::println);

        // From Array
        String[] arr = {"X", "Y", "Z"};
        Arrays.stream(arr).forEach(System.out::println);

        // From Primitive Array
        int[] numsArray = {1, 2, 3};
        Arrays.stream(numsArray).forEach(System.out::println);

        // Using Stream.of()
        Stream.of("P", "Q", "R").forEach(System.out::println);

        // Empty Stream
        Stream.empty().forEach(System.out::println);

        // Using Builder
        Stream.<String>builder()
                .add("One")
                .add("Two")
                .build()
                .forEach(System.out::println);

        // Using generate
        Stream.generate(Math::random)
                .limit(3)
                .forEach(System.out::println);

        // Using iterate
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);

        // Using IntStream range
        IntStream.range(1, 5).forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

        // From String
        "HELLO".chars().forEach(System.out::println);

        // From Pattern
        Pattern.compile(",")
                .splitAsStream("A,B,C")
                .forEach(System.out::println);

        // From Optional
        Optional.of("OptionalValue")
                .stream()
                .forEach(System.out::println);

        // From Map
        Map<String, Integer> map = Map.of("A", 1, "B", 2);
        map.entrySet().stream()
                .forEach(System.out::println);
    }

    /*
     * ==============================
     * 2️⃣ INTERMEDIATE OPERATIONS
     * ==============================
     */
    public static void demonstrateIntermediateOperations() {

        List<Integer> numbers = List.of(1,2,3,4,5,6);

        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .flatMap(n -> Stream.of(n, n + 1))
                .distinct()
                .sorted()
                .peek(System.out::println)
                .limit(5)
                .skip(1)
                .collect(Collectors.toList());
    }

    /*
     * ==============================
     * 3️⃣ TERMINAL OPERATIONS
     * ==============================
     */
    public static void demonstrateTerminalOperations() {

        List<Integer> numbers = List.of(1,2,3,4,5,6);

        numbers.stream().forEach(System.out::println);

        numbers.parallelStream().forEachOrdered(System.out::println);

        List<Integer> collected =
                numbers.stream().collect(Collectors.toList());

        Set<Integer> set =
                numbers.stream().collect(Collectors.toSet());

        Map<Integer, Integer> squaredMap =
                numbers.stream().collect(Collectors.toMap(
                        Function.identity(),
                        n -> n * n
                ));

        Map<Boolean, List<Integer>> partitioned =
                numbers.stream().collect(
                        Collectors.partitioningBy(n -> n % 2 == 0)
                );

        long count = numbers.stream().count();

        int sum = numbers.stream().reduce(0, Integer::sum);

        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);

        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);

        Optional<Integer> first = numbers.stream().findFirst();
        Optional<Integer> any = numbers.stream().findAny();
    }

    /*
     * ==============================
     * 4️⃣ PRIMITIVE STREAM METHODS
     * ==============================
     */
    public static void demonstratePrimitiveStreams() {

        List<Integer> numbers = List.of(1,2,3,4,5,6);

        int total = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        OptionalDouble average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average();

        IntSummaryStatistics stats =
                numbers.stream()
                        .mapToInt(Integer::intValue)
                        .summaryStatistics();
    }

    /*
     * ==============================
     * 5️⃣ SPECIAL STREAM METHODS
     * ==============================
     */
    public static void demonstrateSpecialMethods() {

        List<Integer> numbers = List.of(1,2,3,4,5,6);

        numbers.stream()
                .parallel()
                .sequential()
                .unordered()
                .onClose(() -> System.out.println("Stream closed"))
                .close();
    }
}
