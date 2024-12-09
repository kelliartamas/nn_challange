package com.nn.challange.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileReaderUtil {

    public static List<String[]> readFile(String filePath, String delimiter) throws IOException {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(FileReaderUtil.class.getClassLoader().getResourceAsStream(filePath)), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Trim the line to remove leading/trailing whitespace
                line = line.trim();

                if (line.isEmpty()) {
                    // If the line is empty or just whitespace, add a null record
                    records.add(null);
                } else {
                    // Parse the line and handle empty fields
                    String[] parsedFields = parseFields(line, delimiter);
                    records.add(parsedFields);
                }
            }
        }

        return records;
    }

    private static String[] parseFields(String line, String delimiter) {
        // Split the line while preserving empty fields
        String[] rawFields = line.split(delimiter, -1); // -1 ensures trailing empty fields are included
        List<String> parsedFields = new ArrayList<>();

        for (String field : rawFields) {
            // Replace empty fields with null
            parsedFields.add(field.isEmpty() ? null : field.trim());
        }

        return parsedFields.toArray(new String[0]);
    }
}

