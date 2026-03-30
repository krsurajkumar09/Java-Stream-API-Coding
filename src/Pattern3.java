import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

//Use IntStream when:
//
//        ✔ Numbers
//✔ Calculations
//✔ Performance matters
//✔ Large data
//
//Use Stream<Integer> when:
//
//        ✔ Collections
//✔ Grouping / Mapping
//✔ Complex objects
//✔ Custom sorting



public class Pattern3 {

    public static void main(String[] args) {

        int[] nums = {10, 25, 3, 99, 45, 60};

        // Sum
        int sum = Arrays.stream(nums).sum();

        // Min
        int min = Arrays.stream(nums).min().getAsInt();

        // Max
        int max = Arrays.stream(nums).max().getAsInt();

        // Average
        double avg = Arrays.stream(nums).average().getAsDouble();

        // Count
        long count = Arrays.stream(nums).count();

        // Sorted Ascending
        int[] sorted = Arrays.stream(nums)
                .sorted()
                .toArray();

        // Sorted Descending (IMPORTANT 🔥)
        int[] sortedDesc = Arrays.stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        // Top 2 numbers
        int[] top2 = Arrays.stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .mapToInt(Integer::intValue)
                .toArray();

        // Summary Statistics ⭐
        IntSummaryStatistics stats = Arrays.stream(nums).summaryStatistics();

        System.out.println("---- IntStream ----");
        System.out.println("Sum: " + sum);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Avg: " + avg);
        System.out.println("Count: " + count);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println("Sorted Desc: " + Arrays.toString(sortedDesc));
        System.out.println("Top 2: " + Arrays.toString(top2));
        System.out.println("Stats: " + stats);



        List<Integer> list = Arrays.asList(10, 25, 3, 99, 45, 60);

        // 1. Sum
        int sum2 = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        // 2. Min
        int min2 = list.stream()
                .mapToInt(Integer::intValue)
                .min()
                .getAsInt();

        // 3. Max
        int max2 = list.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        // 4. Average
        double avg2 = list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        // 5. Count
        long count2 = list.stream().count();

        // 6. Sort Ascending
        List<Integer> sortedAsc = list.stream()
                .sorted()
                .collect(Collectors.toList());

        // 7. Sort Descending
        List<Integer> sortedDesc2 = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // 8. Top 2 Numbers
        List<Integer> top_2 = list.stream()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .collect(Collectors.toList());

        // 9. Second Highest
        int secondHighest = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();

        // 10. Distinct + Sorted
        List<Integer> distinctSorted = list.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("---- List Stream ----");
        System.out.println("Sum: " + sum2);
        System.out.println("Min: " + min2);
        System.out.println("Max: " + max2);
        System.out.println("Avg: " + avg2);
        System.out.println("Count: " + count2);
        System.out.println("Sorted Asc: " + sortedAsc);
        System.out.println("Sorted Desc: " + sortedDesc2);
        System.out.println("Top 2: " + top_2);
        System.out.println("Second Highest: " + secondHighest);
        System.out.println("Distinct Sorted: " + distinctSorted);


//    **************************** Strings******************************************************************

    // 🧪 Example 1 — Longest String
    List<String> list3 = Arrays.asList("Banana", "Apple", "Mango", "Orange");

    String longest = list3.stream()
            .max(Comparator.comparing(String::length))
            .orElse(null);

        System.out.println("Longest String: " + longest);
    // Output: Banana


    // 🧪 Example 2 — Shortest String
    String shortest = list3.stream()
            .min(Comparator.comparing(String::length))
            .orElse(null);

        System.out.println("Shortest String: " + shortest);
    // Output: Apple


    // 🧪 Example 3 — Sort Strings by Length
    List<String> sorted3 = list3.stream()
            .sorted(Comparator.comparing(String::length))
            .collect(Collectors.toList());

        System.out.println("Sorted by Length: " + sorted3);
    // Output: [Apple, Mango, Banana, Orange]


    // 🧪 Example 4 — Sort Numbers Descending
    List<Integer> nums3 = Arrays.asList(5, 1, 9, 3, 7);

    List<Integer> desc3 = nums3.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        System.out.println("Descending Order: " + desc3);
    // Output: [9, 7, 5, 3, 1]


    // 🧪 Example 5 — Top 3 Largest Numbers 🔥
    List<Integer> top3 = nums3.stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .collect(Collectors.toList());

        System.out.println("Top 3 Numbers: " + top3);
    // Output: [9, 7, 5]


    // 🧪 Example 6 — Max Object (IMPORTANT)
    List<Employee> employees = Arrays.asList(
            new Employee("Suraj", "IT", 50000),
            new Employee("Amit", "IT", 70000),
            new Employee("Neha", "IT", 70000),
            new Employee("Ravi", "HR", 40000),
            new Employee("Priya", "HR", 60000),
            new Employee("Karan", "HR", 60000),
            new Employee("John", "Sales", 45000),
            new Employee("Doe", "Sales", 80000)
    );

    Employee maxSalaryEmp = employees.stream()
            .max(Comparator.comparing(Employee::getSalary))
            .orElse(null);

        System.out.println("Max Salary Employee: " + maxSalaryEmp);


    // 🔥 BONUS: Top 2 Highest Paid Employees
    List<Employee> top2Employees = employees.stream()
            .sorted(Comparator.comparing(Employee::getSalary).reversed())
            .limit(2)
            .collect(Collectors.toList());

        System.out.println("Top 2 Employees: " + top2Employees);
   }

}
