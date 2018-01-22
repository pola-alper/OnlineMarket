
package com.alper.pola.andoid.onlinemarket.Model.Model2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("food_coupons_allowed")
    @Expose
    private String foodCouponsAllowed;
    @SerializedName("parent_category")
    @Expose
    private ParentCategory parentCategory;
    @SerializedName("products_count")
    @Expose
    private String productsCount;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFoodCouponsAllowed() {
        return foodCouponsAllowed;
    }

    public void setFoodCouponsAllowed(String foodCouponsAllowed) {
        this.foodCouponsAllowed = foodCouponsAllowed;
    }

    public ParentCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(String productsCount) {
        this.productsCount = productsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
