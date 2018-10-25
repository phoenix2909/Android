package com.example.sowrabhsudhir.emer_med.API.models.responses;

import com.google.gson.annotations.SerializedName;

public class DetailsRes {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("emergency")
    private String emergency;

    @SerializedName("age")
    private String age;

    @SerializedName("bloodgroup")
    private String bloodgroup;

    @SerializedName("bloodpressure")
    private String bloodpressure;

    @SerializedName("diseases")
    private String diseases;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmergency() {
        return emergency;
    }

    public String getAge() {
        return age;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getBloodpressure() {
        return bloodpressure;
    }

    public String getDiseases() {
        return diseases;
    }

}
