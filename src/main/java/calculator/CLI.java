package calculator;

import camp.nextstep.edu.missionutils.Console;

public class CLI {

    public static void prompt() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public static String read() {
        String input = Console.readLine();

        if (input.isEmpty()) {
            return "0";
        }

        return input;
    }

    public static void printResult(long result) {
        System.out.println("결과 : "  + result);
    }
}
