package com.ilkaydurgun.retrofitjava.Model;

import com.google.gson.annotations.SerializedName;

public class CriptoModel {

    @SerializedName("symbol")
    String symbol;
    @SerializedName("price")
    String price;
}
