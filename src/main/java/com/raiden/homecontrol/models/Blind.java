package com.raiden.homecontrol.models;

public class Blind {
    private final int positionOpen;
    private final int bitOpen;
    private final int positionClose;
    private final int bitClose;
    private final String id;
    private boolean isAvailable = true;

    public Blind(int positionOpen, int bitOpen, int positionClose, int bitClose, String id) {
        this.positionOpen = positionOpen;
        this.bitOpen = bitOpen;
        this.positionClose = positionClose;
        this.bitClose = bitClose;
        this.id = id;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPositionOpen() {
        return positionOpen;
    }

    public int getBitOpen() {
        return bitOpen;
    }

    public int getPositionClose() {
        return positionClose;
    }

    public int getBitClose() {
        return bitClose;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
