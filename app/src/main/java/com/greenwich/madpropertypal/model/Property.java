package com.greenwich.madpropertypal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Property implements Parcelable, Serializable {

    @PrimaryKey
    private int id;
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

    public Property(int id, String name, String number, String type, String leaseType, int size, String street, String postcode, String city, int bedroomCount, int bathroomCount, double askingPrice, List<String> localAmenities, String description) {
        this.id = id;
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

    protected Property(Parcel in) {
        id = in.readInt();
        name = in.readString();
        number = in.readString();
        type = in.readString();
        leaseType = in.readString();
        size = in.readInt();
        street = in.readString();
        postcode = in.readString();
        city = in.readString();
        bedroomCount = in.readInt();
        bathroomCount = in.readInt();
        askingPrice = in.readDouble();
        localAmenities = in.createStringArrayList();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(type);
        dest.writeString(leaseType);
        dest.writeInt(size);
        dest.writeString(street);
        dest.writeString(postcode);
        dest.writeString(city);
        dest.writeInt(bedroomCount);
        dest.writeInt(bathroomCount);
        dest.writeDouble(askingPrice);
        dest.writeStringList(localAmenities);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

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


    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", leaseType='" + leaseType + '\'' +
                ", size=" + size +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", bedroomCount=" + bedroomCount +
                ", bathroomCount=" + bathroomCount +
                ", askingPrice=" + askingPrice +
                ", localAmenities=" + localAmenities +
                ", description='" + description + '\'' +
                '}';
    }


}
