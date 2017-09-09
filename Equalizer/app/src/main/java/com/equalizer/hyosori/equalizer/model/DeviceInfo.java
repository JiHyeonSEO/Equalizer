
/**
 * Created by samsung on 2017-09-09.
 */

public class deviceInfo {
    String model;
    int freq1_mean;
    int freq2_mean;
    int freq3_mean;
    int freq4_mean;

    public  deviceInfo()
    {

    }

    public deviceInfo(String model, int freq1, int freq2, int freq3, int freq4)
    {
        this.model = model;
        this.freq1_mean = freq1;
        this.freq2_mean = freq2;
        this.freq3_mean = freq3;
        this.freq4_mean = freq4;
    }
}
