import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            int task;
            if (args.length == 0) {
                System.out.print("Задание (1–6): ");
                task = Integer.parseInt(scanner.nextLine().trim());
            } else {
                task = Integer.parseInt(args[0]);
            }
            switch (task) {
                case 1 -> {
                    System.out.print("Что ищем: ");
                    String target = scanner.nextLine();
                    System.out.print("Количество строк: ");
                    int size = Integer.parseInt(scanner.nextLine().trim());
                    if (size < 0 || size > 100_000) {
                        throw new IllegalArgumentException("Количество строк должно быть от 0 до 100000");
                    }
                    String[] lines = new String[size];
                    System.out.println("Где ищем:");
                    for (int index = 0; index < size; index++) {
                        lines[index] = scanner.nextLine();
                    }
                    System.out.println(StringTasks.countOccurrences(target, lines));
                }
                case 2 -> {
                    System.out.print("Строка: ");
                    System.out.printf(Locale.ROOT, "%.2f%n", StringTasks.shannonEntropy(scanner.nextLine()));
                }
                case 3 -> {
                    System.out.print("Пакет: ");
                    String packet = scanner.nextLine();
                    System.out.print("Сортировка (1 — ID, 2 — температура): ");
                    int field = Integer.parseInt(scanner.nextLine().trim());
                    for (String row : StringTasks.calculateSensorAverages(packet, field)) {
                        System.out.println(row);
                    }
                }
                case 4 -> {
                    System.out.print("Email: ");
                    System.out.println(StringTasks.isEmail(scanner.nextLine()) ? "Да" : "Нет");
                }
                case 5 -> {
                    System.out.print("IPv6: ");
                    System.out.println(StringTasks.isFullIpv6(scanner.nextLine()) ? "Да" : "Нет");
                }
                case 6 -> {
                    System.out.print("Строка: ");
                    System.out.println(StringTasks.removeStuckKeys(scanner.nextLine()));
                }
                default -> throw new IllegalArgumentException("Номер задания должен быть от 1 до 6");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Ошибка: введите целое число");
        } catch (NoSuchElementException exception) {
            System.out.println("Ошибка: недостаточно данных");
        } catch (IllegalArgumentException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }
}
