package com.testexercise.hauteloockcdn.model;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Products implements Serializable
{
    private String division;
    private String department;
    private String brand;
    private String name;
    private String images;

    public Products() {
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Products(String division, String department, String brand, String name, String images) {
        this.division = division;
        this.department = department;
        this.brand = brand;
        this.name = name;
        this.images = images;
    }
}
