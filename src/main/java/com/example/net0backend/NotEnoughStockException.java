package com.example.net0backend;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        //메시지와 근원 exception 출력 -> exception trace 나오도록
        super(message, cause);
    }
    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}

