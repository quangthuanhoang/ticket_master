package com.thuanhq.ticket_master.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    INVALID_MESSAGE_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User is existed!"),
    USERNAME_INVALID(1003, "Username must be least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be least 8 characters"),
    USER_NOT_EXISTED(1005, "User not existed"),
    ;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
