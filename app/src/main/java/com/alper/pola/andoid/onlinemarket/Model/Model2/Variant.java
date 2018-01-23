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
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("product")
    @Expose
    private Product_ product;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("large_images")
    @Expose
    private List<String> largeImages = null;
    @SerializedName("is_problematic")
    @Expose
    private Boolean isProblematic;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("mrp")
    @Expose
    private Integer mrp;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("offer")
    @Expose
    private Boolean offer;
    @SerializedName("offer_details")
    @Expose
    private List<Object> offerDetails = null;
    @SerializedName("special_offer_price")
    @Expose
    private String specialOfferPrice;

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

    public Product_ getProduct() {
        return product;
    }

    public void setProduct(Product_ product) {
        this.product = product;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getLargeImages() {
        return largeImages;
    }

    public void setLargeImages(List<String> largeImages) {
        this.largeImages = largeImages;
    }

    public Boolean getIsProblematic() {
        return isProblematic;
    }

    public void setIsProblematic(Boolean isProblematic) {
        this.isProblematic = isProblematic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    public List<Object> getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(List<Object> offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getSpecialOfferPrice() {
        return specialOfferPrice;
    }

    public void setSpecialOfferPrice(String specialOfferPrice) {
        this.specialOfferPrice = specialOfferPrice;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", product=" + product +
                ", properties=" + properties +
                ", images=" + images +
                ", url='" + url + '\'' +
                ", largeImages=" + largeImages +
                ", isProblematic=" + isProblematic +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                ", mrp=" + mrp +
                ", discount=" + discount +
                ", stock='" + stock + '\'' +
                ", offer=" + offer +
                ", offerDetails=" + offerDetails +
                ", specialOfferPrice='" + specialOfferPrice + '\'' +
                '}';
    }
}
