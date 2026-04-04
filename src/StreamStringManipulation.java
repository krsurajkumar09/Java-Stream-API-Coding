import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamStringManipulation {

    public static void main(String[] args) {

        // ===== CASE 1: Array cleaning =====
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
        System.out.println("Final Output (Array): " + result); // aabbccabcdg


        // ===== CASE 2: Sentence cleaning =====
        String dirtySentence = "       ........  This is a       #####^&%^%$*&%^&$&%        clever       fox    , s   o, beware$#%^.     ::::::;;;;;;;%$^&^&^&&%&%%    ,                   ";

        String cleanSentence = cleanSentenceStream(dirtySentence);
        System.out.println("Final Output (Sentence): " + cleanSentence);
    }


    // ✅ ARRAY CLEANING (STREAM)
    public static String cleanAndMerge(String[] input) {
        return Arrays.stream(input)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.replaceAll("[^a-zA-Z]", ""))
                .collect(Collectors.joining());
    }


    // 🔥 SENTENCE CLEANING (STREAM + SMART PARSING)
    public static String cleanSentenceStream(String input) {

        // Step 1: Keep only valid characters
        String cleaned = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> Character.isLetter(c) || c == ' ' || c == ',' || c == '.')
                .map(String::valueOf)
                .collect(Collectors.joining());

        // Step 2: Normalize spaces
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // Step 3: Remove leading/trailing punctuation
        cleaned = cleaned.replaceAll("^[,.]+", "");
        cleaned = cleaned.replaceAll("[,.]+$", "");

        // 🔥 Step 4: Separate punctuation properly
        cleaned = cleaned.replaceAll("([,.])", " $1 ");
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        String[] tokens = cleaned.split(" ");

        StringBuilder result = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        for (String token : tokens) {

            // Single letter
            if (token.matches("[a-zA-Z]")) {
                buffer.append(token);
            }

            // Full word
            else if (token.matches("[a-zA-Z]+")) {

                if (buffer.length() > 0) {
                    result.append(buffer).append(" ");
                    buffer.setLength(0);
                }

                result.append(token).append(" ");
            }

            // Punctuation
            else if (token.equals(",") || token.equals(".")) {

                if (buffer.length() > 0) {
                    result.append(buffer);
                    buffer.setLength(0);
                }

                // remove space before punctuation
                int len = result.length();
                if (len > 0 && result.charAt(len - 1) == ' ') {
                    result.deleteCharAt(len - 1);
                }

                result.append(token).append(" ");
            }
        }

        // Flush buffer
        if (buffer.length() > 0) {
            result.append(buffer).append(" ");
        }

        cleaned = result.toString().trim();

        // Remove standalone "a"
        cleaned = cleaned.replaceAll("\\b[aA]\\b", "")
                .replaceAll("\\s+", " ")
                .trim();

        // Ensure single ending period
        cleaned = cleaned.replaceAll("[,.]+$", "");
        cleaned += ".";

        // Capitalize
        cleaned = Character.toUpperCase(cleaned.charAt(0)) + cleaned.substring(1);

        return cleaned;
    }
}