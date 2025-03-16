package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileReader {

    private final ObjectMapper mapper;

    public JsonFileReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Message> readJsonFromFile(String path) {
        List<Message> messagesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Message message = mapper.readValue(line, Message.class);
                    messagesList.add(message);

                } catch (IOException e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + path);
            e.printStackTrace();
        }
        return messagesList;
    }
}
