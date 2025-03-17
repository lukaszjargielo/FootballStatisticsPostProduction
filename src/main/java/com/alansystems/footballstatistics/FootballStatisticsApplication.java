package com.alansystems.footballstatistics;

import com.alansystems.footballstatistics.model.Message;
import com.alansystems.footballstatistics.utils.JsonFileReader;
import com.alansystems.footballstatistics.utils.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.util.List;

import static com.alansystems.footballstatistics.utils.MessageProcessor.processMessage;

@SpringBootApplication
public class FootballStatisticsApplication implements CommandLineRunner {

    @Autowired
    private JsonFileReader jsonFileReader;
    @Autowired
    private MessageProcessor messageProcessor;

    public static void main(String[] args) {
        SpringApplication.run(FootballStatisticsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String FILE_PATH = "src/main/resources/data/messages_2.txt";

        List<Message> messages = jsonFileReader.readJsonFromFile(FILE_PATH);
        messages.forEach(System.out::println);

        processMessage(messages);

    }
}
