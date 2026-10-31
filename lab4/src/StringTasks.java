import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

public final class StringTasks {
    private static final Pattern EMAIL = Pattern.compile(
            "(?=.{1,64}@)[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+"
            + "(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+"
            + "[A-Za-z]{2,63}");
    private static final Pattern IPV6 = Pattern.compile("(?:[0-9A-Fa-f]{4}:){7}[0-9A-Fa-f]{4}");
    private static final Pattern REPEATS = Pattern.compile("(.)\\1{2,}", Pattern.DOTALL);
    private static final Pattern READING = Pattern.compile("[0-9]{2}-?[0-9]{1,2}");

    private StringTasks() {
    }

    public static long countOccurrences(String target, String[] lines) {
        if (target == null || target.isEmpty() || lines == null) {
            throw new IllegalArgumentException("Нужны непустая искомая строка и массив строк");
        }
        long count = 0;
        for (String line : lines) {
            if (line == null) {
                throw new IllegalArgumentException("Строка не задана");
            }
            int position = line.indexOf(target);
            while (position >= 0) {
                count++;
                position = line.indexOf(target, position + 1);
            }
        }
        return count;
    }

    public static double shannonEntropy(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Строка не задана");
        }
        int[] symbols = text.codePoints().toArray();
        if (symbols.length == 0) {
            return 0;
        }
        Arrays.sort(symbols);
        double entropy = 0;
        int start = 0;
        while (start < symbols.length) {
            int end = start + 1;
            while (end < symbols.length && symbols[end] == symbols[start]) {
                end++;
            }
            double probability = (double) (end - start) / symbols.length;
            entropy -= probability * (Math.log(probability) / Math.log(2));
            start = end;
        }
        return entropy == 0 ? 0 : entropy;
    }

    public static String[] calculateSensorAverages(String packet, int sortField) {
        if (packet == null || packet.isEmpty() || packet.length() > 512) {
            throw new IllegalArgumentException("Длина пакета должна быть от 1 до 512");
        }
        if (sortField != 1 && sortField != 2) {
            throw new IllegalArgumentException("Поле сортировки должно быть 1 или 2");
        }
        String[] readings = packet.split("@", -1);
        for (String reading : readings) {
            if (!READING.matcher(reading).matches()) {
                throw new IllegalArgumentException("Некорректное показание: " + reading);
            }
            int temperature = Integer.parseInt(reading.substring(2));
            if (temperature < -50 || temperature > 50) {
                throw new IllegalArgumentException("Температура должна быть от -50 до 50");
            }
        }
        String[] averages = new String[readings.length];
        int size = 0;
        for (String reading : readings) {
            String id = reading.substring(0, 2);
            boolean found = false;
            for (int index = 0; index < size; index++) {
                if (averages[index].startsWith(id + " ")) {
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            int sum = 0;
            int count = 0;
            for (String other : readings) {
                if (other.startsWith(id)) {
                    sum += Integer.parseInt(other.substring(2));
                    count++;
                }
            }
            averages[size++] = id + " " + ((double) sum / count);
        }
        averages = Arrays.copyOf(averages, size);
        for (int index = 1; index < averages.length; index++) {
            String current = averages[index];
            int previous = index - 1;
            while (previous >= 0 && compareSensors(averages[previous], current, sortField) > 0) {
                averages[previous + 1] = averages[previous];
                previous--;
            }
            averages[previous + 1] = current;
        }
        for (int index = 0; index < averages.length; index++) {
            String id = averages[index].substring(0, 2);
            double average = Double.parseDouble(averages[index].substring(3));
            averages[index] = String.format(Locale.ROOT, "%s %.1f", id, average);
        }
        return averages;
    }

    private static int compareSensors(String first, String second, int field) {
        if (field == 2) {
            int byTemperature = Double.compare(Double.parseDouble(first.substring(3)),
                    Double.parseDouble(second.substring(3)));
            if (byTemperature != 0) {
                return byTemperature;
            }
        }
        return first.substring(0, 2).compareTo(second.substring(0, 2));
    }

    public static boolean isEmail(String text) {
        return text != null && text.length() <= 254 && EMAIL.matcher(text).matches();
    }

    public static boolean isFullIpv6(String text) {
        return text != null && IPV6.matcher(text).matches();
    }

    public static String removeStuckKeys(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Строка не задана");
        }
        return REPEATS.matcher(text).replaceAll("$1");
    }
}
