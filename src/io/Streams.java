//THE UTF-8
//NOTE: UTF-8 is one of many methods to encode "Unicode" code points, with others being UTF-16, UTF-32 etc.
//NOTE: UTF-8 uses 1-4 8bit(1 byte) binary sequences to represent characters
//NOTE: therefore some UTF-8 encoded characters can have variable number of bytes ranging from 1byte to 4bytes

//Encoding to UTF-8
//Example: €
//1. Get the code point using the unicode table -> U+20AC
//2. Following should be obtained using the code point
//The "number of bytes" needed to encode the given character -> 3 (Should be determined using table)
//The "binary value" -> 20AC -> 0010 0000 1010 1100
//3. Construct the 1st byte
//Add the required "number of bytes" as binary 1's in the first 4bits
//1110 **** **** **** **** ****
//The first 4bits of the "binary value" is added next
//1110 0010 **** **** **** ****
//4. Construct the 2nd byte
//Next six bits of the "binary value" is added to the last 6 bits of the 2nd byte
//1110 0010 **00 0010 **** ****
//Pattern "10" is added to mark 2nd byte as a "continuation byte"
//1110 0010 1000 0010 **** ****
//4. Construct the 3rd byte
//Next six bits of the binary value is added to the last 6 bits of the 3nd byte
//1110 0010 1000 0010 **10 1100
//Pattern "10" is added to mark 3nd byte as a "continuation byte"
//1110 0010 1000 0010 1010 1100
//5. Represent final result in different number systems
//11100010 10000010 10101100 = 226 130 172 = E2 82 AC

package io;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

//TRY-WITH-RESOURCE STATEMENT
//NOTE: When working with resources, we have to close (With the "close()") all the used resources when they are no longer needed
//NOTE: Java provides a special code structure called "try-with-resource statement" for the purpose of automatically closing resources
//NOTE: The "try-with-resource statement" only works with classes that implement "AutoClosable"
//NOTE: All the classes used below implements "AutoCloseable", therefore we can initialize them with a "try-with-resource" statement

//IO BYTE STREAMS
//NOTE: Names of "byte streams" typically end with InputStream/OutputStream
//EXAMPLE: "FileInputStream" and "FileOutputStream" classes are "byte streams"
//NOTE: Both the "Standard Input (System.in)" and "Standard Output (System.out)" are "byte streams"

//Following hierarchy shows the most important "byte stream" classes

//                             InputStream                                                                                                     OutputStream
//                                  |                                                                                                               |
//           ================================================================                                      ==================================================================
//           |                      |                   |                   |                                      |                      |                    |                    |
//  ByteArrayInputStream    FilterInputStream    FileInputStream    ObjectInputStream                   ByteArrayOutputStream    FilterOutputStream    FileOutputStream    ObjectOutputStream
//                                  |                                                                              |
//                     =========================                                               ==========================================
//                     |                       |                                               |                   |                    |
//            BufferedInputStream       DataInputStream                             BufferedOutputStream     DataOutputStream        PrintStream

class ByteStreams {
    //Use "resources/textOriginal.txt" as default inputFilePath
    //Use "resources/textCopy.txt" as default outputFilePath
    static void copyFileByByte(String inputFilePath, String outputFilePath) {
        //NOTE: "BufferedInputStream" and "BufferedOutputStream" classes wrap "byte streams" into "buffered byte streams"
        //NOTE: "FileInputStream" reads the file byte by byte as decimal
        try (FileInputStream fileInputStream = new FileInputStream(inputFilePath); FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {
            System.out.println("Decimal values of each byte of the file \"" + inputFilePath + " \"is as follows");
            int byteAsDec;
            while ((byteAsDec = fileInputStream.read()) != -1) {
                fileOutputStream.write(byteAsDec);
                System.out.print(byteAsDec + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    static void standardInputAndOutput() {
        //"System.in" is a "byte stream" which is an instance of "InputStream" and we need to wrap it with a "character stream" in order to take advantage
        InputStreamReader standardInput = new InputStreamReader(System.in);

        //"System.out" and "System.err" are both instances of "PrintStream". Although "PrintStream" is a "byte stream", it utilizes "character streams" internally
    }
}

//IO CHARACTER STREAMS
//NOTE: Names of "character streams" typically end with Reader/Writer
//EXAMPLE: "FileReader" and "FileWriter" classes are "character streams"

//Following hierarchy shows the most important "character stream" classes

//                          Reader                                                                               Writer
//                            |                                                                                    |
//        ========================================                                              ========================================
//        |                   |                  |                                              |                  |                   |
//  BufferedReader    InputStreamReader    StringReader                                   BufferedWriter    InputStreamWriter    StringWriter  
//                            |                                                                                    |
//                        FileReader                                                                           FileWriter

class CharacterStreams {
    //Use "resources/textOriginal.txt" as default inputFilePath
    //Use "resources/textCopy.txt" as default outputFilePath
    static void copyFileByCharacter(String inputFilePath, String outputFilePath) {
        //NOTE: "BufferedReader" and "BufferedWriter" classes wrap "character streams" into "buffered character streams"
        //NOTE: "FileReader" reads the file character by character by decoding relevant UTF encoding and converting the "binary value" into decimal
        try (FileReader fileReader = new FileReader(inputFilePath); FileWriter fileWriter = new FileWriter(outputFilePath)) {
            System.out.println(
                    "Decimal \"unicode values\" of each character of the file \"" + inputFilePath + " \"is as follows");
            int unicodeValueAsDec;
            while ((unicodeValueAsDec = fileReader.read()) != -1) {
                fileWriter.write(unicodeValueAsDec);
                System.out.print(unicodeValueAsDec + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    //Use "resources/textOriginal.txt" as default inputFilePath
    //Use "resources/textCopy.txt" as default outputFilePath
    static void copyFileByLine(String inputFilePath, String outputFilePath) {
        //NOTE: Buffered I/O reduces hardware level file accesses by retrieving or writing content to a buffer
        //NOTE: Use "BufferedReader", "BufferedWriter" classes to convert "character streams" into "buffered character streams"
        //NOTE: Use "PrintWriter" class to convert "character streams" into "buffered character streams" and further support high level writing methods
        //NOTE: There is a "flush()" method to force and write the contents in an "output buffer"
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath)); BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            System.out.println("All the lines of the file \"" + inputFilePath + " \"is as follows");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}