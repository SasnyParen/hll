public final class Engine {
    private int power;

    public Engine(int power) {
        setPower(power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power <= 0) {
            throw new IllegalArgumentException("Мощность должна быть положительной");
        }
        this.power = power;
    }
}
