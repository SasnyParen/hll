import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            int task;
            if (args.length == 0) {
                System.out.print("Задание (1–9): ");
                task = Integer.parseInt(scanner.next());
            } else {
                task = Integer.parseInt(args[0]);
            }
            switch (task) {
                case 1 -> {
                    System.out.print("Возраст: ");
                    System.out.println(LabTasks.formatAge(Integer.parseInt(scanner.next())));
                }
                case 2 -> {
                    System.out.print("Месяц и год: ");
                    int month = Integer.parseInt(scanner.next());
                    int year = Integer.parseInt(scanner.next());
                    System.out.println(LabTasks.daysInMonth(month, year));
                }
                case 3 -> {
                    System.out.print("День недели (1–7): ");
                    int day = Integer.parseInt(scanner.next());
                    System.out.print("Температура (жарко/тепло/холодно): ");
                    String temperature = readWord(scanner);
                    System.out.print("Осадки (ясно/облачно/дождь/снег/град): ");
                    String precipitation = readWord(scanner);
                    System.out.print("Ветер (есть/нет): ");
                    String wind = readWord(scanner);
                    System.out.print("Влажность (высокая/низкая): ");
                    String humidity = readWord(scanner);
                    System.out.println(LabTasks.shouldPlayBadminton(day, temperature, precipitation, wind, humidity)
                            ? "Да" : "Нет");
                }
                case 4 -> {
                    System.out.print("Высота: ");
                    System.out.print(LabTasks.pyramid(Integer.parseInt(scanner.next())));
                }
                case 5 -> {
                    System.out.print("Число: ");
                    System.out.println(LabTasks.heronSqrt(readDouble(scanner)));
                }
                case 6 -> {
                    System.out.print("Верхняя граница: ");
                    System.out.println(Arrays.toString(LabTasks.sieve(Integer.parseInt(scanner.next()))));
                }
                case 7 -> System.out.println(LabTasks.countMaximum(readArray(scanner)));
                case 8 -> {
                    double[] numbers = readArray(scanner);
                    LabTasks.mergeSort(numbers);
                    System.out.println(Arrays.toString(numbers));
                }
                case 9 -> System.out.println(LabTasks.median(readArray(scanner)));
                default -> throw new IllegalArgumentException("Номер задания должен быть от 1 до 9");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Ошибка: введите число");
        } catch (NoSuchElementException exception) {
            System.out.println("Ошибка: недостаточно данных");
        } catch (IllegalArgumentException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }

    private static String readWord(Scanner scanner) {
        return scanner.next().toLowerCase(Locale.ROOT).replace('ё', 'е');
    }

    private static double readDouble(Scanner scanner) {
        return Double.parseDouble(scanner.next().replace(',', '.'));
    }

    private static double[] readArray(Scanner scanner) {
        System.out.print("Количество чисел: ");
        int size = Integer.parseInt(scanner.next());
        if (size < 1 || size > 1_000_000) {
            throw new IllegalArgumentException("Размер массива должен быть от 1 до 1000000");
        }
        double[] numbers = new double[size];
        System.out.print("Числа через пробел: ");
        for (int index = 0; index < size; index++) {
            numbers[index] = readDouble(scanner);
        }
        return numbers;
    }
}
