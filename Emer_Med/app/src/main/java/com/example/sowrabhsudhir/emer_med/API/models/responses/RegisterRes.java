package com.example.sowrabhsudhir.emer_med.API.models.responses;

import com.google.gson.annotations.SerializedName;

public class RegisterRes {

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}