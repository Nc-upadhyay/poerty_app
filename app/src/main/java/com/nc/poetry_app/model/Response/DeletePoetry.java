package com.nc.poetry_app.model.Response;

public class DeletePoetry {

    String status,Message;

    public DeletePoetry(String status, String message) {
        this.status = status;
        Message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
