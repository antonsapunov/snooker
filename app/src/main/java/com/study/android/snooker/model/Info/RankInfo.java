package com.study.android.snooker.model.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RankInfo extends RealmObject{

    public static final String ID = "ID";

    @SerializedName(ID)
    @Expose
    @PrimaryKey
    private Integer iD;
    //TODO Use named constants instead of hardcoded strings.
    @SerializedName("Position")
    @Expose
    private Integer position;
    @SerializedName("PlayerID")
    @Expose
    private Integer playerID;
    @SerializedName("Sum")
    @Expose
    private Integer sum;

    private String name;

    public Integer getID() {
        return iD;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public Integer getSum() {
        return sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO If you working with entity, it's considered like a best practice to override equals and hashcode methods.
}
