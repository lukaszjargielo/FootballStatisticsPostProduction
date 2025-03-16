package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    final static String FILE_PATH = "src/main/resources/data/messages_2.txt";

    public static List<Message> JsonFileReader(String path) {
        List<Message> messagesList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Message message = mapper.readValue(line, Message.class);
                    messagesList.add(message);

                    for (Message m : messagesList) {
                        System.out.println(m);
                    }
                } catch (IOException e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + FILE_PATH);
            e.printStackTrace();
        }
        return messagesList;
    }
}
