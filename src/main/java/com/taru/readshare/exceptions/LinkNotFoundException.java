package com.taru.readshare.exceptions;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException() {
        super("Link not found");
    }
}
