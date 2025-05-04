package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CsvWorkerParser parser = new CsvWorkerParser(';');
            List<Worker> workers = parser.parseWorkers("foreign_names.csv");

            workers.forEach(System.out::println);
            System.out.println("\nTotal workers parsed: " + workers.size());

        } catch (Exception e) {
            System.err.println("Failed to parse workers: " + e.getMessage());
            e.printStackTrace();
        }
    }
}