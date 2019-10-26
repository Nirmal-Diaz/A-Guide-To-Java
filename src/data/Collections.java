package data;

//THE COLLECTIONS FRAMEWORK
//NOTE: In Java, there is a whole framework dedicated for "collections"
//NOTE: "Java Collections Framework" contains,
//1. Interfaces
//2. Implementations
//3. Algorithms

//INTERFACES FOR COLLECTIONS
//NOTE: A "Collection" represents a group of objects known as its "Elements"
//NOTE: The "Collection interface" is used to pass "collections" around and to manipulate them when maximum generality is desired
//NOTE: Some types of "collections" allow duplicate "elements", and others do not. Some are ordered and others are unordered
//NOTE: The "Java Collections Framework" doesn't have any direct implementations for the "java.util.Collection" interface
//NOTE: The "Java Collections Framework" supports "Generics"

//The following hierarchy shows the interfaces defined in the "Java Collections Framework"
//NOTE: "Map" cannot be considered a true "Collection" and therefore belongs in a different hierarchy
//NOTE: Although Java has a "Stack" class, it is not part of the "Java Collections Framework". "Deque" implementations provide "Stack" functionality

//              java.util.Collection                    java.util.Map
//                      |                                     |
//        =============================                   SortedMap
//        |             |             |
//       Set          List          Queue
//        |                           |
//    SortedSet                     Deque

//Properties of Collections
//1. Set: Can be unsorted. Cannot contain duplicate elements. Supports random access
//2. SortedSet: Cannot be unsorted. Cannot contain duplicate elements. Supports random access
//3. List: Can be unsorted. Can contain duplicate elements. Supports random access
//4. Queue: Can be unsorted. Can contain duplicate elements. Doesn't Support random access
//5. Deque: Can be unsorted. Can contain duplicate elements. Doesn't Support random access. Supports both queue and stack operations

//Properties of Maps
//1. Map: An object that maps keys to values. Keys can be unsorted. Cannot contain duplicate keys
//2. SortedMap: Keys cannot be unsorted.  Cannot contain duplicate keys

//GENERAL PURPOSE IMPLEMENTATIONS OF COLLECTION INTERFACES AND MAPS
//=============================================================================================================================
//|  Interfaces  |  Hash Table       |  Resizable Array  |  Tree             |  Linked List      |  Hash Table + Linked List  |
//|              |  Implementations  |  Implementations  |  Implementations  |  Implementations  |  Implementations           |
//=============================================================================================================================
//|  Set         |  HashSet          |                   |  TreeSet          |                   |  LinkedHashSet             |
//|  List        |                   |  ArrayList        |                   |  LinkedList       |                            |
//|  Queue       |                   |                   |                   |                   |                            |
//|  Deque       |                   |  ArrayDeque       |                   |  LinkedList       |                            |
//|  Map         |  HashMap          |                   |  TreeMap          |                   |  LinkedHashMap             |
//=============================================================================================================================

class Collections {
    static void demonstrateCollection() {
        //Initializing Collections
        //Any implementation of the "java.util.Collection" interface can be used as follows
        java.util.List<Integer> arrayList = new java.util.ArrayList<Integer>();

        //These following important methods can be invoked on any of the "java.util.Collection" implementations but the behavior may vary
        arrayList.add(456);
        arrayList.remove(0);
        arrayList.contains(10);
        arrayList.size();
        arrayList.clear();
        Integer[] arrayListArray = arrayList.toArray(new Integer[arrayList.size()]);

        //Traversing Collections
        //Any collection can be iterated using the "Enhanced For Loop"
        for (int num : arrayListArray) {
            System.out.println(num);
        }

        //We can also use "Aggregate Operations" on "collections"
        //NOTE: To use "Aggregate Operations" we must first acquire a "stream"
        //NOTE: "Lambda interfaces" for "Lambdas" used in "Aggregate Operations" are pre-defined so we don't have to
        arrayList.stream()
        .filter(element -> element > 10)
        .forEach(element -> System.out.println(element));

        //Converting Collections
        //We can convert one type of collection to another by taking advantage of their "Conversion Constructor"
        java.util.Set<Integer> setFromArrayList = new java.util.HashSet<Integer>(arrayList);

        //Bulk Operations of Collections
        //1. "coll.containsAll(targetColl)" : Returns true if the "targetColl" contains all of the "elements" in the specified "coll"
        //2. "coll.addAll(targetColl)" : Adds all of the "elements" in the specified "coll" to the "targetColl"
        //3. "coll.removeAll(targetColl)" : Removes from the "targetColl" all of its "elements" that are also contained in the "coll"
        //4. "coll.retainAll(targetColl)" : Removes from the "targetColl" all its "elements" that are not also contained in the "coll"
        //5. "coll.clear()" : Removes all elements from the "coll"
    }

