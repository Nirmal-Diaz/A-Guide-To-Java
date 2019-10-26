package basic;

//NOTE: "Casting" can be done with both "primitive types" and "reference types"
//NOTE: "Casting" can be done only with types that are on the "same inheritance chain"
class Casting {
    static void demonstratePrimitiveCasting() {
        //Remember the order of "primitive numeric types": byte < short < int < long < float < double (This can be thought as an "inheritance chain")
        //NOTE: We can assign any "low-range primitive" to a "high-range primitive" without hassle
        //NOTE: This is known as "Implicit Casting"/"Implicit Up-Casting"
        double floatAsDouble = 10.25F;
        long intAsLong = 12;
        //The reverse is not necessarily true
        float doubleAsFloat1 = 10.25D; //Compile-time error
        int longAsInt1 = 12L; //Compile-time error
        //But we can do such a thing only with "primitive types" if we are ready to sacrifice precision
        float doubleAsFloat2 = (float) 10.25D;
        int longAsInt2 = (int) 12L;
    }

    static void demonstrateClassCasting() {
        //Consider the following class declarations
        class Superclass {}
        class Subclass extends Superclass {}
        
        //NOTE: When "Subclass extends Superclass" as of right now, we can provide a Subclass whenever a Superclass is required
        //NOTE: This is an example for "Implicit Casting"/"Implicit Up-Casting"
        Superclass subclassAsSuperclass = new Subclass();
        //But the reverse is not necessarily true
        Subclass superclassAsSubclass = new Superclass(); //Compile-time error //This can never be done with "reference types"
        //In the following statement, we assign a "subclassInstance casted as superclassInstance" to a "Subclass" type
        //If we consider the actual types (not casted types), we know that this is right. //Only the compiler doesn't understand
        Subclass subclassInstance1 = subclassAsSuperclass; //Compile-time error
        //In such a case, we can force compiler to treat "subclassAsSuperclass" as a "subclassInstance" by casting
        //NOTE: This is known as "Explicit Casting"/"Explicit Down-Casting"
        Subclass subclassInstance2 = (Subclass) subclassAsSuperclass;
    }
}

