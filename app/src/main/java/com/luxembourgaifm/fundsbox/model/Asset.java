package com.luxembourgaifm.fundsbox.model;

public class Asset {

    private String name;
    private String amount;


    public Asset(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
