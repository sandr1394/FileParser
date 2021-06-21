package com.example.fileparser.service.impl;

import com.example.fileparser.entity.Order;
import com.example.fileparser.entity.Result;
import com.example.fileparser.service.FileParser;
import com.example.fileparser.util.Util;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.fileparser.util.Constants.CSV;

@Component
public class CsvFileParser implements FileParser {

    private final ExecutorService executorService;
    private final Gson gson;
    private final AtomicInteger lineNumber = new AtomicInteger();

    @Autowired
    public CsvFileParser(ExecutorService executorService, Gson gson) {
        this.gson = gson;
        this.executorService = executorService;
    }

    @Override
    public void parseFile(String fileName) {
        try {
            List<Order> orders = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(Order.class)
                    .build()
                    .parse();

            orders.forEach(order -> executorService.submit(() ->
                    {
                        Result result = Util.processOrder(order, fileName, lineNumber.incrementAndGet());
                        System.out.println(gson.toJson(result));
                    }
            ));

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file with name: " + fileName);
        }
    }

    @Override
    public String type() {
        return CSV;
    }
}
