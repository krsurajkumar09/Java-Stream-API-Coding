import java.util.*;
import java.util.stream.Collectors;

public class Pattern2 {

    public static void main(String[] args) {


//        For below Questions, I need to understand which function to use and why, Provide the answer which is easier to execute in Interview.
//
//
//        1.Sort all the Employee on the basis of Salary.
//
//        2.Sort all the employee on the basis of Salary per department.
//
//        3.Find the Employee with highest Salary per department. (a. Show all details of Employee   b. Show only Name)
//
//        4.Find the top 2 Salaried employee per department.
//
//        5.Find the 2nd highest salary per department.

        List<Student> StudentList = new ArrayList<>();

        StudentList.add(new Student(1, "Ram"));
        StudentList.add(new Student(1, "Shyam"));
        StudentList.add(new Student(2, "Ramesh"));
        StudentList.add(new Student(3, "Mugesh"));

        //Convert the List into Map

        Map<Integer, String> studentMap = StudentList.stream()
                .collect(Collectors.toMap(
                        Student::getId,
                        Student::getName,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
//        // 🔥 Print
//        studentMap.forEach(
//                (x, y) -> System.out.println("Student ID : " + x + ", Student Name : " + y)
//        );


        // Sorting , Ranking, Getting nth Ranked salaried Employee


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

        // 1. Sort all employees by salary
        System.out.println("1. Sort all Employees by Salary:");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
//                .forEach(System.out::println);
                .forEach(e -> System.out.println(
                "Name: " + e.getName() + " | " + "Dept: " + e.getDepartment() + " | " + "Salary: "+ e.getSalary()   //Custom Formating
        ));

        // 2. Sort employees by salary per department
        System.out.println("\n2. Sort Employees by Salary per Department:");
        Map<String, List<Employee>> sortedDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::getSalary))
                                        .collect(Collectors.toList())
                        )));

        sortedDept.forEach((dept, empList) -> {
            System.out.println(dept + " -> " + empList);
        });


        // 3a. Highest salary employee per department (full object)
        System.out.println("\n3a. Highest Salary Employee per Department:");
        Map<String, Optional<Employee>> highestEmp = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

        highestEmp.forEach((dept, emp) -> {
            System.out.println(dept + " -> " + emp.get());
        });


        // 3b. Only Name
        System.out.println("\n3b. Highest Salary Employee Name per Department:");
        highestEmp.forEach((dept, emp) -> {
            System.out.println(dept + " -> " + emp.get().getName() + "  " + emp.get().getSalary());
        });


        // 4. Top 2 salaried employees per department
        System.out.println("\n4. Top 2 Salaried Employees per Department:");
        Map<String, List<Employee>> top2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::getSalary).reversed())
                                        .limit(2)
                                        .collect(Collectors.toList())
                        )));

        top2.forEach((dept, empList) -> {
            System.out.println(dept + " -> " + empList);
        });


        // 5. 2nd highest salary per department
        System.out.println("\n5. 2nd Highest Salary per Department:");

        Map<String, Optional<Integer>> secondHighest = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getSalary,
                                Collectors.collectingAndThen(Collectors.toList(),
                                        list -> list.stream()
                                                .distinct()
                                                .sorted(Comparator.reverseOrder())
                                                .skip(1)
                                                .findFirst()
                                ))));

        secondHighest.forEach((dept, salary) -> {
            System.out.println(dept + " -> " + salary.orElse(null));
        });

    }
}
