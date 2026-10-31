import java.util.Arrays;
import java.util.Locale;

public class Lab4Test {
    public static void main(String[] args) {
        check(StringTasks.countOccurrences("aa", new String[]{"aaba", "ababa", "cgaaaer"}) == 3);
        check(StringTasks.countOccurrences("aaa", new String[]{"aaaaa"}) == 3);
        check(StringTasks.countOccurrences("a", new String[0]) == 0);
        rejects(() -> StringTasks.countOccurrences("", new String[]{"abc"}));
        check(String.format(Locale.ROOT, "%.2f", StringTasks.shannonEntropy("TEST STRING test string")).equals("3.73"));
        check(StringTasks.shannonEntropy("") == 0);
        check(StringTasks.shannonEntropy("aaaa") == 0);
        check(StringTasks.shannonEntropy("ab") == 1);
        check(StringTasks.shannonEntropy("😀😀😁😁") == 1);

        String packet = "3744@6646@63-4@461@127";
        check(Arrays.equals(StringTasks.calculateSensorAverages(packet, 1),
                new String[]{"12 7.0", "37 44.0", "46 1.0", "63 -4.0", "66 46.0"}));
        check(Arrays.equals(StringTasks.calculateSensorAverages(packet, 2),
                new String[]{"63 -4.0", "46 1.0", "12 7.0", "37 44.0", "66 46.0"}));
        check(Arrays.equals(StringTasks.calculateSensorAverages("0050@00-50@015@017", 1),
                new String[]{"00 0.0", "01 6.0"}));
        check(Arrays.equals(StringTasks.calculateSensorAverages("021@011", 2),
                new String[]{"01 1.0", "02 1.0"}));
        String boundary = "000@".repeat(126) + "000@0010";
        check(boundary.length() == 512);
        check(StringTasks.calculateSensorAverages(boundary, 1).length == 1);
        rejects(() -> StringTasks.calculateSensorAverages(boundary + "0", 1));
        rejects(() -> StringTasks.calculateSensorAverages("", 1));
        rejects(() -> StringTasks.calculateSensorAverages("0051", 1));
        rejects(() -> StringTasks.calculateSensorAverages("00-51", 1));
        rejects(() -> StringTasks.calculateSensorAverages("001@", 1));
        rejects(() -> StringTasks.calculateSensorAverages("@001", 1));
        rejects(() -> StringTasks.calculateSensorAverages("001@@021", 1));
        rejects(() -> StringTasks.calculateSensorAverages("0a1", 1));
        rejects(() -> StringTasks.calculateSensorAverages("001", 3));

        String[] goodEmails = {"user@example.com", "first.last+tag@sub.example.org", "a@a.ru"};
        String[] badEmails = {"", "user", "a..b@example.com", ".a@example.com", "a.@example.com",
                "a@-example.com", "a@example-.com", "a@exam_ple.com", "a@b.c", "a@@b.com",
                "a@example.com\n", "a".repeat(65) + "@example.com"};
        for (String email : goodEmails) {
            check(StringTasks.isEmail(email));
        }
        for (String email : badEmails) {
            check(!StringTasks.isEmail(email));
        }
        check(StringTasks.isFullIpv6("2001:0db8:0000:0000:0000:ff00:0042:8329"));
        check(StringTasks.isFullIpv6("FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF"));
        check(!StringTasks.isFullIpv6("2001:db8::1"));
        check(!StringTasks.isFullIpv6("2001:db8:0000:0000:0000:ff00:0042:8329"));
        check(!StringTasks.isFullIpv6("gggg:0000:0000:0000:0000:0000:0000:0000"));
        check(!StringTasks.isFullIpv6("0000:0000:0000:0000:0000:0000:0000"));
        check(StringTasks.removeStuckKeys("ассимиииляццццция").equals("ассимиляция"));
        check(StringTasks.removeStuckKeys("1111").equals("1"));
        check(StringTasks.removeStuckKeys("@@@").equals("@"));
        check(StringTasks.removeStuckKeys("aabb").equals("aabb"));
        check(StringTasks.removeStuckKeys("😀😀😀").equals("😀"));
        check(StringTasks.removeStuckKeys("\n\n\n").equals("\n"));
        check(StringTasks.removeStuckKeys("").isEmpty());
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
