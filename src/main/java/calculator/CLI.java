package calculator;

import camp.nextstep.edu.missionutils.Console;

public class CLI {

    public CLI() {
    }

    public void prompt() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public String read() {
        String input = Console.readLine();

        if (input.isEmpty()) {
            return "0";
        }

        return input;
    }

    public void printResult(long result) {
        System.out.println("결과 : "  + result);
    }
}
