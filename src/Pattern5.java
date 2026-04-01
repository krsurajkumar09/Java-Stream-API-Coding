import java.util.*;
import java.util.stream.Collectors;

public class Pattern5 {

    public static void main(String[] args) {

        // ================================
        // INPUT
        // ================================
        List<Integer> list = Arrays.asList(12, 5, 18, 7, 30, 3);

        // ================================
        // 1. Numbers greater than 10
        // ================================
        List<Integer> greaterThan10 = list.stream()
                .filter(n -> n > 10)
                .collect(Collectors.toList());

        System.out.println("Numbers > 10: " + greaterThan10);

        // ================================
        // 2. Odd numbers
        // ================================
        List<Integer> oddNumbers = list.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        System.out.println("Odd Numbers: " + oddNumbers);

        // ================================
        // 3. Partition into >10 and <=10
        // ================================
        Map<Boolean, List<Integer>> partitioned = list.stream()
                .collect(Collectors.partitioningBy(n -> n > 10));

        System.out.println("Partitioned (>10 vs <=10): " + partitioned);

        // ================================
        // EXTRA (INTERVIEW LEVEL 🔥)
        // ================================

        // Even numbers sorted descending
        List<Integer> evenDesc = list.stream()
                .filter(n -> n % 2 == 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Even numbers (DESC): " + evenDesc);

        // First number greater than 10
        Integer firstGreaterThan10 = list.stream()
                .filter(n -> n > 10)
                .findFirst()
                .orElse(null);

        System.out.println("First number > 10: " + firstGreaterThan10);

        // ================================
        // EMPLOYEE EXAMPLE (OBJECT FILTER)
        // ================================
        List<Employee> employees = Arrays.asList(
                new Employee("Ram", "IT", 60000),
                new Employee("Shyam", "HR", 40000),
                new Employee("Mohan", "IT", 80000),
                new Employee("Ravi", "Finance", 30000)
        );

        // High salary employees
        List<Employee> highPaid = employees.stream()
                .filter(emp -> emp.getSalary() > 50000)
                .collect(Collectors.toList());

        System.out.println("High Paid Employees: " + highPaid);

        // Partition employees based on salary > 50k
        Map<Boolean, List<Employee>> empPartition = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 50000));

        System.out.println("Employee Partition (>50k): " + empPartition);
    }
}