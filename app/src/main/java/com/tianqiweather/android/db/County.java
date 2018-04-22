package com.tianqiweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/4/22.
 */

public class County extends DataSupport {

    private int id;
    //县名
    private String countyNmae;
    //县对应天气Id
    private String weatherId;
    //市Id
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyNmae() {
        return countyNmae;
    }

    public void setCountyNmae(String countyNmae) {
        this.countyNmae = countyNmae;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
