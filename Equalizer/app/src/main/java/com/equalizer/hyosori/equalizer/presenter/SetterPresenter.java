package com.equalizer.hyosori.equalizer.presenter;


import android.util.Log;

import com.equalizer.hyosori.equalizer.model.BandSet;
import com.equalizer.hyosori.equalizer.model.DeviceInfo;
import com.equalizer.hyosori.equalizer.model.Eq;
import com.equalizer.hyosori.equalizer.view.SetterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SetterPresenter implements Presenter {

    private SetterView view;
    private Eq model;
    private BandSet base;
    private BandSet target;

    public ArrayList<DeviceInfo> ReadData()
    {
        File file = new File("sdcard/data.json");
        ArrayList<DeviceInfo> earphoenInfoList = new ArrayList<DeviceInfo>();;
        if(file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append("\n");
                }
                // 새로운 문자열을 만들어서 내부 버퍼의 내용을 복사하고 반환
                String jString = text.toString();

                JSONObject jsonObject = new JSONObject(jString);

                // json value 값 얻기
                //String device = jsonObject.getString("device").toString();
                //System.out.println("divece:" + device);

                JSONArray freqArr = new JSONArray(jsonObject.getString("earphone"));

                for(int i = 0; i < freqArr.length(); i++){
                    JSONObject earphoneInfo = freqArr.getJSONObject(i);
                    DeviceInfo dvinfo = new DeviceInfo(earphoneInfo.getString("model"), earphoneInfo.getInt("freq1_mean"), earphoneInfo.getInt("freq2_mean"),
                            earphoneInfo.getInt("freq3_mean"), earphoneInfo.getInt("freq4_mean"));

                    //dvinfo.model  = earphoneInfo.getString("model");
                    //dvinfo.freq1_mean = earphoneInfo.getInt("freq1_mean");
                    //dvinfo.freq2_mean = earphoneInfo.getInt("freq2_mean");
                    //dvinfo.freq3_mean = earphoneInfo.getInt("freq3_mean");
                    //dvinfo.freq4_mean = earphoneInfo.getInt("freq4_mean");

                    //ArrayList<deviceInfo> deviceInfoList = new ArrayList<deviceInfo>();
                    //earphoenInfoList = new ArrayList<JSONObject>();
                    earphoenInfoList.add(dvinfo);
                }

                for(int i = 0; i < freqArr.length(); i++)
                {
                    Log.i("earphone1 : ", earphoenInfoList.get(i).toString());
                }

            return earphoenInfoList;
            }catch (JSONException je){
                Log.e("jsonErr", "json 에러", je);
                je.printStackTrace();
                return null;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            Log.i("error : ", "파일 없음");
            return null;
        }
    }

    public SetterPresenter(SetterView view) {
        this.view = view;
        this.model = new Eq();
    }

    @Override
    public void onCreate() {
        model = new Eq();
        ReadData();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onApplyBtnSelected(String baseName, String targetName) {
        this.base = new BandSet(baseName);
        this.target = new BandSet(targetName);

        this.view.setSeekBars(this.target.getBandSet());
    }

    public void onSeekBarChanged(int frequency, int amplitude) {

    }
}
