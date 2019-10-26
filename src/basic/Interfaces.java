package basic;

class Interfaces {
    static void demonstrateInterfaces() {
        //NOTE: When a class "implements" an "interface", that "interface" can be used as a type to store an instance of the class
        //It is better to use an "interface" as the type rather than using a class
        SonyColorTv sonyTV1 = new SonyColorTv();
        Television sonyTV2 = new SonyColorTv();

        //NOTE: "Static" members of an "interface" can be accessed as follows
        System.out.println(Television.signalType);
        Television.disassemble();
    }
}

//NOTE: "Interfaces" are just like classes
//NOTE: "Interfaces" can only contain "constants", "method signatures"/"abstract methods", "default methods", "static methods", and "nested types"
//NOTE: Method signatures can be declared as either with "abstract" or without "abstract"
//NOTE: Method bodies exist only for "default" methods and "static" methods
//NOTE: All members in an "interface" are implicitly "public", so we can omit the "public" modifier
//NOTE: All fields in an "interface" are implicitly "final", so we can omit the "final" keyword
interface Television {
    //Constants
    public final static String signalType = "TV Signals";

    //Method Signatures/Abstract Methods
    public void turnOn();
    public abstract void changeChannel(int channel);
    public void turnOff();

    //Static Methods
    public static void disassemble() {
        System.out.println("The TV is ready to repair");
    }

    //Default Methods
    //NOTE: "Default" methods are like "static" methods
    //NOTE: "Default" methods support "overriding" while "static" methods do not

    //Think when a TV manufacturer "implements" out "Television interface" and have to provide "implementation" for all the methods
    //That manufacturer may not have retractable TVs. In that case the manufacturer cannot "implement" retractScreen()
    //Therefore we can make retractScreen() "default" and provide a "default implementation" so that the manufacturer doesn't have to
    //But the best option is not to use "default methods" and declare another "interface" as "RetractableTelevision extends Television" 
    public default void retractScreen() {
        System.out.println("Screen is retracted");
    }
}

interface Radio {
    void tuneForFrequency(int frequency);
}

//NOTE: "Interfaces" can be "extended" by other "interfaces" (Java supports "multiple inheritance" for "interfaces")
interface SmartTelevision extends Television, Radio {
    void browseInternet(String URL);
}

//NOTE: "Interfaces" cannot be instantiated
//NOTE: They can only be "implemented" by classes or "extended" by other "interfaces"
//NOTE: When a class "implements" an "interface", that class must provide "implementation" for all the "abstract" methods
//NOTE: Since all members of an "interface" are "public", so must be the "implementations" inside a class
class SonyColorTv implements Television {
    @Override
    public void turnOn() {
        System.out.println("Turned off");
    }

    @Override
    public void changeChannel(int channel) {
        System.out.println("Changed channel to: " + channel);
    }

    @Override
    public void turnOff() {
        System.out.println("Turned on");
    }
}