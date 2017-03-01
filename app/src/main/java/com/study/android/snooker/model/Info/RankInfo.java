package com.study.android.snooker.model.Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RankInfo extends RealmObject{

    private static final String ID = "ID";
    private static final String POSITION = "Position";
    private static final String PLAYER_ID = "PlayerID";
    private static final String SUM = "Sum";

    @SerializedName(ID)
    @Expose
    @PrimaryKey
    private Integer iD;
    @SerializedName(POSITION)
    @Expose
    private Integer position;
    @SerializedName(PLAYER_ID)
    @Expose
    private Integer playerID;
    @SerializedName(SUM)
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

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankInfo rankInfo = (RankInfo) o;

        return iD != null ? iD.equals(rankInfo.iD) : rankInfo.iD == null;
    }

    @Override
    public int hashCode() {
        return iD != null ? iD.hashCode() : 0;
    }
}
