package net.com.gopal.ecommerceapp.models;

public class Product {
    private String Name;
    private  String Image;
    private String Status;
    private double Price, Discount;
    private int Stock, Id;


    public Product(String name, String image, String status, double price, double discount, int stock, int id) {
        Name = name;
        Image = image;
        Status = status;
        Price = price;
        Discount = discount;
        Stock = stock;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
