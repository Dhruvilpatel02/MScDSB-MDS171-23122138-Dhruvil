package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class csvr {

    private String csvFile;

    public csvr(String csvFile) {
        this.csvFile = csvFile;
    }

    public void readCSV() {
        int nullCount = 0;

        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            // Skip the first line assuming it contains headers
            if (scanner.hasNextLine()) {
                System.out.println("Dataset:");
                System.out.println(scanner.nextLine()); // Print headers
            }

            // Read and process each line of the CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into columns based on the CSV delimiter (e.g., comma)
                String[] columns = line.split(",");

                // Process each column of the line
                for (String column : columns) {
                    if (column.trim().isEmpty()) { // Check if the column is empty or only whitespace
                        nullCount++; // Increment null count
                    }
                    System.out.print(column + "\t"); // Print column data
                }
                System.out.println(); // Move to the next line after printing each row
            }

            scanner.close(); // Close the scanner when done
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Number of null values: " + nullCount);
    }
}
