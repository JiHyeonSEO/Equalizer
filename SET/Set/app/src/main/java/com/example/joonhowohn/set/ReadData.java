package com.example.joonhowohn.set;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by JiHyeonSEO on 2017-07-28.
 */

public class ReadData {
    public ReadData() {
        //File dir = Environment.getExternalStorageDirectory();

        File file = new File("sdcard/data.json");
        ArrayList<deviceInfo> earphoenInfoList = new ArrayList<deviceInfo>();;
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
                    deviceInfo dvinfo = new deviceInfo();

                    dvinfo.model  = earphoneInfo.getString("model");
                    dvinfo.freq1_mean = earphoneInfo.getInt("freq1_mean");
                    dvinfo.freq2_mean = earphoneInfo.getInt("freq2_mean");
                    dvinfo.freq3_mean = earphoneInfo.getInt("freq3_mean");
                    dvinfo.freq4_mean = earphoneInfo.getInt("freq4_mean");

                    //ArrayList<deviceInfo> deviceInfoList = new ArrayList<deviceInfo>();
                    //earphoenInfoList = new ArrayList<JSONObject>();
                    earphoenInfoList.add(dvinfo);
                }

                for(int i = 0; i < freqArr.length(); i++)
                {
                    Log.i("earphone1 : ", earphoenInfoList.get(i).toString());
                }
            }catch (JSONException je){
                Log.e("jsonErr", "json 에러", je);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.i("error : ", "파일 없음");
        }
    }
}
