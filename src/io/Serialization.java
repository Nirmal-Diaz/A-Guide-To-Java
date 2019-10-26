package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//NOTE: "Serializing" an object means to convert its state to a byte stream so that the byte stream can be reverted back into a copy of the object
//NOTE: A Java object is "serializable" if its class or any of its superclasses implements either the "java.io.Serializable"
//NOTE: "Deserialization" is the process of converting the serialized form of an object back into a copy of the object

public class Serialization {
    //Use "resources/serializedObject.ser" as default outputFilePath
    void demonstrateSerialization(String outputFilePath) {
        //NOTE: Only instances of classes can be "serialized"
        ProgrammingLanguage java = new ProgrammingLanguage();
        java.name = "Java";
        java.objectOriented = true;
        java.functional = false;
        java.birth = 1995;

        //NOTE: ".ser" is the conventional extension for "serialized" files
        //NOTE: "Object serialization" is done using "ObjectOutputStream"
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath)); ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
            objectOutputStream.writeObject(java);
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    //Use "resources/serializedObject.ser" as default inputFilePath
    public static void demonstrateDeserialization(String inputFilePath) {
        //We must first declare a variable to hold the "deserialized" object
        ProgrammingLanguage java;

        //NOTE: "Object deserialization" is done using "ObjectInputStream"
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(inputFilePath)); ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {
            //NOTE: "readObject()" returns an "Object", Therefor we need to cast it appropriately
            java = (ProgrammingLanguage) objectInputStream.readObject();
            System.out.println(java.name);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}

class ProgrammingLanguage implements java.io.Serializable{
    //NOTE: "ANY-ACCESS-MODIFIER static final long serialVersionUID" is required to load correct class during "deserialization"
    private static final long serialVersionUID = 1L;

    String name;
    boolean objectOriented;
    boolean functional;

    //NOTE: To exclude fields from "serialization", they must be marked "transient"
    transient int birth;
}