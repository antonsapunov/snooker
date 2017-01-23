package com.study.android.snooker.model.Info;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlayerInfo extends RealmObject{

    @SerializedName("ID")
    @Expose
    @PrimaryKey
    private Integer iD;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("BioPage")
    @Expose
    private String bioPage;
    @SerializedName("Born")
    @Expose
    private String born;
    @SerializedName("Twitter")
    @Expose
    private String twitter;
    @SerializedName("SurnameFirst")
    @Expose
    private Boolean surnameFirst;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("Photo")
    @Expose
    private String photo;

    public Integer getID() {
        return iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBioPage() {
        return bioPage;
    }

    public String getBorn() {
        return born;
    }

    public String getTwitter() {
        return twitter;
    }

    public Boolean getSurnameFirst() {
        return surnameFirst;
    }

    public String getURL() {
        return uRL;
    }

    public String getPhoto() {
        return photo;
    }
}
