package com.online.BookStore.dto;

import java.util.Date;

public class ErrorDetails {
    //private int data;
    private Date timestamp;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Date timestamp, String message) {

        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
