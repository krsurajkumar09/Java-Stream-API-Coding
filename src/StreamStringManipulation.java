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
        String dirtySentence = "       ........  This is a       #####^&%^%$*&%^&$&%        clever         fo        x    , s         o, beware$#%^.     ::::::;;;;;;;%$^&^&^&&%&%%    ,                   ";

        String cleanSentence = cleanSentenceStream(dirtySentence);
        System.out.println("Final Output (Sentence): " + cleanSentence);
    }


    // ✅ EXISTING FUNCTION (unchanged)
    public static String cleanAndMerge(String[] input) {
        return Arrays.stream(input)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.replaceAll("[^a-zA-Z]", ""))
                .collect(Collectors.joining());
    }


    // 🔥 NEW STREAM FUNCTION ADDED
    public static String cleanSentenceStream(String input) {

        // Step 1: Keep valid characters
        String cleaned = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> Character.isLetter(c) || c == ' ' || c == ',' || c == '.')
                .map(String::valueOf)
                .collect(Collectors.joining());

        // Step 2: Normalize spaces
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // ✅ Step 3: Remove leading/trailing dots & commas
        cleaned = cleaned.replaceAll("^[,\\.]+", "");   // start
        cleaned = cleaned.replaceAll("[,\\.]+$", "");   // end

        // ✅ Step 4: Fix broken words (better approach)
        // merge sequences like "f o x" → "fox"
        cleaned = cleaned.replaceAll("\\b([a-zA-Z])(?:\\s+([a-zA-Z]))+\\b", match -> {
            return match.group().replaceAll("\\s+", "");
        });

        // fallback (for Java 8 compatibility)
        for (int i = 0; i < 3; i++) {
            cleaned = cleaned.replaceAll("\\b([a-zA-Z])\\s+([a-zA-Z])\\b", "$1$2");
        }

        // ✅ Step 5: Remove unwanted standalone "a"
        cleaned = cleaned.replaceAll("\\b[aA]\\b", "").replaceAll("\\s+", " ").trim();

        // Step 6: Fix punctuation spacing
        cleaned = cleaned.replaceAll("\\s+,", ",");
        cleaned = cleaned.replaceAll("\\s+\\.", ".");

        // ✅ Step 7: Ensure proper ending "."
        if (!cleaned.endsWith(".")) {
            cleaned += ".";
        }

        // Step 8: Capitalize
        if (!cleaned.isEmpty()) {
            cleaned = Character.toUpperCase(cleaned.charAt(0)) + cleaned.substring(1);
        }

        return cleaned;
    }
}