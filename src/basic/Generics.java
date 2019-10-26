package basic;

//NOTE: "Generics" is a way of detecting "run-time" errors at "compile-time"
//NOTE: All definitions of classes, interfaces and methods can be "Generic"
//NOTE: When defining formal classes, interfaces and methods we must permanently define "input types" and "return types"
//NOTE: When defining "generic" classes, interfaces and methods we can parameterize "input types" and "return types"
//NOTE: "Type parameters" provide a way for us to re-use the same code with different "type arguments"
//NOTE: The inputs to "formal parameters" are "values", while the inputs to "type parameters" are "types"
class Generics {
    void demonstrateNonGenericClasses() {
        //Following non-generic/formal class has concrete "input types" and "return types"
        //If we want different instances of "DataContainer" to store different "types" of data, we must to use "Object"
        //Using "Object" makes the type-safety fall apart
        class DataContainer {
            private Object data;
        
            public void set(Object data) {
                this.data = data;
            }

            public Object get() {
                return data;
            }
        }

        //We can store any "type" of data in an instance of "DataContainer" (because "Object" is a generalized "type")
        DataContainer container = new DataContainer();
        container.set(12);
        container.set("StringData");
        //If we were to retrieve stored data from an instance of "DataContainer", we must use "casting"
        String myString = (String) container.get();
    }

    void demonstrateGenericClasses() {
        //Following generic class has parameterized "input types" and "return types"
        //If we want different instances of "DataContainer" to store different "types" of data, we now have a method to tell the exact "type" to the compiler at the compile-time
        class DataContainer<T> {
            private T data;
        
            public void set(T data) {
                this.data = data;
            }

            public T get() {
                return data;
            }
        }

        //To instantiate a "generic" class we can provide required types (This is not required)
        DataContainer container = new DataContainer(); //Valid instantiation (Known as "Raw Type" instantiation)
        DataContainer<Integer> container = new DataContainer<Integer>(); //Valid instantiation (Known as "Parameterized Type" instantiation)
        //NOTE: When using "raw types", an instance gives us non-generic behavior using "Object" (if parameter is "unbounded") or "first bound type" (if parameter is "bounded")

        //We can now only store the data of "type" given when instantiating the "DataContainer"
        //But different instances can store different "types" of data
        container.set(12);
        container.set("StringData"); //Compile-time error
        //If we were to retrieve stored data from an instance of "DataContainer", we don't need "casting"
        Integer myInteger = container.get();
    }

    void demonstrateGenericMethods() {
        //In the following example, only the method is "generic", but not the class
        static class NonGenericClass {
            static <T> void genericPrint(T item) {
                System.out.println(item);
            }
        }
        //We can invoke the "generic" method as follows
        NonGenericClass.<String>genericPrint("String");
    }

    void demonstrateTypeInference() {
        //NOTE: When invoking "generic methods" or instantiating "generic classes", compiler can "infer/guess" the required type parameters by an algorithm
        //We can omit providing "type arguments" when invoking or instantiating
        java.util.List<Integer> list = new java.util.ArrayList<>(); //We can use "Diamond operator" to let the compiler "infer" "type arguments" when instantiating

        //Consider the following "non-generic" class with a "generic" method (Ignore the errors of the declaration as it is invalid)
        class NonGenericClass {
            static <T> void genericPrint(T item) {
                System.out.println(item);
            }
        }
        //We can invoke the "generic" method as follows with "type arguments"
        NonGenericClass.<String>genericPrint("String");
        NonGenericClass.<Integer>genericPrint(10);
        //We can also invoke the "generic" method as follows without "type arguments" with the help of "type inference"
        //NOTE: There is no way to distinguish "generic" method invocations from "non-generic" invocations as there is no use of "Diamond operator"
        NonGenericClass.genericPrint("String");
        NonGenericClass.genericPrint(10);
    }

    void demonstrateRestrictions() {
        //1. Cannot instantiate "generic types" with primitive types
        //2. Cannot create instances of "generic types"
            <E> void append(List<E> list) {
                E elem = new E();  // compile-time error
                list.add(elem);
            }
            //As a workaround, the above method can be declared using "Reflection"
            <E> void append(List<E> list, Class<E> cls) {
                E elem = cls.newInstance();   // OK
                list.add(elem);
            }
        //3. Cannot declare static fields of "generic types"
        //4. Cannot use "casts" or "instanceof" on "generic types"
        //5. Cannot create arrays of "generic types"
            List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error
    }
}