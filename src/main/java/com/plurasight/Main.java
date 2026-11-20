package com.plurasight;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String searchTerm;

        try (BufferedWriter writerbuffered =
                     new BufferedWriter(new FileWriter("logs.txt", true))) {

            writeLog(writerbuffered, "launch");

            while (true) {
                System.out.print("Enter a search term (X or x to exit): ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                searchTerm = scanner.nextLine().trim();

                if (searchTerm.equalsIgnoreCase("x")) {
                    break;
                }

                if (!searchTerm.isEmpty()) {
                    writeLog(writerbuffered, "search : " + searchTerm);
                }
            }

            writeLog(writerbuffered, "exit");
        } catch (IOException e) {
            System.out.println("Can not write to logs.txt: " + e.getMessage());
        }
    }

    public static void writeLog(BufferedWriter writerbuffered, String action)
            throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(format);

        writerbuffered.write(time + " " + action);
        writerbuffered.newLine();

    }
}