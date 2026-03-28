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
                .collect(Collectors.toList());

        System.out.println("Unique Elements: " + unique);

        // 🧪 2. Duplicate Elements (count > 1)
        List<String> duplicates = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Duplicate Elements: " + duplicates);

        // 🧪 3. First Non-Repeating Element
        String firstNonRepeating = freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First Non-Repeating Element: " + firstNonRepeating);


        // 4. Find the Unique elements (case-insensitive)
        List<Object> result = Collections.singletonList(input.stream()
                .map(obj -> {
                    if (obj instanceof String) {
                        return ((String) obj).toLowerCase(); // case-insensitive
                    } else {
                        return String.valueOf(obj); // convert numbers to string
                    }
                })
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList());

        System.out.println("Unique Element: " + result);


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
    }

}