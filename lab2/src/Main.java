import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Product notebook = new Product();
        notebook.setName("Тетрадь");
        notebook.setPrice(60);
        notebook.setQuantity(10);
        Product pen = new Product("Ручка", 35, 20);
        System.out.println(notebook);
        System.out.println(pen);

        Octagon octagon = new Octagon(5);
        System.out.printf(Locale.ROOT, "Восьмиугольник: S = %.4f, P = %.4f%n",
                octagon.calculateArea(), octagon.calculatePerimeter());

        Student ivan = new Student("Иван", LocalDateTime.of(2006, 5, 15, 10, 30), "ИС-21", 101, 4.8);
        Student anna = new Student("Анна", LocalDateTime.of(2005, 12, 10, 8, 0), "ИС-21", 102, 4.5);
        System.out.println(ivan);
        System.out.println(anna);
        anna.setAverageScore(4.9);
        System.out.println("Анна после изменения балла: отличник = " + anna.isExcellentStudent());

        BankAccount account = new BankAccount("10001");
        account.deposit(1000);
        System.out.println("Снятие 300: " + account.withdraw(300));
        System.out.println("Снятие 1000: " + account.withdraw(1000));
        System.out.println("Счёт " + account.getAccountNumber() + ", баланс: " + account.getBalance());

        Author author = new Author("Александр Пушкин", 1799);
        Book book = new Book("Капитанская дочка", author);
        System.out.println(book);
        book.setAuthor(new Author("Михаил Лермонтов", 1814));
        book.setTitle("Герой нашего времени");
        System.out.println(book.getTitle() + ": " + book.getAuthor());

        Car car = new Car("Lada Vesta", new Engine(106));
        Passenger first = new Passenger("Иван");
        Passenger second = new Passenger("Анна");
        Passenger third = new Passenger("Олег");
        car.addPassenger(first);
        car.addPassenger(second);
        car.displayInfo();
        System.out.println("Добавление третьего пассажира: " + car.addPassenger(third));
        car.removePassenger(first);
        car.addPassenger(third);
        car.displayInfo();
    }
}
