package ru.kpfu.itis.dto;

public enum Manufacturer {

    RUSSIA("Russia"), USA("USA"), CHINA("China"), USSR("USSR"), GERMANY("Germany");

    private String country;

    Manufacturer(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
