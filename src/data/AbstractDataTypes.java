package data;

public class AbstractDataTypes {
    public static void test() {
        BinarySearchTree bst = new BinarySearchTree(new int[] {8, 3, 10, 1, 6, 14, 4, 7, 13});
        bst.print("level");
    }
}

//IMPLEMENTING ABSTRACT DATA TYPES USING JAVA ARRAYS
class CompleteBinaryTree {
    //Introduction
    //NOTE: A "Complete Binary Tree" has all its "non-leaf nodes" completely filled except possibly the last which can have completely or partially filled
    //NOTE: Therefore a "Complete Binary Tree" can be represented with an "Array" ("Trees" are usually represented using "Linked Lists")
    //NOTE: A "Binary Heap" can be built by "Heapifying" a "Complete Binary Tree"
    //NOTE: After converting a "Complete Binary Tree" implemented using an "Array" into a "Binary Heap", Its "Array" can be sorted using "Heap Sort"
    
    //Implementation
    //NOTE: This "CompleteBinaryTree" class is implemented using an "Array"
    //NOTE: "Last non-leaf node index" equals to "(numOfNodes/2)-1" when the "Array" indices start at 0
    //NOTE: "Left child index" of a given "node index" equals to "(2*nodeIndex)+1"
    //NOTE: "Right child index" of a given "node index" equals to "(2*nodeIndex)+2"
    //NOTE: "Parent index" of a given "node index" equals to "(nodeIndex-1)/2" (This is true only with integer division)

    private int[] internalArray;

    CompleteBinaryTree(int[] nodeData) {
        internalArray = nodeData;
    }

    //Retrieving Methods
    int size() {
        return internalArray.length;
    }

    int[] toArray() {
        int[] copyOfInternalArray = new int[internalArray.length];
        System.arraycopy(this.internalArray, 0, copyOfInternalArray, 0, internalArray.length);
        return copyOfInternalArray;
    }

    void print() {
        StringBuilder stringifiedInternalArray = new StringBuilder(internalArray.length + " : ");
        for (int i = 0; i < internalArray.length; i++) {
            stringifiedInternalArray.append(internalArray[i] + " ");
        }
        System.out.println(stringifiedInternalArray);
    }

    //Updating Methods
    //NOTE: "Heapification" procedure can be applied to a "node" only if its "children nodes" are "heapified". Therefore "heapification" must be performed in the bottom up order
    void heapify() {
        //Heapification Traversal Procedure
        //1. Find the "last(bottom right) non-leaf node index" using the relevant formula (because "leaf nodes" are already "heapified")
        //2. Start "heapifying" each node (according to the below "heapifyNode" procedure) starting from "last non-leaf node" towards left and up
        int lastNonLeafNodeIndex = (internalArray.length / 2) - 1;
        for (int i = lastNonLeafNodeIndex; i >= 0; i--) {
            heapifyNode(i, internalArray.length);
        }
    }
    
    private void heapifyNode(int nodeIndex, int numOfNodes) {
        //Heapification of a Node
        //1. If "left child > node && left child >= right child" (equality is to support duplicates) then swap with "node"
        //2. Else if "right child > node && right child >= left child" (equality is to support duplicates) then swap with "node"
        //3. If swapped "heapify node" at its new location ("node" is now a "child")
        int leftChildIndex = (2 * nodeIndex) + 1;
        int rightChildIndex = (2 * nodeIndex) + 2;

        //Check if the node have its leftChild. rightChild is present only if there is a leftChild
        if (!(leftChildIndex >= numOfNodes)) {
            //Check if the node have the rightChild
            if (!(rightChildIndex >= numOfNodes)) {
                //Node have both children present
                //Promote only the largest amongst currentNode, leftChild and rightChild
                if (internalArray[leftChildIndex] > internalArray[nodeIndex] && internalArray[leftChildIndex] >= internalArray[rightChildIndex]) {
                    //leftChild is the largest, swap node with leftChild
                    int nodeValue = internalArray[nodeIndex];
                    internalArray[nodeIndex] = internalArray[leftChildIndex];
                    internalArray[leftChildIndex] = nodeValue;
                    heapifyNode(leftChildIndex, numOfNodes);
                } else if (internalArray[rightChildIndex] > internalArray[nodeIndex] && internalArray[rightChildIndex] >= internalArray[leftChildIndex]) {
                    //rightChild is the largest, swap node with rightChild
                    int nodeValue = internalArray[nodeIndex];
                    internalArray[nodeIndex] = internalArray[rightChildIndex];
                    internalArray[rightChildIndex] = nodeValue;
                    heapifyNode(rightChildIndex, numOfNodes);
                }
            } else {
                //Node only have its leftChild present
                if (internalArray[leftChildIndex] > internalArray[nodeIndex]) {
                    //leftChild is larger than node, swap node with leftChild
                    int nodeValue = internalArray[nodeIndex];
                    internalArray[nodeIndex] = internalArray[leftChildIndex];
                    internalArray[leftChildIndex] = nodeValue;
                    heapifyNode(leftChildIndex, numOfNodes);
                }
            }
        }
    }

