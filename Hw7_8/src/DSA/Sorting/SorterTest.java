package DSA.Sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * A simple test class for sorting algorithms.
 * Tests are performed within the main method without using JUnit.
 */
public class SorterTest {
    
    // Counters to track test results
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println("GTUSorter Tests Starting...");
        
        // Collect all sorting algorithms in an array
        GTUSorter[] sorters = {
            new MyInsertSort(),
            new MySelectSort(),
            new MyQuickSort(),
            new MyQuickSort(new MyInsertSort(), 10),
            new MyQuickSort(new MySelectSort(), 10)
        };
        
        String[] sorterNames = {
            "MyInsertSort",
            "MySelectSort",
            "MyQuickSort",
            "MyQuickSort_MyInsertSort",
            "MyQuickSort_MySelectSort"
        };
        
        // Comparators
        Comparator<Integer> ascendingComparator = Integer::compareTo;
        Comparator<Integer> descendingComparator = (a, b) -> b.compareTo(a);
        
        // Test arrays
        Integer[] emptyArray = new Integer[0];
        Integer[] singleElementArray = new Integer[]{42};
        Integer[] sortedArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] reverseSortedArray = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] randomArray = new Integer[]{5, 2, 9, 1, 7, 6, 3, 8, 4, 10};
        Integer[] duplicateArray = new Integer[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        
        // Run tests for each sorting algorithm
        for (int i = 0; i < sorters.length; i++) {
            GTUSorter sorter = sorters[i];
            String sorterName = sorterNames[i];
            
            System.out.println("\n=== " + sorterName + " Tests ===");
            
            // Empty array test
            testEmptyArray(sorter, sorterName, emptyArray, ascendingComparator);
            
            // Single element array test
            testSingleElementArray(sorter, sorterName, singleElementArray, ascendingComparator);
            
            // Ascending sort test
            testAscendingSort(sorter, sorterName, randomArray, sortedArray, ascendingComparator);
            
            
            // Descending sort test
            testDescendingSort(sorter, sorterName, randomArray, reverseSortedArray, descendingComparator);
            
            // Duplicate elements test
            testDuplicateElements(sorter, sorterName, duplicateArray, ascendingComparator);
            
            // Already sorted array test
            testAlreadySortedArray(sorter, sorterName, sortedArray, ascendingComparator);
            
            // Reverse sorted array test
            testReverseSortedArray(sorter, sorterName, reverseSortedArray, sortedArray, ascendingComparator);
        }
        
        // Large array test
        testLargeArray(sorters, sorterNames);
        
        // String array test
        testStringArray(sorters, sorterNames);
        
        // Performance test
        testPerformance(sorters, sorterNames);
        
        // Show test results
        System.out.println("\n=== Test Results ===");
        System.out.println("Passed Tests: " + passedTests);
        System.out.println("Failed Tests: " + failedTests);
        System.out.println("Total Tests: " + (passedTests + failedTests));
        
        if (failedTests == 0) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println("Some tests failed, please check!");
        }
    }
    
    /**
     * Tests sorting an empty array.
     */
    private static void testEmptyArray(GTUSorter sorter, String sorterName, Integer[] emptyArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(emptyArray, emptyArray.length);
            sorter.sort(testArray, comparator);
            
            if (testArray.length == 0) {
                System.out.println("✓ " + sorterName + ": Empty array test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Empty array test failed");
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Empty array test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting an array with a single element.
     */
    private static void testSingleElementArray(GTUSorter sorter, String sorterName, Integer[] singleElementArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(singleElementArray, singleElementArray.length);
            Integer expectedValue = testArray[0];
            sorter.sort(testArray, comparator);
            
            if (testArray.length == 1 && testArray[0].equals(expectedValue)) {
                System.out.println("✓ " + sorterName + ": Single element array test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Single element array test failed");
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Single element array test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting an array in ascending order.
     */
    private static void testAscendingSort(GTUSorter sorter, String sorterName, Integer[] randomArray, Integer[] sortedArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(randomArray, randomArray.length);
            sorter.sort(testArray, comparator);
            
            boolean isEqual = Arrays.equals(testArray, sortedArray);
            boolean isSorted = isSorted(testArray, comparator);
            
            if (isEqual && isSorted) {
                System.out.println("✓ " + sorterName + ": Ascending sort test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Ascending sort test failed");
                System.out.println("   Expected: " + Arrays.toString(sortedArray));
                System.out.println("   Actual: " + Arrays.toString(testArray));
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Ascending sort test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting an array in descending order.
     */
    private static void testDescendingSort(GTUSorter sorter, String sorterName, Integer[] randomArray, Integer[] reverseSortedArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(randomArray, randomArray.length);
            sorter.sort(testArray, comparator);
            
            boolean isEqual = Arrays.equals(testArray, reverseSortedArray);
            boolean isSorted = isSorted(testArray, comparator);
            
            if (isEqual && isSorted) {
                System.out.println("✓ " + sorterName + ": Descending sort test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Descending sort test failed");
                System.out.println("   Expected: " + Arrays.toString(reverseSortedArray));
                System.out.println("   Actual: " + Arrays.toString(testArray));
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Descending sort test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting an array with duplicate elements.
     */
    private static void testDuplicateElements(GTUSorter sorter, String sorterName, Integer[] duplicateArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(duplicateArray, duplicateArray.length);
            Integer[] expectedArray = Arrays.copyOf(duplicateArray, duplicateArray.length);
            Arrays.sort(expectedArray);
            
            sorter.sort(testArray, comparator);
            
            boolean isEqual = Arrays.equals(testArray, expectedArray);
            boolean isSorted = isSorted(testArray, comparator);
            
            if (isEqual && isSorted) {
                System.out.println("✓ " + sorterName + ": Duplicate elements test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Duplicate elements test failed");
                System.out.println("   Expected: " + Arrays.toString(expectedArray));
                System.out.println("   Actual: " + Arrays.toString(testArray));
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Duplicate elements test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting an already sorted array.
     */
    private static void testAlreadySortedArray(GTUSorter sorter, String sorterName, Integer[] sortedArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(sortedArray, sortedArray.length);
            Integer[] expectedArray = Arrays.copyOf(sortedArray, sortedArray.length);
            
            sorter.sort(testArray, comparator);
            
            boolean isEqual = Arrays.equals(testArray, expectedArray);
            boolean isSorted = isSorted(testArray, comparator);
            
            if (isEqual && isSorted) {
                System.out.println("✓ " + sorterName + ": Already sorted array test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Already sorted array test failed");
                System.out.println("   Expected: " + Arrays.toString(expectedArray));
                System.out.println("   Actual: " + Arrays.toString(testArray));
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Already sorted array test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting a reverse-sorted array.
     */
    private static void testReverseSortedArray(GTUSorter sorter, String sorterName, Integer[] reverseSortedArray, Integer[] sortedArray, Comparator<Integer> comparator) {
        try {
            Integer[] testArray = Arrays.copyOf(reverseSortedArray, reverseSortedArray.length);
            
            sorter.sort(testArray, comparator);
            
            boolean isEqual = Arrays.equals(testArray, sortedArray);
            boolean isSorted = isSorted(testArray, comparator);
            
            if (isEqual && isSorted) {
                System.out.println("✓ " + sorterName + ": Reverse sorted array test passed");
                passedTests++;
            } else {
                System.out.println("✗ " + sorterName + ": Reverse sorted array test failed");
                System.out.println("   Expected: " + Arrays.toString(sortedArray));
                System.out.println("   Actual: " + Arrays.toString(testArray));
                failedTests++;
            }
        } catch (Exception e) {
            System.out.println("✗ " + sorterName + ": Reverse sorted array test failed - Error: " + e.getMessage());
            failedTests++;
        }
    }
    
    /**
     * Tests sorting a large array.
     */
    private static void testLargeArray(GTUSorter[] sorters, String[] sorterNames) {
        System.out.println("\n=== Large Array Test ===");
        
        int size = 1000;
        Integer[] largeArray = new Integer[size];
        Random random = new Random(42); // Fixed seed for consistent results
        
        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(10000);
        }
        
        Integer[] expectedArray = Arrays.copyOf(largeArray, largeArray.length);
        Arrays.sort(expectedArray);
        
        Comparator<Integer> comparator = Integer::compareTo;
        
        for (int i = 0; i < sorters.length; i++) {
            GTUSorter sorter = sorters[i];
            String sorterName = sorterNames[i];
            
            try {
                Integer[] testArray = Arrays.copyOf(largeArray, largeArray.length);
                
                sorter.sort(testArray, comparator);
                
                boolean isEqual = Arrays.equals(testArray, expectedArray);
                boolean isSorted = isSorted(testArray, comparator);
                
                if (isEqual && isSorted) {
                    System.out.println("✓ " + sorterName + ": Large array test passed");
                    passedTests++;
                } else {
                    System.out.println("✗ " + sorterName + ": Large array test failed");
                    failedTests++;
                }
            } catch (Exception e) {
                System.out.println("✗ " + sorterName + ": Large array test failed - Error: " + e.getMessage());
                failedTests++;
            }
        }
    }
    
    /**
     * Tests sorting an array of strings.
     */
    private static void testStringArray(GTUSorter[] sorters, String[] sorterNames) {
        System.out.println("\n=== String Array Test ===");
        
        String[] strings = {"banana", "apple", "pear", "orange", "grape"};
        String[] expected = {"apple", "banana", "grape", "orange", "pear"};
        
        Comparator<String> comparator = String::compareTo;
        
        for (int i = 0; i < sorters.length; i++) {
            GTUSorter sorter = sorters[i];
            String sorterName = sorterNames[i];
            
            try {
                String[] testArray = Arrays.copyOf(strings, strings.length);
                
                sorter.sort(testArray, comparator);
                
                boolean isEqual = Arrays.equals(testArray, expected);
                boolean isSorted = isSorted(testArray, comparator);
                
                if (isEqual && isSorted) {
                    System.out.println("✓ " + sorterName + ": String array test passed");
                    passedTests++;
                } else {
                    System.out.println("✗ " + sorterName + ": String array test failed");
                    System.out.println("   Expected: " + Arrays.toString(expected));
                    System.out.println("   Actual: " + Arrays.toString(testArray));
                    failedTests++;
                }
            } catch (Exception e) {
                System.out.println("✗ " + sorterName + ": String array test failed - Error: " + e.getMessage());
                failedTests++;
            }
        }
    }
    
    /**
     * Tests the performance of sorting algorithms.
     */
    private static void testPerformance(GTUSorter[] sorters, String[] sorterNames) {
        System.out.println("\n=== Performance Test ===");
        
        int size = 10000;
        Integer[] largeArray = new Integer[size];
        Random random = new Random(42);
        
        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(10000);
        }
        
        Comparator<Integer> comparator = Integer::compareTo;
        
        for (int i = 0; i < sorters.length; i++) {
            GTUSorter sorter = sorters[i];
            String sorterName = sorterNames[i];
            
            try {
                Integer[] testArray = Arrays.copyOf(largeArray, largeArray.length);
                
                long startTime = System.nanoTime();
                sorter.sort(testArray, comparator);
                long endTime = System.nanoTime();
                
                long duration = (endTime - startTime) / 1000000; // in milliseconds
                
                boolean isSorted = isSorted(testArray, comparator);
                
                if (isSorted) {
                    System.out.println("✓ " + sorterName + ": Performance test passed - Time: " + duration + " ms");
                    passedTests++;
                } else {
                    System.out.println("✗ " + sorterName + ": Performance test failed - Array not sorted");
                    failedTests++;
                }
            } catch (Exception e) {
                System.out.println("✗ " + sorterName + ": Performance test failed - Error: " + e.getMessage());
                failedTests++;
            }
        }
    }
    
    /**
     * Helper method to check if an array is sorted according to the given comparator.
     */
    private static <T> boolean isSorted(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
