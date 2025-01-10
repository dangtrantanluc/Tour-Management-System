package model;

public class Tour {
    protected String id;
    protected String name;
    protected String destination;
    protected double price;

    public Tour(String id, String name, String destination, double price) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.price = price;
    }



    // Getter và Setter cho các thuộc tính
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tour ID: " + id + ", Name: " + name + " (" + destination + ") - $" + price;
    }
}
