import java.time.LocalDate;
import java.time.ZoneId;

public class Lab2Test {
    public static void main(String[] args) {
        Octagon octagon = new Octagon(1);
        check(Math.abs(octagon.calculateArea() - 3.313708498984761) < 1e-12);
        check(Math.abs(octagon.calculatePerimeter() - 6.627416997969522) < 1e-12);
        octagon.setInnerRadius(2);
        check(Math.abs(octagon.calculateArea() - 13.254833995939044) < 1e-12);
        rejects(() -> octagon.setInnerRadius(0));
        rejects(() -> octagon.setInnerRadius(Double.NaN));

        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        Student student = new Student("Иван", today.minusYears(20).atStartOfDay(), "ИС-21", 1, 4.8);
        check(student.getAge() == 20);
        check(student.isExcellentStudent());
        student.setBirthDate(today.minusYears(20).plusDays(1).atStartOfDay());
        check(student.getAge() == 19);
        student.setBirthDate(today.minusYears(20).minusDays(1).atStartOfDay());
        check(student.getAge() == 20);
        student.setAverageScore(4.79);
        check(!student.isExcellentStudent());
        rejects(() -> student.setBirthDate(today.plusDays(1).atStartOfDay()));
        rejects(() -> student.setAverageScore(Double.NaN));

        BankAccount account = new BankAccount("1");
        account.deposit(1000);
        check(account.withdraw(300));
        check(!account.withdraw(701));
        check(account.getBalance() == 700);
        rejects(() -> account.deposit(-10));
        rejects(() -> account.withdraw(Double.NaN));
        check(account.getBalance() == 700);
        check(account.withdraw(700));
        check(account.getBalance() == 0);
        account.deposit(Double.MAX_VALUE);
        rejects(() -> account.deposit(Double.MAX_VALUE));
        check(account.getBalance() == Double.MAX_VALUE);

        Author author = new Author("Автор", 1900);
        Book book = new Book("Книга", author);
        author.setName("Новое имя");
        check(book.getAuthor().getName().equals("Новое имя"));
        Author other = new Author("Другой автор", 1950);
        book.setAuthor(other);
        check(book.getAuthor() == other);

        Car car = new Car("Модель", new Engine(100));
        Passenger first = new Passenger("Первый");
        Passenger second = new Passenger("Второй");
        Passenger third = new Passenger("Третий");
        check(car.addPassenger(first));
        check(!car.addPassenger(first));
        check(car.addPassenger(second));
        check(!car.addPassenger(third));
        check(!car.removePassenger(third));
        check(car.removePassenger(first));
        check(!car.removePassenger(first));
        check(car.addPassenger(third));
        check(car.removePassenger(second));
        check(car.addPassenger(first));
        check(!car.removePassenger(null));
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
