package com.greenwich.madpropertypal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;

@Entity
public class Report implements Parcelable, Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private Date viewingDate;
    private String interest;
    private Double offerPrice;
    private Date offerExpiryDate;
    private String conditionsOfOffer;
    private String viewingComments;
    private int propertyId;


    public Report(Date viewingDate, String interest, Double offerPrice, Date offerExpiryDate, String conditionsOfOffer, String viewingComments, int propertyId) {
        this.viewingDate = viewingDate;
        this.interest = interest;
        this.offerPrice = offerPrice;
        this.offerExpiryDate = offerExpiryDate;
        this.conditionsOfOffer = conditionsOfOffer;
        this.viewingComments = viewingComments;
        this.propertyId = propertyId;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setViewingDate(Date viewingDate) {
        this.viewingDate = viewingDate;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Date getViewingDate() {
        return viewingDate;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Date getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(Date offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

    public String getConditionsOfOffer() {
        return conditionsOfOffer;
    }

    public void setConditionsOfOffer(String conditionsOfOffer) {
        this.conditionsOfOffer = conditionsOfOffer;
    }

    public String getViewingComments() {
        return viewingComments;
    }

    public void setViewingComments(String viewingComments) {
        this.viewingComments = viewingComments;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", date=" + viewingDate +
                ", interest='" + interest + '\'' +
                ", offerPrice=" + offerPrice +
                ", offerExpiryDate=" + offerExpiryDate +
                ", conditionsOfOffer='" + conditionsOfOffer + '\'' +
                ", viewingComments='" + viewingComments + '\'' +
                '}';
    }

    protected Report(Parcel in) {
        id = in.readInt();
        interest = in.readString();
        if (in.readByte() == 0) {
            offerPrice = null;
        } else {
            offerPrice = in.readDouble();
        }
        conditionsOfOffer = in.readString();
        viewingComments = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(interest);
        if (offerPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(offerPrice);
        }
        dest.writeString(conditionsOfOffer);
        dest.writeString(viewingComments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };


}