    //NOTE: "Heap Sort" can only be applied to a "Binary Heap". In this case, a "heapified Complete Binary Tree"
    void sortHeap() {
        //Heap Sort Procedure
        //1. Repeat following procedure until the "Binary Heap" has only one "node" left
            //1.1 Swap the "last leaf" with the "root node" (Now the "root node" is sorted)
            //1.2 Remove and place "root node" in a new "Array" as its next element (This placement is responsible for the ascending or descending order)
            //1.3 Heapify recursively starting from the "new root node"
        //2. Finally place the "last node" of the "Binary Heap" as last element of the new "Array"

        //NOTE: Following algorithm updates the same "internal Array" as the "Binary Heap" to optimize space usage (It does not remove the sorted "root node" into a new "Array")
        //NOTE: Therefore the following algorithm produces a reversed "Array" than expected (eg. "Min Heap" produces a "descending Array" rather than an "ascending Array")
        int numOfNodes = internalArray.length;
        while (numOfNodes > 1) {
            int firstNode = internalArray[0];
            internalArray[0] = internalArray[numOfNodes - 1];
            internalArray[numOfNodes - 1] = firstNode;
            numOfNodes--;
            
            heapifyNode(0, numOfNodes);
        }
    }
}

//Binary Search Tree
class BinarySearchTree {
    Node rootNode = null;
    int size = 0;
    
    class Node {
        int data;
        Node parentNode;
        Node leftChildNode = null;
        Node rightChildNode = null;
        
        Node(Node parentNode, int data) {
            this.data = data;
            this.parentNode = parentNode;
        }
    }

    BinarySearchTree(int[] initialNodeData) {
        for (int data : initialNodeData) {
            add(data);
        }
    }
    
    //Creating Methods
    void add(int data) {
        if (size == 0) {
            Node newNode = new Node(null, data);
            rootNode = newNode;
            size++;
        } else {
            Node currentNode = rootNode;
            while (true) {
                if (data <= currentNode.data && currentNode.leftChildNode != null) {
                    currentNode = currentNode.leftChildNode;
                } else if (data > currentNode.data && currentNode.rightChildNode != null) {
                    currentNode = currentNode.rightChildNode;
                } else {
                    break;
                }
            }

            Node newNode = new Node(currentNode, data);
            if (data <= currentNode.data) {
                currentNode.leftChildNode = newNode;
            } else {
                currentNode.rightChildNode = newNode;
            }
            size++;
        }
    }

    //Retrieving Methods
    int size() {
        return size;
    }

    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            printPreOrder(node.leftChildNode);
            printPreOrder(node.rightChildNode);
        }
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.leftChildNode);
            System.out.print(node.data + " ");
            printInOrder(node.rightChildNode);
        }
    }

    private void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.leftChildNode);
            printPostOrder(node.rightChildNode);
            System.out.print(node.data + " ");
        }
    }

    private void printLevelOrder() {
        StringBuilder stringifiedTree = new StringBuilder();
        Queue printQueue = new Queue(4);

        if (rootNode != null) {
            printQueue.enqueue(rootNode);
            while (!printQueue.isEmpty()) {
                Node nextNode = (Node) printQueue.dequeue();
                stringifiedTree.append(nextNode.data + " ");
                if (nextNode.leftChildNode != null) {
                    printQueue.enqueue(nextNode.leftChildNode);
                }
                if (nextNode.rightChildNode != null) {
                    printQueue.enqueue(nextNode.rightChildNode);
                }
            }
        }
        
        System.out.println(stringifiedTree);
    }

    void print(String order) {
        System.out.print(size + " : ");
        switch (order) {
            case "pre":
                printPreOrder(rootNode);
                break;
            case "in":
                printInOrder(rootNode);
                break;
            case "post":
                printPostOrder(rootNode);
                break;
            case "level":
                printLevelOrder();
                break;
            default:
                System.out.println("Invalid print order");
        }
        System.out.print("\n");
    }
}
