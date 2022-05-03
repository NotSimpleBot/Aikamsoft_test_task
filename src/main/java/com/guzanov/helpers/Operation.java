package com.guzanov.helpers;

public enum Operation {
    SEARCH("search"),
    STAT("stat"),
    ERROR("error");


    private String stat;

    Operation(String stat) {
        this.stat = stat;
    }

    public String getStat() {
        return stat;
    }
}
