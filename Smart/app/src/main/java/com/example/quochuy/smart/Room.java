package com.example.quochuy.smart;

/**
 * Created by Quoc Huy on 4/21/2018.
 */

public class Room {
    private String nameRoom;
    private String power;
    private int timeUsed;

    public Room(String nameRoom, String power, int timeUsed) {
        this.nameRoom = nameRoom;
        this.power = power;
        this.timeUsed = timeUsed;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }
}
