package com.nn.challange;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nn.challange.Services.FileProcessingService;

@SpringBootApplication
public class FileProcessorApplication implements CommandLineRunner {
    
    private final FileProcessingService fileProcessingService;

    public FileProcessorApplication(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FileProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileProcessingService.processFiles();
    }
}

