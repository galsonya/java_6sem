package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Dependency Injection Tester ===");
            System.out.println("1. Run with current config (show output)");
            System.out.println("2. Change SomeInterface implementation");
            System.out.println("3. Change SomeOtherInterface implementation");
            System.out.println("4. Show current config");
            System.out.println("5. Exit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        runWithCurrentConfig();
                        break;
                    case 2:
                        changeImplementation("SomeInterface");
                        break;
                    case 3:
                        changeImplementation("SomeOtherInterface");
                        break;
                    case 4:
                        showCurrentConfig();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option, try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void runWithCurrentConfig() throws Exception {
        SomeBean bean = new Injector().inject(new SomeBean());
        System.out.println("\nRunning with current configuration:");
        bean.foo();
    }

    private static void changeImplementation(String interfaceName) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAvailable implementations for " + interfaceName + ":");
        if (interfaceName.equals("SomeInterface")) {
            System.out.println("1. SomeImpl (outputs 'A')");
            System.out.println("2. OtherImpl (outputs 'B')");
        } else {
            System.out.println("1. SODoer (outputs 'C')");
        }

        System.out.print("Select implementation (1-2): ");
        int implChoice = scanner.nextInt();
        scanner.nextLine();

        String newImplementation;
        if (interfaceName.equals("SomeInterface")) {
            newImplementation = (implChoice == 1) ?
                    "org.example.SomeImpl" : "org.example.OtherImpl";
        } else {
            newImplementation = "org.example.SODoer";
        }

        // Обновляем config.properties
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(input);
        }

        props.setProperty("org.example." + interfaceName, newImplementation);

        try (FileWriter writer = new FileWriter(
                "src/main/resources/config.properties")) {
            props.store(writer, "Updated implementations");
        }

        System.out.println("Configuration updated successfully!");
        System.out.println("New mapping: " + interfaceName + " -> " + newImplementation);
    }

    private static void showCurrentConfig() throws IOException {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(input);
        }

        System.out.println("\nCurrent configuration:");
        props.forEach((key, value) ->
                System.out.println(key + " = " + value));
    }
}