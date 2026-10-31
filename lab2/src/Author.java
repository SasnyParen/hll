public final class Author {
    private String name;
    private int birthYear;

    public Author(String name, int birthYear) {
        setName(name);
        setBirthYear(birthYear);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя автора не должно быть пустым");
        }
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return name + " (" + birthYear + ")";
    }
}
