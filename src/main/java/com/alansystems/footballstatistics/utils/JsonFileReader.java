package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    final static String FILE_PATH = "src/main/resources/data/messages_2.txt";

    public static List<Message> JsonFileReader(String path) {
        List<Message> messagesList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while (line = reader.readLine() != null) {
                Message message = mapper.readValue(line, Message.class);
                messagesList.add(message);
            }

            for (Message message : messagesList) {
                System.out.println(message);
            }
        }
    }

}
