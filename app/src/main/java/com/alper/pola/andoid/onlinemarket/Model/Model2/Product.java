
package com.alper.pola.andoid.onlinemarket.Model.Model2;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("properties")
    @Expose
    private List<Object> properties = null;
    @SerializedName("is_new")
    @Expose
    private Boolean isNew;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("variants")
    @Expose
    private List<Variant> variants = null;
    @SerializedName("last_bought_on")
    @Expose
    private Object lastBoughtOn;
    @SerializedName("replacementCount")
    @Expose
    private Integer replacementCount;
    @SerializedName("defaultVariant")
    @Expose
    private DefaultVariant defaultVariant;
    @SerializedName("availableFromTime")
    @Expose
    private Object availableFromTime;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Object> getProperties() {
        return properties;
    }

    public void setProperties(List<Object> properties) {
        this.properties = properties;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Object getLastBoughtOn() {
        return lastBoughtOn;
    }

    public void setLastBoughtOn(Object lastBoughtOn) {
        this.lastBoughtOn = lastBoughtOn;
    }

    public Integer getReplacementCount() {
        return replacementCount;
    }

    public void setReplacementCount(Integer replacementCount) {
        this.replacementCount = replacementCount;
    }

    public DefaultVariant getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(DefaultVariant defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public Object getAvailableFromTime() {
        return availableFromTime;
    }

    public void setAvailableFromTime(Object availableFromTime) {
        this.availableFromTime = availableFromTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", images=" + images +
                ", brand=" + brand +
                ", category=" + category +
                ", properties=" + properties +
                ", isNew=" + isNew +
                ", status='" + status + '\'' +
                ", variants=" + variants +
                ", lastBoughtOn=" + lastBoughtOn +
                ", replacementCount=" + replacementCount +
                ", defaultVariant=" + defaultVariant +
                ", availableFromTime=" + availableFromTime +
                '}';
    }
}
