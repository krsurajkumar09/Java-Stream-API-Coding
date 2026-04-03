import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamStringManipulation {

    public static void main(String[] args) {

        String[] input = {
                "aa",
                "b@##b",
                null,
                " ",
                "",
                " c c ",
                "654^^@#",
                "       ",
                "........................436abc668d9g...."
        };

        String result = cleanAndMerge(input);

        System.out.println("Final Output: " + result);
    }

    public static String cleanAndMerge(String[] input) {
        return Arrays.stream(input)
                .filter(Objects::nonNull)                 // remove null
                .map(String::trim)                        // trim spaces
                .filter(s -> !s.isEmpty())                // remove empty
                .map(s -> s.replaceAll("[^a-zA-Z]", ""))  // keep only letters
                .collect(Collectors.joining());           // merge all
    }
}