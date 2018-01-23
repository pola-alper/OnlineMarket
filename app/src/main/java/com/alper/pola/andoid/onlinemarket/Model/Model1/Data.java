package com.alper.pola.andoid.onlinemarket.Model.Model1;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categories")
    @Expose
    private List<Category> categories;
    @SerializedName("min_img_width")
    @Expose
    private String minImgWidth;
    @SerializedName("images")
    @Expose
    private List<Image> images;
    @SerializedName("cart")
    @Expose
    private List<Object> cart;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("youPay")
    @Expose
    private Integer youPay;
    @SerializedName("savings")
    @Expose
    private Integer savings;
    @SerializedName("zoppie_offer")
    @Expose
    private Object zoppieOffer;
    @SerializedName("user_zoppies")
    @Expose
    private Integer userZoppies;
    @SerializedName("user_logged_in")
    @Expose
    private Boolean userLoggedIn;
    @SerializedName("changes")
    @Expose
    private List<Object> changes;
    @SerializedName("offers")
    @Expose
    private List<Object> offers;
    @SerializedName("variant_offers")
    @Expose
    private List<Object> variantOffers;
    @SerializedName("potential_offers")
    @Expose
    private List<Object> potentialOffers;
    @SerializedName("hash")
    @Expose
    private String hash;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getMinImgWidth() {
        return minImgWidth;
    }

    public void setMinImgWidth(String minImgWidth) {
        this.minImgWidth = minImgWidth;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Object> getCart() {
        return cart;
    }

    public void setCart(List<Object> cart) {
        this.cart = cart;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getYouPay() {
        return youPay;
    }

    public void setYouPay(Integer youPay) {
        this.youPay = youPay;
    }

    public Integer getSavings() {
        return savings;
    }

    public void setSavings(Integer savings) {
        this.savings = savings;
    }

    public Object getZoppieOffer() {
        return zoppieOffer;
    }

    public void setZoppieOffer(Object zoppieOffer) {
        this.zoppieOffer = zoppieOffer;
    }

    public Integer getUserZoppies() {
        return userZoppies;
    }

    public void setUserZoppies(Integer userZoppies) {
        this.userZoppies = userZoppies;
    }

    public Boolean getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(Boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public List<Object> getChanges() {
        return changes;
    }

    public void setChanges(List<Object> changes) {
        this.changes = changes;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public List<Object> getVariantOffers() {
        return variantOffers;
    }

    public void setVariantOffers(List<Object> variantOffers) {
        this.variantOffers = variantOffers;
    }

    public List<Object> getPotentialOffers() {
        return potentialOffers;
    }

    public void setPotentialOffers(List<Object> potentialOffers) {
        this.potentialOffers = potentialOffers;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Data{" +
                "message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", categories=" + categories +
                ", minImgWidth='" + minImgWidth + '\'' +
                ", images=" + images +
                ", cart=" + cart +
                ", currency='" + currency + '\'' +
                ", youPay=" + youPay +
                ", savings=" + savings +
                ", zoppieOffer=" + zoppieOffer +
                ", userZoppies=" + userZoppies +
                ", userLoggedIn=" + userLoggedIn +
                ", changes=" + changes +
                ", offers=" + offers +
                ", variantOffers=" + variantOffers +
                ", potentialOffers=" + potentialOffers +
                ", hash='" + hash + '\'' +
                '}';
    }
}
