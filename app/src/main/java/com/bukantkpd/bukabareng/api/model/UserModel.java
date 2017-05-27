package com.bukantkpd.bukabareng.api.model;

/**
 * Created by Ibam on 5/25/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("confirmed")
    @Expose
    private Boolean confirmed;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("confirmed_phone")
    @Expose
    private Object confirmedPhone;
    @SerializedName("omnikey")
    @Expose
    private String omnikey;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getConfirmedPhone() {
        return confirmedPhone;
    }

    public void setConfirmedPhone(Object confirmedPhone) {
        this.confirmedPhone = confirmedPhone;
    }

    public String getOmnikey() {
        return omnikey;
    }

    public void setOmnikey(String omnikey) {
        this.omnikey = omnikey;
    }
}

