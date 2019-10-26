package basic;

//NOTE: In "inheritance", "subclass" "inherits" all the "static and instance" methods and fields of its "superclass"
//NOTE: When the "Subclass" re-declares "instance" methods and fields of its "superclass" it is known as "Overriding"
//NOTE: When the "Subclass" re-declares "static" methods and fields of its "superclass" it is known as "Hiding"
//NOTE: A class can only "inherit" from a single parent class (Java doesn't support "multiple inheritance" for classes)
class Inheritance {
    static void demonstrateInheritance() {
        //"Superclass" have access to its own "static" methods and fields
        System.out.println(Superclass.superclassStaticField);
        Superclass.superclassStaticMethod();
        //"Subclass" have access to its own "static" methods and fields
        System.out.println(Subclass.subclassStaticField);
        Subclass.subclassStaticMethod();
        //"Subclass" have access to its "Superclass" "static" methods and fields
        System.out.println(Subclass.superclassStaticField);
        Subclass.superclassStaticMethod();
        //"Subclass" cannot "override" "Superclass" "static" methods and fields

        //Each class can be instantiate to its own type
        Superclass superclassInstance = new Superclass();
        Subclass subclassInstance = new Subclass();
        //Additionally "Subclass" can be instantiated to its "Superclass" type. But Compiler treats it as an instance of "Superclass"
        //NOTE: Doing this will hide all "Subclass" specific methods and fields of this instance
        Superclass subclassAsSuperclass = new Subclass();
        subclassAsSuperclass.subclassInstanceMethod(); //Compile-time error
        //NOTE: To use "Subclass" specific methods and fields from this instance, we have to "cast"
        Subclass subclassAsSubclass = (Subclass) subclassAsSuperclass;
        subclassAsSubclass.subclassInstanceMethod();

        //"Superclass" have access to its own "instance" methods and fields
        System.out.println(superclassInstance.superclassInstanceField);
        superclassInstance.superclassInstanceMethod();
        //"Subclass" have access to its own "instance" methods and fields
        System.out.println(subclassInstance.subclassInstanceField);
        subclassInstance.subclassInstanceMethod();
        //"Subclass" have access to its "Superclass" "instance" methods and fields
        System.out.println(subclassInstance.superclassInstanceField);
        subclassInstance.superclassInstanceMethod();
        //"Subclass" can only "override" "Superclass" "instance" methods and fields
        subclassInstance.superclassWhoAmI(); //Prints "I'm Subclass"
        //"Subclass" can only "override and extend" Superclass "instance" methods and fields
        subclassInstance.explainSelf(); //Executes both methods of the two classes
    }
}

class Superclass {
    static String superclassStaticField = "superclassStaticField";

    static void superclassStaticMethod() {
        System.out.println("superclassStaticMethod");
    }

    String superclassInstanceField = "superclassStaticMethod";

    void superclassInstanceMethod() {
        System.out.println("superclassInstanceMethod");
    }

    void superclassWhoAmI() {
        System.out.println("I'm Superclass");
    }

    void explainSelf() {
        System.out.println("Super: I'm Superclass. I have one child named Subclass.");
    }
}

class Subclass extends Superclass {
    static String subclassStaticField = "subclassStaticField";

    //You can "hide" "static" members of the "Superclass" by re-declaring
    static String superclassStaticField = "I've hidden superclassStaticField";

    static void subclassStaticMethod() {
        System.out.println("subclassStaticMethod");
    }

    String subclassInstanceField = "subclassStaticMethod";

    void subclassInstanceMethod() {
        System.out.println("subclassInstanceMethod");
    }

    @Override
    void superclassWhoAmI() {
        System.out.println("I'm Subclass");
    }

    @Override
    void explainSelf() {
        super.explainSelf();
        System.out.println("Sub: Yeah! That's me. I'm Subclass. My parent is Superclass.");
    }
}