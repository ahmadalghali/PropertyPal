package com.greenwich.madpropertypal.web;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    private String uploadResponseCode;
    private int number;
    private String message;
    @SerializedName("userid")
    private String userId;
    private String names;

    public ApiResponse(String uploadResponseCode, String userId, String names, int number, String message) {
        this.uploadResponseCode = uploadResponseCode;
        this.number = number;
        this.message = message;
        this.userId = userId;
        this.names = names;
    }

    public String getUploadResponseCode() {
        return uploadResponseCode;
    }

    public void setUploadResponseCode(String uploadResponseCode) {
        this.uploadResponseCode = uploadResponseCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "uploadResponseCode='" + uploadResponseCode + '\'' +
                ", number=" + number +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", names='" + names + '\'' +
                '}';
    }
}
