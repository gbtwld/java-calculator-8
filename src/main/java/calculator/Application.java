package calculator;

public class Application {
    public static void main(String[] args) {

        CLI cli = new CLI();

        cli.prompt();
        String input = cli.read();

        cli.printResult(6L);
    }
}
