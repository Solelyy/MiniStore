package ministore;

public class Product {
    private String name;
    private double price;

    Product (String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("%s - ₱%.2f", name, price);
    }

    public String getName () { return name;}

    public double getPrice() {return price;}
}