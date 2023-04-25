package com.taru.readshare.exceptions;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatusCode;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ErrorMessage  {

    private final ZonedDateTime timestamp;
    private final HttpStatusCode statusCode;
    private final String message;

    @Nonnull
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    @Nonnull
    public String getBody() {
        return message;
    }

    @Nonnull
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    
    public ErrorMessage(HttpStatusCode statusCode, String message) {
        this.timestamp = ZonedDateTime.now(ZoneId.of("Z"));
        this.statusCode = statusCode;
        this.message = message;
    }
}
