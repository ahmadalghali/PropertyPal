package com.greenwich.madpropertypal.web;

import com.greenwich.madpropertypal.model.Property;

import java.util.List;

public class ApiRequest {

    private String userId;
    private List<Property> detailList;

    public ApiRequest(String userId, List<Property> detailList) {
        this.userId = userId;
        this.detailList = detailList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Property> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Property> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "JsonPayLoad{" +
                "userId='" + userId + '\'' +
                ", detailList=" + detailList +
                '}';
    }
}
