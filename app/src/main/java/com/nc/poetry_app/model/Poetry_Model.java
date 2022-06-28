package com.nc.poetry_app.model;

public class Poetry_Model {
    int  id;
    String poet_name;
    String pretry_data;
    String data_time;

    public Poetry_Model(int id, String poet_name, String pretry_data, String data_time) {
        this.id = id;
        this.poet_name = poet_name;
        this.pretry_data = pretry_data;
        this.data_time = data_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoet_name() {
        return poet_name;
    }

    public void setPoet_name(String poet_name) {
        this.poet_name = poet_name;
    }

    public String getPretry_data() {
        return pretry_data;
    }

    public void setPretry_data(String pretry_data) {
        this.pretry_data = pretry_data;
    }

    public String getData_time() {
        return data_time;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }
}
