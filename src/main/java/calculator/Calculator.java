package calculator;

public class Calculator {

    public static long sum(long[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0L;
        }

        long total = 0L;

        for (long num : numbers) {
            total += num;
        }

        return total;
    }
}
