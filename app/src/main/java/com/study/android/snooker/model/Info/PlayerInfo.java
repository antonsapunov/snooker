package com.study.android.snooker.model.Info;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlayerInfo extends RealmObject{

    private static final String ID = "ID";
    private static final String FIRST_NAME = "FirstName";
    private static final String MIDDLE_NAME = "MiddleName";
    private static final String LAST_NAME = "LastName";
    private static final String NATIONALITY = "Nationality";
    private static final String BIO_PAGE = "BioPage";
    private static final String BORN = "Born";
    private static final String TWITTER = "Twitter";
    private static final String SURNAME_FIRST = "SurnameFirst";
    private static final String URL = "URL";
    private static final String PHOTO = "Photo";
    @SerializedName(ID)
    @Expose
    @PrimaryKey
    private Integer iD;
    @SerializedName(FIRST_NAME)
    @Expose
    private String firstName;
    @SerializedName(MIDDLE_NAME)
    @Expose
    private String middleName;
    @SerializedName(LAST_NAME)
    @Expose
    private String lastName;
    @SerializedName(NATIONALITY)
    @Expose
    private String nationality;
    @SerializedName(BIO_PAGE)
    @Expose
    private String bioPage;
    @SerializedName(BORN)
    @Expose
    private String born;
    @SerializedName(TWITTER)
    @Expose
    private String twitter;
    @SerializedName(SURNAME_FIRST)
    @Expose
    private Boolean surnameFirst;
    @SerializedName(URL)
    @Expose
    private String uRL;
    @SerializedName(PHOTO)
    @Expose
    private String photo;

    public Integer getID() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
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

    public void setSurnameFirst(Boolean surnameFirst) {
        this.surnameFirst = surnameFirst;
    }

    public String getURL() {
        return uRL;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInfo that = (PlayerInfo) o;

        return iD != null ? iD.equals(that.iD) : that.iD == null;
    }

    @Override
    public int hashCode() {
        return iD != null ? iD.hashCode() : 0;
    }
}
