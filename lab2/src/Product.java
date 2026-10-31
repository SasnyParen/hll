public final class Product {
    private String name;
    private double price;
    private int quantity;

    public Product() {
        this("Товар", 0, 0);
    }

    public Product(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не должно быть пустым");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Некорректная цена");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + ": цена = " + price + ", количество = " + quantity;
    }
}
