package com.nc.poetry_app.model.Response;

import android.util.Log;

import com.nc.poetry_app.model.Poetry_Model;

import java.util.List;

public class GetPoetryResponse {
    String status;
    String message;
    List<Poetry_Model> data;

    public GetPoetryResponse(String status, String message, List<Poetry_Model> data) {
        this.status = status;
        this.message = message;
        this.data = data;
        Log.d("naveen","Under constructor");
        System.out.println("Under constructor////////////////////////////////////////////");
        for(Poetry_Model p: data)
        {
            Log.d("naveen"," "+p.getPoet_name());
            System.out.println(p.getPoet_name());
        }
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Poetry_Model> getData() {
        return data;
    }

    public void setData(List<Poetry_Model> data) {
        this.data = data;
    }
}
