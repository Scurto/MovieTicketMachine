package enums;

public enum TicketType {
    ADULT(20.00),
    SENIOR(8.00);

    TicketType(double price) {
        this.price = price;
    }

    private double price;

    public double getPrice() {
        return price;
    }
}
