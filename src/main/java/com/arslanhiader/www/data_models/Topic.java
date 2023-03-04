package com.arslanhiader.www.data_models;

public class Topic {

    private int id;

    private String keyValue;
    private long timestamp;


    public Topic(int id, String keyValue, long timestamp) {
        this.id = id;
        this.keyValue = keyValue;
        this.timestamp = timestamp;
    }

    public int getId(){return  id;}


    public String getKeyValue() {
        return keyValue;
    }

    public long getTimestamp() {
        return timestamp;
    }


}
