package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    private static final String CUSTOM_SEPARATOR_PREFIX = "//";
    private static final String CUSTOM_SEPARATOR_POSTFIX = "\\n";

    public static void validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return; // 빈 입력은 예외 아님 (0 반환 대상)
        }

        if (trimmed.startsWith(CUSTOM_SEPARATOR_PREFIX)) {
            validateCustomSeparatorInput(trimmed);
        } else {
            validateBasicInput(trimmed);
        }
    }

    private static void validateCustomSeparatorInput(String input) {
        // "//" 이후 반드시 "\n"이 포함되어야 함
        int newlineIndex = input.indexOf(CUSTOM_SEPARATOR_POSTFIX);
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("Invalid input");
        }

        String delimiterPart = input.substring(2, newlineIndex);
        String numbersPart = input.substring(newlineIndex + CUSTOM_SEPARATOR_POSTFIX.length());

        // 구분자는 반드시 한 글자
        if (delimiterPart.length() != 1) {
            throw new IllegalArgumentException("Invalid input");
        }

        String customDelimiter = Pattern.quote(delimiterPart);
        String splitPattern = "[,:|" + customDelimiter + "]";

        // 본문 내 개행이 또 있으면 안 됨
        if (numbersPart.contains("\n")) {
            throw new IllegalArgumentException("Invalid input");
        }

        validateTokens(numbersPart, splitPattern);
    }

    private static void validateBasicInput(String input) {
        if (input.contains(CUSTOM_SEPARATOR_PREFIX)) {
            throw new IllegalArgumentException("Invalid input");
        }

        String splitPattern = "[,:]";
        validateTokens(input, splitPattern);
    }

    private static void validateTokens(String input, String delimiterPattern) {
        String[] tokens = input.split(delimiterPattern);

        for (String token : tokens) {
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (!trimmed.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid input");
            }

            long number = Long.parseLong(trimmed);
            if (number <= 0) {
                throw new IllegalArgumentException("Invalid input");
            }
        }

        // 끝 구분자 검증 (예: "1,2,")
        if (input.matches(".*[,:|]+$")) {
            throw new IllegalArgumentException("Invalid input");
        }

        // 연속 구분자 검증 (예: "1,,2")
        Pattern repeated = Pattern.compile("[,:|]{2,}");
        Matcher matcher = repeated.matcher(input);
        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
