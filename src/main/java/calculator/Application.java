package calculator;

public class Application {
    public static void main(String[] args) {

        CLI.prompt();
        String input = CLI.read();

        Validator.validate(input);
        long[] parsed = Parser.parse(input);

        long result = Calculator.sum(parsed);
        CLI.printResult(result);
    }
}
