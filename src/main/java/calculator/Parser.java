package calculator;

import java.util.regex.Pattern;

public class Parser {

    private static final String CUSTOM_SEPARATOR_PREFIX = "//";
    private static final String CUSTOM_SEPARATOR_POSTFIX = "\\n";

    public static long[] parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new long[]{0L};
        }

        String trimmed = input.trim();

        if (trimmed.startsWith(CUSTOM_SEPARATOR_PREFIX)) {
            return parseCustomSeparatorInput(trimmed);
        }

        return parseBasicInput(trimmed);
    }

    private static long[] parseCustomSeparatorInput(String input) {
        int newlineIndex = input.indexOf(CUSTOM_SEPARATOR_POSTFIX);
        String delimiterPart = input.substring(CUSTOM_SEPARATOR_PREFIX.length(), newlineIndex);
        String numbersPart = input.substring(newlineIndex + CUSTOM_SEPARATOR_POSTFIX.length());

        String delimiterRegex = "[,:|" + Pattern.quote(delimiterPart) + "]";
        return toNumberArray(numbersPart.split(delimiterRegex));
    }

    private static long[] parseBasicInput(String input) {
        String[] tokens = input.split("[,:]");
        return toNumberArray(tokens);
    }

    private static long[] toNumberArray(String[] tokens) {
        long[] numbers = new long[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            long value = Long.parseLong(tokens[i].trim());
            numbers[i] = value;
        }

        return numbers;
    }
}
