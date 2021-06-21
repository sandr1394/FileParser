package com.example.fileparser.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdHolder {

    private static final AtomicInteger ID = new AtomicInteger();

    public static Integer incrementAndGet() {
        return ID.incrementAndGet();
    }
}
