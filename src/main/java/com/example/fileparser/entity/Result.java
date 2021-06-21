package com.example.fileparser.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    private Integer id;
    private String orderId;
    private String amount;
    private String comment;
    private String fileName;
    private String lineNumber;
    private String result;
}
