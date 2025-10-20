package calculator;

public class Application {
    public static void main(String[] args) {

        CLI.prompt();
        String input = CLI.read();

        Validator.validate(input);
        long[] parsed = Parser.parse(input);

        for (long l : parsed) {
            System.out.println(l);
        }

        CLI.printResult(6L);
    }
}
