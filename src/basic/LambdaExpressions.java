package basic;

//NOTE: The intention of "Lambda Expressions" is to treat procedure as data
class LambdaExpressions {
    static void demonstrateLambdas() {
        //Defining Lambda Expressions
        //NOTE: By defining a "Lambda Expression", we are creating and instantiating a class implicitly that implements the relevant interface
        
        //You can define as many lambdas to fit wer needs that complies to the below defined "SimpleMath" interface
        SimpleMath add = (a, b) -> {return a + b;};
        //"Return blocks" with only one statement can be defined without "braces" and the "return" keyword
        SimpleMath multiply = (x, y) -> x * y;
        //"Lambdas" with only one parameter can be defined without "parenthesis"
        Greet greetName = name -> {System.out.println("Hello " + name + "!");};

        //Using Lambda Expressions
        int answer = add.solve(1, 3);
        System.out.println(answer);

        System.out.println(multiply.solve(3, 5));

        greetName.greet("Nirmal Diaz");
    }
}

//NOTE: A "Lambda Expression" needs an interface to define its type data
//NOTE: An interface defined for a "Lambda Expression" can only contain a single abstract method
interface SimpleMath {
    int solve(int number1, int number2);
}

interface Greet {
    void greet(String username);
}