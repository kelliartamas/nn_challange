package com.nn.challange.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FixedWidthFileReader {
    public static List<String[]> parseFixedWidthFile(String filePath, int[] fieldLengths) throws IOException {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(FixedWidthFileReader.class.getClassLoader().getResourceAsStream(filePath))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = new String[fieldLengths.length];
                int currentPosition = 0;

                for (int i = 0; i < fieldLengths.length; i++) {
                    int length = fieldLengths[i];
                    fields[i] = line.substring(currentPosition, Math.min(currentPosition + length, line.length())).trim();
                    currentPosition += length;
                }

                records.add(fields);
            }
        }
        return records;
    }
}

