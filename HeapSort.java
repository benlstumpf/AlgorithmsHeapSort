
import java.util.Random;
import java.lang.Math;
//@author Ben Stumpf

class HeapSort {
    /**
     * Runs the expierement
     * @param args not applicable
     */
    public static void main(String[] args) {
        int numberOfRuns = 10000;
        int maxNumberOfElementsFactor = 100000;
        System.out.println("Number of Elements,Total Opperations, Number of Runs, Average Opperations, Differance of Expected and Counted");
        for (int numberOfElementsFactor = 10; numberOfElementsFactor <= maxNumberOfElementsFactor; numberOfElementsFactor += 10) {
            averageRunning(numberOfRuns, numberOfElementsFactor);
        }
    }

    /**
     * Finds the log base 2 of a number. used for calculating the expected
     * average number of operations.
     * @param  a The number that is needing the log found
     * @return   returns log base 2
     */
    public static double log2(double a){
        return (Math.log10(a) / Math.log10(2));
    }

    /**
     * Runs the expierement for a data set size and prints the results
     * @param numberOfRuns controls how robust the data set is
     * @param sizeOfArray  size of data set
     */
    public static void averageRunning(int numberOfRuns, int sizeOfArray) {
        double totalOperations = 0;
        double averageOperations = 0;
        double expectedAverageOperations = sizeOfArray*log2(sizeOfArray);
        for (int i = 1; numberOfRuns >= i; i++) {
            int[] array = new int[sizeOfArray];
            array = createRandomIntArray(sizeOfArray);
            int runOpperations = Sort(array);
            //System.out.println("Run Opperations " + runOpperations);
            totalOperations = totalOperations + runOpperations;
        }
        averageOperations = totalOperations / numberOfRuns;
        System.out.print(sizeOfArray + ",");
        System.out.print(totalOperations + ",");
        System.out.print(numberOfRuns + ",");
        System.out.print((totalOperations / numberOfRuns) + ",");
        System.out.println((averageOperations/expectedAverageOperations));
    }

    /**
     * Heap sort funtion, named in order to allow code reuse
     * @param  int[] valuetoSort    execpts an array that needs to be sorted
     * @return       returns        the number of operations counted though the algorithm
     */
    public static int Sort(int[] valuetoSort) {
        int operations = 0;                                     //start counting
        int heapSize = valuetoSort.length;
        operations++;
        operations = operations + biuldMaxHeap(valuetoSort);
        for (int index = (valuetoSort.length-1); index >= 2; index--) {
            int exchangeValue = valuetoSort[1];
            operations++;
            valuetoSort[1] = valuetoSort[index];
            operations++;
            valuetoSort[index] = exchangeValue;
            operations++;
            heapSize = heapSize - 1;
            operations++;
            operations = operations + maxHeapify(valuetoSort, 1, heapSize);
        }
        return operations;
    }

    /**
     * gives the partent of the node that is supplyed
     * @param  startingIndex the original node
     * @return               the parent of the node
     */
    public static int parent(int startingIndex) {
        return ((int)Math.ceil((startingIndex / 2)-1));
    }

    /**
     * gives the left child of the node that is supplyed
     * @param  startingIndex the original node
     * @return               the left child of the node
     */
    public static int leftIndex(int startingIndex) {
        return ((2 * startingIndex) + 2);
    }

    /**
     * gives the right child of the node that is supplyed
     * @param  startingIndex the original node
     * @return               the right child of the node
     */
    public static int rightIndex(int startingIndex) {
        return ((2 * startingIndex) + 1);
    }

    /**
     * used in the sorting algorithm in order to give the data a predictable structure
     * @param  Array         the full data set
     * @param  index         where to start in the data set
     * @param  startingIndex used to calculate the subset of the array that is the data set for this process
     * @return               the number of operations it took in order to run the process
     */
    public static int maxHeapify(int[] Array, int index, int startingIndex) {
        int heapSize = Array.length -startingIndex;
        int operations = 0;
        int l = leftIndex(index);
        operations++;
        int r = rightIndex(index);
        operations++;
        int largest;
        if (l <= heapSize && Array[l] > Array[index]) {
            largest = l;
            operations++;
        } else {
            largest = index;
            operations++;
        }
        if (r <= heapSize && Array[r] > Array[largest]) {
            largest = r;
            operations++;
        }
        if (largest != index) {
            int exchangeValue = Array[index];
            operations++;
            Array[index] = Array[largest];
            operations++;
            Array[largest] = exchangeValue;
            operations++;
            operations = operations + maxHeapify(Array, largest, heapSize);
        }
        operations++;
        return operations;
    }

    /**
     * Takes a completely unstructured data set and stuctures it such that the algoithm can sort the data
     * @param  Array the data set
     * @return       the number of operations it took in order to run the process
     */
    public static int biuldMaxHeap(int[] Array) {
        int operations = 0;
        for (int index = (int) Array.length / 2; index >= 1; index--) {
            operations = operations + maxHeapify(Array, index, index);
            operations++;
        }
        return operations;
    }

    /**
     * Funtion given by prof in order to create random sets of values in arrays
     *
     * @param int size execpts a value for in order to know what to create
     * @return Returns the array created
     */
    public static int[] createRandomIntArray(int size) {
        int[] answer = new int[size];
        Random generator = new Random();
        for (int index = 0; index < answer.length; index++) {
            answer[index] = generator.nextInt(100) + 1;
        }
        return answer;
    }

    /**
     * A function to print arrays, toString was not working
     *
     * @param int[] anArray execepts an array to print the elements of
     */
    public static void printArray(int[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
    }
}
