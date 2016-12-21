package com.study.android.snooker.model.Info;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerInfo {
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("TeamName")
    @Expose
    private String teamName;
    @SerializedName("TeamNumber")
    @Expose
    private Integer teamNumber;
    @SerializedName("TeamSeason")
    @Expose
    private Integer teamSeason;
    @SerializedName("ShortName")
    @Expose
    private String shortName;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Sex")
    @Expose
    private String sex;
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
    @SerializedName("License")
    @Expose
    private String license;
    @SerializedName("Club")
    @Expose
    private String club;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Info")
    @Expose
    private String info;

    private String name = firstName + " " + middleName + " " + lastName;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Integer getTeamSeason() {
        return teamSeason;
    }

    public void setTeamSeason(Integer teamSeason) {
        this.teamSeason = teamSeason;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBioPage() {
        return bioPage;
    }

    public void setBioPage(String bioPage) {
        this.bioPage = bioPage;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Boolean getSurnameFirst() {
        return surnameFirst;
    }

    public void setSurnameFirst(Boolean surnameFirst) {
        this.surnameFirst = surnameFirst;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }
}
