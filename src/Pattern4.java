import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Pattern4 {

    public static void main(String[] args) {

        // ================================
        // PART 1 — map() Examples
        // ================================

        System.out.println("===== map() Examples =====");

        // Example 1: Uppercase conversion
        List<String> fruits = Arrays.asList("apple", "banana", "mango");

        List<String> upper = fruits.stream()
                .map(String::toUpperCase)
                .collect(toList());

        System.out.println("Uppercase: " + upper);


        // Example 2: Extract names from Employee
        List<Employee> employees = Arrays.asList(
                new Employee("Ram", "IT", 50000),
                new Employee("Shyam", "HR", 40000),
                new Employee("Mohan", "Finance", 60000)
        );

        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(toList());

        System.out.println("Employee Names: " + names);


        // Example 3: Convert String → Integer
        List<String> nums = Arrays.asList("1", "2", "3");

        List<Integer> numbers = nums.stream()
                .map(Integer::parseInt)
                .collect(toList());

        System.out.println("Converted Numbers: " + numbers);


        // ================================
        // PART 2 — flatMap() Examples
        // ================================

        System.out.println("\n===== flatMap() Examples =====");

        // Example 1: Flatten list
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D")
        );

        List<String> flat = nestedList.stream()
                .flatMap(List::stream)
                .collect(toList());

        System.out.println("Flattened List: " + flat);


        // Example 2: Flatten + Uppercase
        List<List<String>> words = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("mango", "orange")
        );

        List<String> transformed = words.stream()
                .flatMap(List::stream)
                .map(String::toUpperCase)
                .collect(toList());

        System.out.println("Flatten + Uppercase: " + transformed);


        // Example 3: Split words into characters
        List<String> wordList = Arrays.asList("Java", "Stream");

        List<String> chars = wordList.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .collect(toList());

        System.out.println("Characters: " + chars);


        // ================================
        // 🚀 YOUR TASK

//        🚀 Your Task Input:
//        List<List<Integer>> list = Arrays.asList( Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6) );
//        Solve: Flatten list → [1,2,3,4,5,6]
//        Square each number → [1,4,9,16,25,36]
        // ================================

        System.out.println("\n===== TASK: Flatten + Square =====");

        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

        List<Integer> result = list.stream()
                .flatMap(List::stream)   // Flatten
                .map(n -> n * n)         // Square
//                .collect(toList());
                .toList();

        System.out.println("Final Result: " + result);
    }
}