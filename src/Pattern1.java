import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pattern1 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana", "kiwi"
        );

        List<Object> input = Arrays.asList("A", "B", "C", "1", "2", "3", "4", 1, 2, 3, "a", "b");

        List <String> input2 = List.of("apple", "banana", "apple", "orange", "banana", "kiwi");

        String[] input3 = {"apple", "banana", "apple", "orange", "banana", "kiwi"};

        // 🔥 STEP 1 — Create Frequency Map
        Map<String, Long> freqMap = list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,   // preserves order
                        Collectors.counting()
                ));

        System.out.println("Frequency Map: " + freqMap);

        // 🧪 1. Unique Elements (count == 1)
        List<String> unique = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
                .toList();

        System.out.println("Unique Elements: " + unique);

        // 🧪 2. Duplicate Elements (count > 1)
        List<String> duplicates = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
//              .toList(); // We can also use it

        System.out.println("Duplicate Elements: " + duplicates);

        // 🧪 3. First Non-Repeating Element
        String firstNonRepeating = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First Non-Repeating Element: " + firstNonRepeating);


        // 4. Find the Unique elements (case-insensitive)
        List<Object> result = input.stream()
                .map(obj -> {
                    if (obj instanceof String s) return s.toLowerCase();
                    if (obj instanceof Number n) return String.valueOf(n);
                    return obj; // required
                })
                .collect(Collectors.groupingBy
                        (element -> element,
                                LinkedHashMap::new,
                                Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Unique Element of Interview: " + result);


//        Question: Input: ["apple", "banana", "apple", "orange", "banana", "kiwi"]
//        Find: 1. Unique elements
//        2. Duplicate elements
//        3. First non-repeating element

        //    Find: 1. Unique elements
        List<String> UniqueFruits = Arrays.stream(input3)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Unique Fruits: " + UniqueFruits);


        String[] UniqueFruits2 = Arrays.stream(input3)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);

        System.out.println("Unique Fruits: " + Arrays.toString(UniqueFruits2));


        //    Find: 2. Duplicate elements
        List<String> duplicatesFruits = input2.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Duplicates Fruits: " + duplicatesFruits);

        //    Find: 3. First non-repeating element

        String firstNonRepeating2 = input2.stream()
                .collect(Collectors.groupingBy(
                        n -> n,
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First Non-Repeating Element: " + firstNonRepeating2);

        // 🔥 QUESTION — 2nd Lowest Number & its Occurrence
        List<Integer> numbers = Arrays.asList(10,10,34,45,10,9,9,10);


        // Step 1: Frequency Map (Sorted using TreeMap)
        Map<Integer, Long> freq = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        TreeMap::new,
                        Collectors.counting()
                ));

        System.out.println("Frequency Map (Sorted): " + freq);

        // Step 2: Get 2nd lowest key
        Integer secondLowest = freq.keySet().stream()
                .skip(1)
                .findFirst()
                .orElse(null);

        // Step 3: Get occurrence
        long occurrence = secondLowest != null ? freq.get(secondLowest) : 0;

         // Alternate Way
//      Integer secondLowest = numbers.stream()
//                .distinct()
//                .sorted()
//                .skip(1)
//                .findFirst()
//                .orElse(null);
//
//      long count = numbers.stream()
//                .filter(n -> n.equals(secondLowest))
//                .count();

        System.out.println("Second Lowest Number: " + secondLowest);
        System.out.println("Number of Occurrences: " + occurrence);

//      If the input is primitive array

        int[] numbers_ = {10,10,34,45,10,9,9,10};

        // Step 1: Frequency Map (Sorted using TreeMap)
        Map<Integer, Long> freq_ = Arrays.stream(numbers_)
                .boxed() // 🔥 convert int -> Integer
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        TreeMap::new,
                        Collectors.counting()
                ));

        System.out.println("Frequency Map (Sorted): " + freq);

        // Step 2: Get 2nd lowest key
        Integer secondLowest_ = freq_.keySet().stream()
                .skip(1)
                .findFirst()
                .orElse(null);

        // Step 3: Get occurrence
        long occurrence_ = secondLowest != null ? freq.get(secondLowest_) : 0;

        System.out.println("Second Lowest Number: " + secondLowest_);
        System.out.println("Number of Occurrences: " + occurrence_);


        // Count the frequency of String input /Character array input/ primitive array input

        // a.Count the frequency of String input
        String str = "aabccd";

        Map<Character, Long> freqMapping = str.chars()   // IntStream
                .mapToObj(c -> (char) c)            // convert int → char
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(freqMapping);

        // b.Count the frequency of character array input

        char[] arr3 = {'a','a','b','c','c','d'};

        Map<Character, Long> freqMapping2 = new String(arr3)  // convert to String
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(freqMapping2);

        // c.Count the frequency of Character array input

        Character[] arr = {'a','a','b','c','c','d'};

        Map<Character, Long> freqMapping1 = Arrays.stream(arr)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(freqMapping1);

    }

}