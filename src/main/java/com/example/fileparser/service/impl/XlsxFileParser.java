package com.example.fileparser.service.impl;

import com.example.fileparser.service.FileParser;

import static com.example.fileparser.util.Constants.XLSX;

public class XlsxFileParser implements FileParser {

    @Override
    public void parseFile(String fileName) {

    }

    @Override
    public String type() {
        return XLSX;
    }
}
