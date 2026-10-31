public final class Octagon {
    private double innerRadius;

    public Octagon(double innerRadius) {
        setInnerRadius(innerRadius);
    }

    public double getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(double innerRadius) {
        if (!Double.isFinite(innerRadius) || innerRadius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным конечным числом");
        }
        this.innerRadius = innerRadius;
    }

    public double calculateArea() {
        return calculatePerimeter() * innerRadius / 2;
    }

    public double calculatePerimeter() {
        return 16 * innerRadius * Math.tan(Math.PI / 8);
    }
}
