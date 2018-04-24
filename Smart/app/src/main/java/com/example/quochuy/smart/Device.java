package com.example.quochuy.smart;

/**
 * Created by Quoc Huy on 4/21/2018.
 */

public class Device {
    private String key;
    private Boolean isTurn;
    private String nameDevice;
    private int usage=0;
    private String key_usage;

    public String getKey_usage() {
        return key_usage;
    }

    public void setKey_usage(String key_usage) {
        this.key_usage = key_usage;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public Device(String key, Boolean isTurn, String nameDevice, String key_usage) {
        this.key = key;
        this.isTurn = isTurn;
        this.nameDevice = nameDevice;
        this.key_usage=key_usage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getTurn() {
        return isTurn;
    }

    public void setTurn(Boolean turn) {
        isTurn = turn;
//        this.nameDevice=this.nameDevice+"("+turn.toString()+")";
    }

    public String getNameDevice() {
        return nameDevice + "(" + isTurn.toString()+")";
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }
}
