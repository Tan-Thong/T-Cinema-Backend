package com.example.backend.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    MOVIE_NOT_EXISTED(2001, "Movie không tồn tại"),
    USER_NOT_EXISTED(1001, "User không tồn tại"),
    UNAUTHENTICATED(1006, "Unauthenticated!");

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
