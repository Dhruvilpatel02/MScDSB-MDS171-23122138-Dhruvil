package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String csvFile = "C:\\Games\\cac\\src\\main\\resources\\Credit_card.csv";
        csvr csvReader = new csvr(csvFile);
        analytics analytics = new analytics("Expense Analysis");
        statistical statistics = new statistical(csvFile);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Read CSV file");
            System.out.println("2. Display All Charts");
            System.out.println("3. Perform Statistical Analysis");
            System.out.println("4. Describe Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = inputScanner.nextInt();

            switch (choice) {
                case 1:
                    csvReader.readCSV();
                    break;
                case 2:
                    System.out.println("Displaying All Charts...");
                    analytics.displayCharts();
                    break;
                case 3:
                    System.out.println("Performing Statistical Analysis...");
                    statistics.performAnalysis();
                    break;
                case 4:
                    System.out.println("Describing Data...");
                    statistics.describeData();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
