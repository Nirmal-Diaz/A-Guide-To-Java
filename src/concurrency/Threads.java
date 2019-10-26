package concurrency;

//NOTE: In "concurrent programming", there are two basic units of execution, "Processes" and "Threads"
//NOTE: In the Java, "concurrent programming" is mostly concerned with "Threads" (But "Processes" can be involved too)
//NOTE: A computer system normally has many active "Processes" and "Threads" made available by the OS even when its hardware supports only one "Hardware Thread"
//NOTE: Processing time for a "Hardware Thread" is shared among "OS level processes and threads" through an OS feature called "Time Slicing"

//PROCESS
//NOTE: A "process" has a self-contained execution environment. A "process" generally has a complete, private set of basic run-time resources, and its own "memory space"
//NOTE: To facilitate communication between "processes", most OSs support "Inter Process Communication (IPC) resources", such as "Pipes and Sockets"
//NOTE: Most implementations of the JVM run as a "single process". A Java application can create additional "processes" using a "ProcessBuilder" object

//THREADS
//NOTE: "Threads" are sometimes called "Lightweight Processes"
//NOTE: Both "processes" and "threads" provide an execution environment, but creating a new "thread" requires fewer resources than creating a new "process"
//NOTE: "Threads" exist within a "process". Every "process" has at least one "thread"
//NOTE: "Threads" share the "process's" resources, including memory and open files. This makes for efficient, but potentially problematic, communication
//NOTE: In Java, from the programmer's point of view, we start with just "one thread", called the "Main Thread". This "thread" has the ability to create additional "threads"
class Threads {
    static void demonstrateThreadCreation() {
        //To create a "thread", we need to have a class that implements "Runnable" interface
        //A class that implements "Runnable" must define a method "void run()" which includes the code to run inside the "thread"
        class MyRunnable implements Runnable {
            public void run() {
                System.out.println("This is the code for the new thread");
            }
        }

        //Now we can create a new "thread" instance by passing an instance of "MyRunnable" to it
        Thread myThread = new Thread(new MyRunnable());
        //To start the "thread", we must invoke it's "void start()" method
        myThread.start();
    }

    static void demonstrateDelay(){
        //We can delay the current "thread" with the "static" method "void sleep()"
        //"void sleep()" is prone to "interruptions" and throws "InterruptedException" (Because it depends on the OS for sleeping and timing)
        int[] numbers = {1, 2, 3, 4};

        for (int i = 0; i < numbers.length; i++) {
            try {
                Thread.sleep(4000); //Pause for 4 seconds
            } catch (InterruptedException e) {
                System.out.println("Interruption occurred. Terminating");
                return;
            }
            //Print a message
            System.out.println(numbers[i]);
        }
    }
}
