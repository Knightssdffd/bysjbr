package com.example.aksy.myapplication.base;

public class CommodityBase {
    public CommodityBase(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    private String name;
    private int imageId;


    @Override
    public String toString() {
        return "CommodityBase{" +
                "name='" + name + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
