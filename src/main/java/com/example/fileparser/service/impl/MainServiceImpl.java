package com.example.fileparser.service.impl;

import com.example.fileparser.service.FileParser;
import com.example.fileparser.service.MainService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class MainServiceImpl implements CommandLineRunner, MainService {


    private static final Map<String, FileParser> PARSERS_IMPL = new HashMap<>();

    @Autowired
    public MainServiceImpl(FileParser[] parsers) {
        fillParsers(parsers);
    }

    private void fillParsers(FileParser[] parsers) {
        for (FileParser parser : parsers) {
            PARSERS_IMPL.put(parser.type(), parser);
        }
    }

    @Override
    public void run(String... args) {
        Stream.of(args).parallel().forEach(arg -> {
            String extension = FilenameUtils.getExtension(arg);
            PARSERS_IMPL.get(extension).parseFile(arg);
        });
    }
}
