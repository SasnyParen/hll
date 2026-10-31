import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            System.out.print("Введите имя: ");
            if (!scanner.hasNextLine()) {
                return;
            }
            String name = scanner.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Имя не должно быть пустым. Введите имя: ");
                if (!scanner.hasNextLine()) {
                    return;
                }
                name = scanner.nextLine().trim();
            }
            System.out.println("Привет, " + name + "!");
            printNumericLimits();
            while (true) {
                System.out.print("Введите длину ребра: ");
                if (!scanner.hasNextLine()) {
                    return;
                }
                try {
                    double edge = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                    if (!Double.isFinite(edge) || edge <= 0) {
                        System.out.println("Введите положительное число.");
                        continue;
                    }
                    double volume = dodecahedronVolume(edge);
                    if (!Double.isFinite(volume) || volume == 0) {
                        System.out.println("Объём вне диапазона double.");
                        continue;
                    }
                    System.out.printf(Locale.ROOT, "Объём: %.12g%n", volume);
                    break;
                } catch (NumberFormatException exception) {
                    System.out.println("Введите число.");
                }
            }
        }
    }

    public static void printNumericLimits() {
        System.out.println("byte: min = " + Byte.MIN_VALUE + ", max = " + Byte.MAX_VALUE);
        System.out.println("short: min = " + Short.MIN_VALUE + ", max = " + Short.MAX_VALUE);
        System.out.println("int: min = " + Integer.MIN_VALUE + ", max = " + Integer.MAX_VALUE);
        System.out.println("long: min = " + Long.MIN_VALUE + ", max = " + Long.MAX_VALUE);
        System.out.println("char: min = " + (int) Character.MIN_VALUE
                + ", max = " + (int) Character.MAX_VALUE);
        System.out.println("float: min = " + (-Float.MAX_VALUE) + ", max = " + Float.MAX_VALUE);
        System.out.println("double: min = " + (-Double.MAX_VALUE) + ", max = " + Double.MAX_VALUE);
    }
    public static double dodecahedronVolume(double a) {
        return ((15.0 + 7.0 * Math.sqrt(5.0)) / 4.0) * a * a * a;
    }
}

