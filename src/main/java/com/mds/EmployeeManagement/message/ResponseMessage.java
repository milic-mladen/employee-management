package com.mds.EmployeeManagement.message;

public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
