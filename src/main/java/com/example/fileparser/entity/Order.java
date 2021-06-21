package com.example.fileparser.entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    @CsvBindByPosition(position = 0)
    private String orderId;

    @CsvBindByPosition(position = 1)
    private String amount;

    @CsvBindByPosition(position = 2)
    private String currency;

    @CsvBindByPosition(position = 3)
    private String comment;
}
