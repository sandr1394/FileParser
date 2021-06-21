package com.example.fileparser.util;

import com.example.fileparser.entity.Order;
import com.example.fileparser.entity.Result;

import static com.example.fileparser.util.Constants.OK;

public class Util {

    public static Result processOrder(Order order, String fileName, Integer lineNumber) {
        String validationResult = validateOrder(order);
        return getResult(order, fileName, lineNumber, validationResult);
    }

    private static String validateOrder(Order order) {
        StringBuilder validationResult = new StringBuilder();
        if (order.getComment().isEmpty()) {
            validationResult.append(validationResult.toString().isEmpty() ? "Exception: Comment is empty." : " Comment is empty");
        }
        if (order.getCurrency().isEmpty()) {
            validationResult.append(validationResult.toString().isEmpty() ? "Exception: Currency is empty." : " Currency is empty.");
        }
        if (!order.getAmount().matches("[+-]?\\d+[.]?\\d+") || order.getAmount().isEmpty()) {
            validationResult.append(validationResult.toString().isEmpty() ? "Exception: Amount have to be in numeric format." : " Exception: Amount have to be in numeric format");
        }

        return validationResult.toString();
    }

    private static Result getResult(Order order, String fileName, Integer lineNumber, String validationResult) {
        Result result = new Result();
        result.setId(IdHolder.incrementAndGet());
        result.setOrderId(order.getOrderId());
        result.setAmount(order.getAmount());
        result.setComment(order.getComment());
        result.setFileName(fileName);
        result.setLineNumber(String.valueOf(lineNumber));
        result.setResult(validationResult.isEmpty() ? OK : validationResult);
        return result;
    }
}
