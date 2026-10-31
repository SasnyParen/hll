public final class LabTasks {
    private LabTasks() {
    }

    public static String formatAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        int lastTwo = age % 100;
        String word;
        if (lastTwo >= 11 && lastTwo <= 14) {
            word = "лет";
        } else {
            word = switch (age % 10) {
                case 1 -> "год";
                case 2, 3, 4 -> "года";
                default -> "лет";
            };
        }
        return age + " " + word;
    }

    public static int daysInMonth(int month, int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Год должен быть положительным");
        }
        boolean leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> leap ? 29 : 28;
            default -> throw new IllegalArgumentException("Месяц должен быть от 1 до 12");
        };
    }

    public static boolean shouldPlayBadminton(int day, String temperature, String precipitation,
                                              String wind, String humidity) {
        if (day < 1 || day > 7) {
            throw new IllegalArgumentException("День недели должен быть от 1 до 7");
        }
        if (!(temperature.equals("жарко") || temperature.equals("тепло") || temperature.equals("холодно"))
                || !(precipitation.equals("ясно") || precipitation.equals("облачно")
                || precipitation.equals("дождь") || precipitation.equals("снег") || precipitation.equals("град"))
                || !(wind.equals("есть") || wind.equals("нет"))
                || !(humidity.equals("высокая") || humidity.equals("низкая"))) {
            throw new IllegalArgumentException("Некорректное описание погоды");
        }
        return day == 7 && temperature.equals("тепло") && precipitation.equals("ясно")
                && wind.equals("нет") && humidity.equals("низкая");
    }

    public static String pyramid(int height) {
        if (height < 1 || height > 200) {
            throw new IllegalArgumentException("Высота должна быть от 1 до 200");
        }
        StringBuilder result = new StringBuilder();
        for (int row = 1; row <= height; row++) {
            for (int space = 0; space < height - row; space++) {
                result.append(' ');
            }
            for (int column = 0; column < row; column++) {
                result.append('#');
            }
            result.append("  ");
            for (int column = 0; column < row; column++) {
                result.append('#');
            }
            result.append('\n');
        }
        return result.toString();
    }

    public static double heronSqrt(double number) {
        if (!Double.isFinite(number) || number < 0) {
            throw new IllegalArgumentException("Число должно быть конечным и неотрицательным");
        }
        if (number == 0) {
            return 0;
        }
        double current = Math.scalb(1.0, Math.floorDiv(Math.getExponent(number), 2));
        double next = current / 2 + (number / current) / 2;
        while (Math.abs(next - current) > 1e-14 * next) {
            current = next;
            next = current / 2 + (number / current) / 2;
        }
        return next;
    }

    public static int[] sieve(int limit) {
        if (limit < 0 || limit > 10_000_000) {
            throw new IllegalArgumentException("Граница должна быть от 0 до 10000000");
        }
        boolean[] composite = new boolean[limit + 1];
        for (int number = 2; number <= limit / number; number++) {
            if (!composite[number]) {
                for (int multiple = number * number; multiple <= limit; multiple += number) {
                    composite[multiple] = true;
                }
            }
        }
        int count = 0;
        for (int number = 2; number <= limit; number++) {
            if (!composite[number]) {
                count++;
            }
        }
        int[] primes = new int[count];
        int index = 0;
        for (int number = 2; number <= limit; number++) {
            if (!composite[number]) {
                primes[index++] = number;
            }
        }
        return primes;
    }

    public static int countMaximum(double[] numbers) {
        checkArray(numbers);
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Массив пуст");
        }
        double maximum = numbers[0];
        int count = 0;
        for (double number : numbers) {
            if (number > maximum) {
                maximum = number;
                count = 1;
            } else if (number == maximum) {
                count++;
            }
        }
        return count;
    }

    public static void mergeSort(double[] numbers) {
        checkArray(numbers);
        mergeSort(numbers, new double[numbers.length], 0, numbers.length);
    }

    private static void mergeSort(double[] numbers, double[] buffer, int left, int right) {
        if (right - left < 2) {
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(numbers, buffer, left, middle);
        mergeSort(numbers, buffer, middle, right);
        int first = left;
        int second = middle;
        for (int index = left; index < right; index++) {
            if (first < middle && (second >= right || numbers[first] <= numbers[second])) {
                buffer[index] = numbers[first++];
            } else {
                buffer[index] = numbers[second++];
            }
        }
        for (int index = left; index < right; index++) {
            numbers[index] = buffer[index];
        }
    }

    public static double median(double[] numbers) {
        checkArray(numbers);
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Массив пуст");
        }
        double[] sorted = numbers.clone();
        mergeSort(sorted);
        int middle = sorted.length / 2;
        if (sorted.length % 2 == 1) {
            return sorted[middle];
        }
        double first = sorted[middle - 1];
        double second = sorted[middle];
        if (first < 0 && second > 0) {
            return first / 2 + second / 2;
        }
        return first + (second - first) / 2;
    }

    private static void checkArray(double[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Массив не задан");
        }
        for (double number : numbers) {
            if (!Double.isFinite(number)) {
                throw new IllegalArgumentException("Элементы должны быть конечными числами");
            }
        }
    }
}
