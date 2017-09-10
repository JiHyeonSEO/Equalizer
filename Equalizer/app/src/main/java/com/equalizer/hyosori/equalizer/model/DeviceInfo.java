package com.equalizer.hyosori.equalizer.model;
/**
 * Created by samsung on 2017-09-09.
 */

public class DeviceInfo {
    String model;
    int freq1_mean;
    int freq2_mean;
    int freq3_mean;
    int freq4_mean;

    public  DeviceInfo()
    {

    }

    public String getModel()
    {
        return model;
    }

    public DeviceInfo(String model, int freq1, int freq2, int freq3, int freq4)
    {
        this.model = model;
        this.freq1_mean = freq1;
        this.freq2_mean = freq2;
        this.freq3_mean = freq3;
        this.freq4_mean = freq4;
    }

    @Override
    public String toString(){
        return model;
    }
}
