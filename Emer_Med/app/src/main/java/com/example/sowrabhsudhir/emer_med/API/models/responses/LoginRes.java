package com.example.sowrabhsudhir.emer_med.API.models.responses;

import com.google.gson.annotations.SerializedName;

public class LoginRes {

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private DetailsRes details;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public DetailsRes getDetails() {
        return details;
    }

}