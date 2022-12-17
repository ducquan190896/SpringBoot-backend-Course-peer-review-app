package com.quan.coursepeerreview.Exception;

import java.time.LocalDateTime;

import org.apache.logging.log4j.message.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private Throwable throwable;
    private String message;
    private LocalDateTime localDateTime;

    public ErrorResponse(Throwable throwable, String message, LocalDateTime localDateTime) {
        this.throwable = throwable;
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
