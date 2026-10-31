import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Student {
    private String name;
    private LocalDateTime birthDate;
    private String group;
    private int studentId;
    private double averageScore;

    public Student(String name, LocalDateTime birthDate, String group, int studentId, double averageScore) {
        setName(name);
        setBirthDate(birthDate);
        setGroup(group);
        setStudentId(studentId);
        setAverageScore(averageScore);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не должно быть пустым");
        }
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDateTime.now(ZoneId.of("Europe/Moscow")))) {
            throw new IllegalArgumentException("Некорректная дата рождения");
        }
        this.birthDate = birthDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        if (group == null || group.isBlank()) {
            throw new IllegalArgumentException("Группа не должна быть пустой");
        }
        this.group = group;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Код студента должен быть положительным");
        }
        this.studentId = studentId;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        if (!Double.isFinite(averageScore) || averageScore < 0 || averageScore > 5) {
            throw new IllegalArgumentException("Средний балл должен быть от 0 до 5");
        }
        this.averageScore = averageScore;
    }

    public int getAge() {
        return Period.between(birthDate.toLocalDate(), LocalDate.now(ZoneId.of("Europe/Moscow"))).getYears();
    }

    public boolean isExcellentStudent() {
        return averageScore >= 4.8;
    }

    @Override
    public String toString() {
        return name + ", дата рождения: " + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                + ", группа: " + group + ", код: " + studentId + ", средний балл: " + averageScore
                + ", возраст: " + getAge() + ", отличник: " + isExcellentStudent();
    }
}
