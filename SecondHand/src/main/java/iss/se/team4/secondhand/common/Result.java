package iss.se.team4.secondhand.common;

public class Result<T> {

    private boolean success;
    private String message;
    private T data;


    public Result() {
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // success
    public static <T> Result<T> success() {
        return new Result<>(true, "Operation successful", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "Operation successful", data);
    }

    // fail
    public static <T> Result<T> failure(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
