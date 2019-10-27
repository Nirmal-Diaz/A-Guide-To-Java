package io;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Scanners {
    //Use "resources/textOriginal.txt" as default inputFilePath
    public static void readFileByScanner(String inputFilePath) {
        //NOTE: "Scanner" automatically reads and converts data into Java compatible types for easier usage
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFilePath)))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch(FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred");
        }
    }
}