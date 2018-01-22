package com.alper.pola.andoid.onlinemarket.Model.Model1;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Example implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private Data data;
    private List<Data> dataList;

    public Example(Data... ms) {
        dataList = Arrays.asList(ms);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "Example{" +
                "name='" + name + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                '}';
    }

    public static class LocationsDeserializer implements JsonDeserializer<Example> {

        @Override
        public Example deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            JsonElement monumentElement = json.getAsJsonObject().get("data");
            if (monumentElement.isJsonArray()) {
                return new Example((Data[]) context.deserialize(monumentElement.getAsJsonArray(), Data[].class));
            } else if (monumentElement.isJsonObject()) {
                return new Example((Data) context.deserialize(monumentElement.getAsJsonObject(), Data.class));
            } else {
                throw new JsonParseException("Unsupported type of monument element");
            }
        }
    }
}
