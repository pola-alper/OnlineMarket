package com.alper.pola.andoid.onlinemarket.Model.Model2;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    private List<String> images = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("large_images")
    @Expose
    private List<String> largeImages = null;

    private Integer mrp;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public List<String> getImages() {
        return images;
    }



    public Integer getMrp() {
        return mrp;
    }




}
