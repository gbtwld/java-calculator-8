package calculator;

public class Application {
    public static void main(String[] args) {

        CLI.prompt();
        String input = CLI.read();

        Validator.validate(input);

        CLI.printResult(6L);
    }
}
