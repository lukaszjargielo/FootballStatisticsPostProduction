package com.alansystems.footballstatistics;

import com.alansystems.footballstatistics.utils.JsonFileReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;

@SpringBootApplication
public class FootballStatisticsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FootballStatisticsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String FILE_PATH = "src/main/resources/data/messages_2.txt";

        JsonFileReader.readJsonFromFile(FILE_PATH);
    }
}
