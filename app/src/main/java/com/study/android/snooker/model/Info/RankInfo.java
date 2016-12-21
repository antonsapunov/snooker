package com.study.android.snooker.model.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankInfo {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Position")
    @Expose
    private Integer position;
    @SerializedName("PlayerID")
    @Expose
    private Integer playerID;
    @SerializedName("Season")
    @Expose
    private Integer season;
    @SerializedName("Sum")
    @Expose
    private Integer sum;
    @SerializedName("Type")
    @Expose
    private String type;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
