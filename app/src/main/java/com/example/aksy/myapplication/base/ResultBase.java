package com.example.aksy.myapplication.base;

public class ResultBase {

    private int triumph;
    private String data;

    public int getTriumph() {
        return triumph;
    }

    public void setTriumph(int triumph) {
        this.triumph = triumph;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBase{" +
                "triumph=" + triumph +
                ", data='" + data + '\'' +
                '}';
    }
}
