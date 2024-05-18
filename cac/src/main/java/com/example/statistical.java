package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class statistical {
    
    private String csvFile;

    public statistical(String csvFile) {
        this.csvFile = csvFile;
    }

    public void describeData() {
        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            // Read the first line to get the headers
            if (scanner.hasNextLine()) {
                String headerLine = scanner.nextLine();
                String[] headers = headerLine.split(",");
                System.out.println("Number of columns: " + headers.length);
                System.out.println("Column headers: ");
                for (String header : headers) {
                    System.out.println(header.trim());
                }
            }

            // Count the number of rows
            int rowCount = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                rowCount++;
            }

            System.out.println("Number of rows (excluding headers): " + rowCount);
            scanner.close(); // Close the scanner when done
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void performAnalysis() {
        List<Double> data = new ArrayList<>();

        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            // Skip the first line assuming it contains headers
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip headers
            }

            // Read and process each line of the CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into columns based on the CSV delimiter (e.g., comma)
                String[] columns = line.split(",");

                // Process each column of the line
                for (String column : columns) {
                    if (!column.trim().isEmpty()) { // Check if the column is not empty or only whitespace
                        try {
                            double value = Double.parseDouble(column.trim());
                            data.add(value); // Add the numeric value to the list
                        } catch (NumberFormatException ignored) {
                            // Ignore non-numeric values
                        }
                    }
                }
            }

            scanner.close(); // Close the scanner when done
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Calculate statistics
        if (!data.isEmpty()) {
            Collections.sort(data);

            double sum = 0;
            for (double value : data) {
                sum += value;
            }
            double mean = sum / data.size();

            double variance = 0;
            for (double value : data) {
                variance += Math.pow(value - mean, 2);
            }
            variance /= data.size();

            double standardDeviation = Math.sqrt(variance);

            double min = data.get(0);
            double max = data.get(data.size() - 1);

            double q1 = data.get(data.size() / 4);
            double q3 = data.get((data.size() * 3) / 4);
            double interquartileRange = q3 - q1;

            double coefficientOfVariation = (standardDeviation / mean) * 100;

            // Print statistics
            System.out.println("Mean: " + mean);
            System.out.println("Variance: " + variance);
            System.out.println("Standard Deviation: " + standardDeviation);
            System.out.println("Minimum: " + min);
            System.out.println("Maximum: " + max);
            System.out.println("Interquartile Range: " + interquartileRange);
            System.out.println("Coefficient of Variation: " + coefficientOfVariation + "%");
        } else {
            System.out.println("No numerical data found in the CSV file.");
        }
    }
}
