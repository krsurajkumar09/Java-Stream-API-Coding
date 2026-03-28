import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pattern1 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana", "kiwi"
        );

        List<Object> input = Arrays.asList("A", "B", "C", "1", "2", "3", "4", 1, 2, 3, "a", "b");

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
    }

}