    static void demonstrateIterators() {
        //NOTE: "Iterator" is an object that enables us to traverse through a "collection"
        //NOTE: "Iterator" objects are "generic" types
        //NOTE: To get an "Iterator" for a "collection", we must invoke its "iterator()" method
        java.util.List<Integer> arrayList = new java.util.ArrayList<Integer>();
        java.util.Iterator<Integer> iterator = arrayList.iterator();

        //We can use the "Iterator" as follows
        while (iterator.hasNext()) {
            Integer nextElement = iterator.next();
            if (nextElement > 10) {
                System.out.println(nextElement);
            } else {
                iterator.remove(); //"remove()" removes the last "element" retrieved by "next()"
            }
        }
    }

    static void demonstrateQueue() {
        //Introduction
        //NOTE: "Queues" are also known as "FIFO (First in, first out) lists"
        //NOTE: FIFO means that the first item to enter comes out first
        //NOTE: Although "Queues" are "sequential data structures", they are implemented as "circular data structures" to reduce repetitive element movements

        //Usage
        //1. Resource sharing among multiple consumers (eg: CPU scheduling)
        //2. Flow control of data (eg: Leaky bucket algorithm)
        //3. "Breadth First Traversal/ Level Order Traversal" of a "Graph/Tree"
        //4. Reverse a path in a "Binary Search Tree"

        //Operations Using Standard Methods
        //1. Reversing a "Queue"
        //1. Reversing a "Queue" using recursion 
        //1. Reversing the first K elements of a "Queue"
        //1. Interleave the first half of the "Queue" with second half
        //1. Sorting a "Queue" without extra space

        //Implementation
        //1. Using an "Array"
        //2. Using a "Linked List"
        //3. Using two "Stacks"
    }

    static void demonstrateDeque() {
        //Introduction
        //NOTE: "Deque" means "Double-Ended Queue"
        //NOTE: "Deque" is pronounced as "Deck"
        //NOTE: "Deque" supports both "Queue" and "Stack" operations
    }

    static void demonstrateSet() {
        //IMPLEMENTATIONS OF "Set" INTERFACE
        //1. HashSet: Stores its elements in a "Hash Table". It makes no guarantees concerning the order of iteration
        java.util.Set<Integer> hashSet = new java.util.HashSet<Integer>();
        //2. TreeSet: Stores its elements in a "Red-black Tree". Orders its elements based on their values. It is substantially slower than "HashSet"
        //3. LinkedHashSet
    }

    static void demonstrateList() {
        //IMPLEMENTATIONS OF "List" INTERFACE
        //1. ArrayList: A resizable array
        java.util.List<Integer> arrayList = new java.util.ArrayList<Integer>();
        //2. LinkedList (LinkedList also implements Deque interface making it a Double Ended Linked List)

        //Lists has the following additional important methods
        arrayList.set(0, 12);
        arrayList.get(0);

        //There are many static methods in the utility class "java.util.Collections" that work specifically on lists
        java.util.Collections.sort(arrayList);
        java.util.Collections.shuffle(arrayList);
        java.util.Collections.reverse(arrayList);
        java.util.Collections.swap(arrayList, 0, 1);
        java.util.Collections.rotate(arrayList, 5);
        java.util.Collections.binarySearch(arrayList, 5);
        java.util.List<Integer> newArrayList = new java.util.ArrayList<Integer>();
        java.util.Collections.copy(arrayList, newArrayList);
    }
}

class Maps {
    static void demonstrateMap() {
        //IMPLEMENTATIONS OF "Map" INTERFACE
        //1. HashMap
        java.util.Map<String, Integer> hashMap = new java.util.HashMap<String, Integer>();
        //2. TreeMap
        //2. LinkedHashMap

        //Map has the following additional important methods
        hashMap.put("Age", 12);
        hashMap.remove("Age", 12);
        hashMap.remove("Age");
        hashMap.replace("Age", 30);
    }
}

class NonCollections {
    void demonstrateStack() {
        //Introduction
        //NOTE: "Stacks" are also known as "LIFO (Last in, first out) lists"
        //NOTE: LIFO means that the last element to enter comes out first

        //Usage
        //1. Balancing of symbols
        //2. Infix to Postfix/Prefix conversion 
        //3. Redo-Undo feature
        //4. Forward-Backward feature in web browsers
        //5. Solving algorithms: Tower of Hanoi, tree traversals, stock span problem, histogram problem
        //6. Backtracking algorithms: Knight tour problem, rat in a maze, N queen problem and sudoku solver
        //7. Graph algorithms: like Topological Sorting and Strongly Connected Components
        //8. Sorting an "Arrays"

        //Operations Using Standard Methods
        //1. Reverse a "Stack" using recursion
        //2. Sort a "Stack" using recursion
        //3. Sort a "Stack" using a temporary "Stack"
        //4. Reverse a "Stack" without using extra space in O(n)

        //Implementation
        //NOTE: This "Stack" class is implemented using an "Array"
        //1. Using an "Array"
        //2. Using a "Linked List"
    }
}
