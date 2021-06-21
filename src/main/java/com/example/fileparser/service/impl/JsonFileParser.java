package com.example.fileparser.service.impl;

import com.example.fileparser.entity.Order;
import com.example.fileparser.entity.Result;
import com.example.fileparser.service.FileParser;
import com.example.fileparser.util.Util;
import com.google.gson.Gson;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.fileparser.util.Constants.JSON;

@Component
public class JsonFileParser implements FileParser {

    private final ExecutorService executorService;
    private final Gson gson;
    private final AtomicInteger lineNumber = new AtomicInteger();

    @Autowired
    public JsonFileParser(ExecutorService executorService, Gson gson) {
        this.executorService = executorService;
        this.gson = gson;
    }

    @Override
    public void parseFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader in = new InputStreamReader(fis)) {
            LineIterator it = new LineIterator(in);
            while (it.hasNext()) {
                Order order = gson.fromJson(it.nextLine(), Order.class);
                executorService.submit(() -> {
                    Result result = Util.processOrder(order, fileName, lineNumber.incrementAndGet());
                    System.out.println(gson.toJson(result));
                });
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file with name: " + fileName);
        } catch (IOException e) {
            System.out.println(String.format("Error has occurred during reading file: %s. Error message: %s", fileName, e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception has occurred within processing file: %s. Exception message: %s", fileName, e.getMessage()));
        }
    }


    @Override
    public String type() {
        return JSON;
    }
}
