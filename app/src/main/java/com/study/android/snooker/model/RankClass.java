package com.study.android.snooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankClass {

    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("PlayerID")
    @Expose
    private String playerID;
    @SerializedName("Sum")
    @Expose
    private String sum;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
