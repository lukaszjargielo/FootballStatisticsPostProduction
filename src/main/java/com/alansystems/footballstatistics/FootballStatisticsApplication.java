package com.alansystems.footballstatistics;

/*import com.alansystems.footballstatistics.utils.MessageProcessor;*/
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class FootballStatisticsApplication implements CommandLineRunner {

    /*private final JsonFileReader jsonFileReader;
    private final MessageProcessor messageProcessor;

    public FootballStatisticsApplication(JsonFileReader jsonFileReader, MessageProcessor messageProcessor) {
        this.jsonFileReader = jsonFileReader;
        this.messageProcessor = messageProcessor;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(FootballStatisticsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*final String FILE_PATH = "src/main/resources/data/messages_2.txt";

        List<Message> messages = jsonFileReader.readJsonFromFile(FILE_PATH);
        messages.forEach(System.out::println);
        messageProcessor.processMessage(messages);*/

    }
}
