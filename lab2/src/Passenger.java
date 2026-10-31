public final class Passenger {
    private String name;

    public Passenger(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя пассажира не должно быть пустым");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
