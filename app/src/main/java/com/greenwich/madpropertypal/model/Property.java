package com.greenwich.madpropertypal.model;

import java.util.List;

public class Property {

    private String name;
    private String number;
    private String type;
    private String leaseType;
    private int size;

    private String street;
    private String postcode;
    private String city;

    private int bedroomCount;
    private int bathroomCount;

    private double askingPrice;
    private List<String> localAmenities;

    private String description;

    public Property(String name, String number, String type, String leaseType, int size, String street, String postcode, String city, int bedroomCount, int bathroomCount, double askingPrice, List<String> localAmenities, String description) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.leaseType = leaseType;
        this.size = size;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.bedroomCount = bedroomCount;
        this.bathroomCount = bathroomCount;
        this.askingPrice = askingPrice;
        this.localAmenities = localAmenities;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public List<String> getLocalAmenities() {
        return localAmenities;
    }

    public void setLocalAmenities(List<String> localAmenities) {
        this.localAmenities = localAmenities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
