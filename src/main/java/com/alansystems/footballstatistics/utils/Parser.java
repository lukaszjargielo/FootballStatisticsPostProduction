package com.alansystems.footballstatistics.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    Path path = Paths.get("src/main/resources/data/messages_2.txt");
    ObjectMapper mapper = new ObjectMapper();
    List<String> lines;

    {
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lines.stream().forEach(line -> mapper.readValues(line, new TypeReference<List<String>>(){}));
    }

}
