package ru.kpfu.itis.dto;

import java.util.StringJoiner;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private double weight;
    private Manufacturer manufacturer;
    private String category;

    public ProductDto(int id, String name, double price, double weight, Manufacturer manufacturer, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public ProductDto(String name, double price, double weight, Manufacturer manufacturer, String category) {
        this(0, name, price, weight, manufacturer, category);
    }

    public ProductDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ProductDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("weight=" + weight)
                .add("manufacturer=" + manufacturer)
                .add("category='" + category + "'")
                .toString();
    }
}
