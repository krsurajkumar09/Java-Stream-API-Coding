import java.util.*;
import java.util.stream.*;

class Employee {
    int id;
    String name;
    int salary;

    Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

public class StreamReductionDemo {

    public static void main(String[] args) {

        // -------------------------------
        // Basic List
        // -------------------------------
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // 1️⃣ Sum
        int sum = nums.stream()
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);

        // 2️⃣ Product
        int product = nums.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Product: " + product);

        // 3️⃣ Max using reduce
        int max = nums.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println("Max: " + max);

        // 4️⃣ Concatenate Strings
        List<String> words = Arrays.asList("Java", "Stream", "API");

        String sentence = words.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("Concatenated: " + sentence.trim());

        // -------------------------------
        // Object Example
        // -------------------------------
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ram", 50000),
                new Employee(2, "Shyam", 60000),
                new Employee(3, "Ramesh", 70000)
        );

        int totalSalary = employees.stream()
                .map(emp -> emp.salary)
                .reduce(0, Integer::sum);

        System.out.println("Total Salary: " + totalSalary);

        // -------------------------------
        // Custom Logic
        // -------------------------------
        int evenSum = nums.stream()
                .filter(n -> n % 2 == 0)
                .reduce(0, Integer::sum);

        System.out.println("Sum of Even Numbers: " + evenSum);

        // -------------------------------
        // 🚀 FINAL TASK (INTERVIEW GOLD)
        // -------------------------------
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6);

        // 🔹 Sum of all numbers
        int total = list.stream()
                .reduce(0, Integer::sum);

        System.out.println("Final Task - Sum: " + total);

        // 🔹 Product of all numbers
        int totalProduct = list.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Final Task - Product: " + totalProduct);

        // 🔹 Sum of squares of odd numbers
        int sumOfSquaresOdd = list.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        System.out.println("Final Task - Sum of Squares of Odd: " + sumOfSquaresOdd);


        // -------------------------------
        // 🔥 BONUS (Interview Upgrade)
        // -------------------------------

        // Cleaner way for sum (preferred)
        int cleanSum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Cleaner Sum (Preferred): " + cleanSum);

        // Optional reduce (no identity)
        Optional<Integer> optionalSum = list.stream()
                .reduce(Integer::sum);

        optionalSum.ifPresent(val ->
                System.out.println("Optional Reduce Sum: " + val)
        );
    }
}