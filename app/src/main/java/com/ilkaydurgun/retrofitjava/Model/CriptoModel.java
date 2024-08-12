package com.ilkaydurgun.retrofitjava.Model;

import com.google.gson.annotations.SerializedName;

public class CriptoModel {

    @SerializedName("currency")
    public String symbol;
    @SerializedName("price")
    public  String price;
}
