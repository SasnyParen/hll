import java.util.Objects;

public class Car {
    private String model;
    private Engine engine;
    private Passenger passenger1;
    private Passenger passenger2;

    public Car(String model, Engine engine) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Модель не должна быть пустой");
        }
        this.model = model;
        this.engine = Objects.requireNonNull(engine, "Двигатель не указан");
    }

    public boolean addPassenger(Passenger passenger) {
        Objects.requireNonNull(passenger, "Пассажир не указан");
        if (passenger == passenger1 || passenger == passenger2) {
            return false;
        }
        if (passenger1 == null) {
            passenger1 = passenger;
            return true;
        }
        if (passenger2 == null) {
            passenger2 = passenger;
            return true;
        }
        return false;
    }

    public boolean removePassenger(Passenger passenger) {
        if (passenger == null) {
            return false;
        }
        if (passenger == passenger1) {
            passenger1 = null;
            return true;
        }
        if (passenger == passenger2) {
            passenger2 = null;
            return true;
        }
        return false;
    }

    public void displayInfo() {
        System.out.println(model + ", " + engine.getPower() + " л.с., пассажиры: "
                + (passenger1 == null ? "—" : passenger1.getName()) + ", "
                + (passenger2 == null ? "—" : passenger2.getName()));
    }
}
