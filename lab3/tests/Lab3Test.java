import java.util.Arrays;
import java.util.Random;

public class Lab3Test {
    public static void main(String[] args) {
        int[] ages = {0, 1, 2, 4, 5, 11, 12, 14, 18, 21, 22, 25, 111, 112, 121};
        String[] words = {"лет", "год", "года", "года", "лет", "лет", "лет", "лет", "лет",
                "год", "года", "лет", "лет", "лет", "год"};
        for (int index = 0; index < ages.length; index++) {
            check(LabTasks.formatAge(ages[index]).equals(ages[index] + " " + words[index]));
        }
        rejects(() -> LabTasks.formatAge(-1));
        check(LabTasks.daysInMonth(2, 1900) == 28);
        check(LabTasks.daysInMonth(2, 2000) == 29);
        check(LabTasks.daysInMonth(2, 2024) == 29);
        check(LabTasks.daysInMonth(2, 2025) == 28);
        for (int month = 1; month <= 12; month++) {
            check(LabTasks.daysInMonth(month, 2026) == java.time.YearMonth.of(2026, month).lengthOfMonth());
        }
        rejects(() -> LabTasks.daysInMonth(13, 2026));
        check(LabTasks.shouldPlayBadminton(7, "тепло", "ясно", "нет", "низкая"));
        check(!LabTasks.shouldPlayBadminton(6, "тепло", "ясно", "нет", "низкая"));
        check(!LabTasks.shouldPlayBadminton(7, "холодно", "ясно", "нет", "низкая"));
        check(!LabTasks.shouldPlayBadminton(7, "тепло", "дождь", "нет", "низкая"));
        check(!LabTasks.shouldPlayBadminton(7, "тепло", "ясно", "есть", "низкая"));
        check(!LabTasks.shouldPlayBadminton(7, "тепло", "ясно", "нет", "высокая"));
        check(LabTasks.pyramid(1).equals("#  #\n"));
        check(LabTasks.pyramid(2).equals(" #  #\n##  ##\n"));
        check(LabTasks.pyramid(23).lines().count() == 23);
        rejects(() -> LabTasks.pyramid(0));

        double[] roots = {0, 1, 2, 4, 0.25, 1e-200, 1e200, Double.MIN_VALUE, Double.MAX_VALUE};
        for (double number : roots) {
            double expected = Math.sqrt(number);
            double actual = LabTasks.heronSqrt(number);
            check(expected == 0 ? actual == 0 : Math.abs(actual - expected) / expected < 1e-13);
        }
        rejects(() -> LabTasks.heronSqrt(-1));
        rejects(() -> LabTasks.heronSqrt(Double.NaN));
        check(Arrays.equals(LabTasks.sieve(0), new int[0]));
        check(Arrays.equals(LabTasks.sieve(1), new int[0]));
        check(Arrays.equals(LabTasks.sieve(2), new int[]{2}));
        check(Arrays.equals(LabTasks.sieve(30), new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}));
        check(LabTasks.sieve(100).length == 25);
        check(LabTasks.countMaximum(new double[]{1, 5, 10, 10, 0, -10}) == 2);
        check(LabTasks.countMaximum(new double[]{-5, -2, -2, -10}) == 2);
        check(LabTasks.countMaximum(new double[]{4, 4, 4}) == 3);
        check(LabTasks.countMaximum(new double[]{-0.0, 0.0}) == 2);
        rejects(() -> LabTasks.countMaximum(new double[0]));
        rejects(() -> LabTasks.median(new double[]{Double.POSITIVE_INFINITY}));

        Random random = new Random(42);
        for (int size = 0; size <= 200; size++) {
            double[] values = new double[size];
            for (int index = 0; index < size; index++) {
                values[index] = random.nextInt(101) - 50;
            }
            double[] expected = values.clone();
            Arrays.sort(expected);
            double[] actual = values.clone();
            LabTasks.mergeSort(actual);
            check(Arrays.equals(actual, expected));
            if (size > 0) {
                double expectedMedian = size % 2 == 1 ? expected[size / 2]
                        : (expected[size / 2 - 1] + expected[size / 2]) / 2;
                check(LabTasks.median(values) == expectedMedian);
            }
        }
        double[] unchanged = {5, 1, 3};
        check(LabTasks.median(unchanged) == 3);
        check(Arrays.equals(unchanged, new double[]{5, 1, 3}));
        check(LabTasks.median(new double[]{Double.MAX_VALUE, Double.MAX_VALUE}) == Double.MAX_VALUE);
        check(LabTasks.median(new double[]{-Double.MAX_VALUE, Double.MAX_VALUE}) == 0);
        rejects(() -> LabTasks.median(new double[0]));
        System.out.println("All checks passed");
    }

    private static void check(boolean condition) {
        if (!condition) {
            throw new AssertionError("Check failed");
        }
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException exception) {
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }
}
