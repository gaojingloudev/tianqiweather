package com.tianqiweather.android.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.tianqiweather.android.db.City;
import com.tianqiweather.android.db.County;
import com.tianqiweather.android.db.Province;
import com.tianqiweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    @Nullable
    public static Weather handleWeatherResponse(String response) {
        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
//            String weatherContent = jsonArray.getJSONObject(0).toString();
//            return new Gson().fromJson(weatherContent, Weather.class);
            if(response == null){
                Log.d("222222", response + "为null");
            }else{
                Log.d("222222", response + "不为null");
            }
//            response = "{\"HeWeather\": [{\"basic\":{\"cid\":\"CN101190606\",\"location\":\"邗江\",\"parent_city\":\"扬州\",\"admin_area\":\"江苏\",\"cnty\":\"中国\",\"lat\":\"32.37789917\",\"lon\":\"119.39777374\",\"tz\":\"+8.00\",\"city\":\"邗江\",\"id\":\"CN101190606\",\"update\":{\"loc\":\"2018-04-23 02:47\",\"utc\":\"2018-04-22 18:47\"}},\"update\":{\"loc\":\"2018-04-23 02:47\",\"utc\":\"2018-04-22 18:47\"},\"status\":\"ok\",\"now\":{\"cloud\":\"83\",\"cond_code\":\"104\",\"cond_txt\":\"阴\",\"fl\":\"16\",\"hum\":\"98\",\"pcpn\":\"0.0\",\"pres\":\"1010\",\"tmp\":\"16\",\"vis\":\"16\",\"wind_deg\":\"8\",\"wind_dir\":\"北风\",\"wind_sc\":\"2\",\"wind_spd\":\"11\",\"cond\":{\"code\":\"104\",\"txt\":\"阴\"}},\"daily_forecast\":[{\"date\":\"2018-04-23\",\"cond\":{\"txt_d\":\"雷阵雨\"},\"tmp\":{\"max\":\"19\",\"min\":\"11\"}},{\"date\":\"2018-04-24\",\"cond\":{\"txt_d\":\"多云\"},\"tmp\":{\"max\":\"19\",\"min\":\"9\"}},{\"date\":\"2018-04-25\",\"cond\":{\"txt_d\":\"晴\"},\"tmp\":{\"max\":\"22\",\"min\":\"13\"}},{\"date\":\"2018-04-26\",\"cond\":{\"txt_d\":\"多云\"},\"tmp\":{\"max\":\"24\",\"min\":\"14\"}},{\"date\":\"2018-04-27\",\"cond\":{\"txt_d\":\"多云\"},\"tmp\":{\"max\":\"27\",\"min\":\"15\"}},{\"date\":\"2018-04-28\",\"cond\":{\"txt_d\":\"多云\"},\"tmp\":{\"max\":\"29\",\"min\":\"17\"}},{\"date\":\"2018-04-29\",\"cond\":{\"txt_d\":\"晴间多云\"},\"tmp\":{\"max\":\"29\",\"min\":\"18\"}}],\"hourly\":[{\"cloud\":\"100\",\"cond_code\":\"302\",\"cond_txt\":\"雷阵雨\",\"dew\":\"17\",\"hum\":\"94\",\"pop\":\"61\",\"pres\":\"1011\",\"time\":\"2018-04-23 04:00\",\"tmp\":\"15\",\"wind_deg\":\"186\",\"wind_dir\":\"南风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"15\"},{\"cloud\":\"100\",\"cond_code\":\"104\",\"cond_txt\":\"阴\",\"dew\":\"16\",\"hum\":\"93\",\"pop\":\"34\",\"pres\":\"1010\",\"time\":\"2018-04-23 07:00\",\"tmp\":\"19\",\"wind_deg\":\"174\",\"wind_dir\":\"南风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"15\"},{\"cloud\":\"100\",\"cond_code\":\"302\",\"cond_txt\":\"雷阵雨\",\"dew\":\"15\",\"hum\":\"91\",\"pop\":\"68\",\"pres\":\"1012\",\"time\":\"2018-04-23 10:00\",\"tmp\":\"20\",\"wind_deg\":\"349\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"19\"},{\"cloud\":\"100\",\"cond_code\":\"302\",\"cond_txt\":\"雷阵雨\",\"dew\":\"14\",\"hum\":\"90\",\"pop\":\"20\",\"pres\":\"1011\",\"time\":\"2018-04-23 13:00\",\"tmp\":\"18\",\"wind_deg\":\"10\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"22\"},{\"cloud\":\"100\",\"cond_code\":\"302\",\"cond_txt\":\"雷阵雨\",\"dew\":\"15\",\"hum\":\"88\",\"pop\":\"20\",\"pres\":\"1011\",\"time\":\"2018-04-23 16:00\",\"tmp\":\"16\",\"wind_deg\":\"10\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"17\"},{\"cloud\":\"94\",\"cond_code\":\"104\",\"cond_txt\":\"阴\",\"dew\":\"13\",\"hum\":\"85\",\"pop\":\"7\",\"pres\":\"1014\",\"time\":\"2018-04-23 19:00\",\"tmp\":\"14\",\"wind_deg\":\"11\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"18\"},{\"cloud\":\"65\",\"cond_code\":\"104\",\"cond_txt\":\"阴\",\"dew\":\"11\",\"hum\":\"86\",\"pop\":\"7\",\"pres\":\"1015\",\"time\":\"2018-04-23 22:00\",\"tmp\":\"16\",\"wind_deg\":\"350\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"13\"},{\"cloud\":\"84\",\"cond_code\":\"104\",\"cond_txt\":\"阴\",\"dew\":\"9\",\"hum\":\"89\",\"pop\":\"3\",\"pres\":\"1014\",\"time\":\"2018-04-24 01:00\",\"tmp\":\"16\",\"wind_deg\":\"359\",\"wind_dir\":\"北风\",\"wind_sc\":\"3-4\",\"wind_spd\":\"19\"}],\"aqi\":{\"city\":{\"aqi\":\"73\",\"pm25\":\"59\",\"qlty\":\"良\"}},\"suggestion\":{\"comf\":{\"brf\":\"舒适\",\"txt\":\"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。\",\"type\":\"comf\"},\"sport\":{\"brf\":\"较适宜\",\"txt\":\"天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。\",\"type\":\"sport\"},\"cw\":{\"brf\":\"较不宜\",\"txt\":\"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。\",\"type\":\"cw\"}}}]}";
            JSONObject jsonObject = new JSONObject(response);
            Log.d("222222", jsonObject.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
