package org.zeyes.bean;

import java.util.Date;
import java.util.List;

public class HeartPacket {
    private Date serverTime;
    private int heart;
    private double gold;
    private double maxGold;
    private double addGold;
    private double dps;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getMaxGold() {
        return maxGold;
    }

    public void setMaxGold(double maxGold) {
        this.maxGold = maxGold;
    }

    public double getAddGold() {
        return addGold;
    }

    public void setAddGold(double addGold) {
        this.addGold = addGold;
    }

    public double getDps() {
        return dps;
    }

    public void setDps(double dps) {
        this.dps = dps;
    }

    @Override
    public String toString() {
        return "HeartPacket{" +
                "serverTime=" + serverTime +
                ", heart=" + heart +
                ", gold=" + gold +
                ", maxGold=" + maxGold +
                ", addGold=" + addGold +
                ", dps=" + dps +
                ", error='" + error + '\'' +
                '}';
    }
}

//SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

//{"serverTime":1525361148,"viewUsers":[],"heart":7,"gold":"1.5917805499845459e28","maxGold":"6.02119904751025e29","addGold":"3.290873459835757e25","dps":"4.8395041683918e24"}