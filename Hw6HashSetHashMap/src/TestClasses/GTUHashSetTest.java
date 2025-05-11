package TestClasses;

import Structure.*;

/**
 * Unit test class for testing the basic functionalities of the GTUHashSet class.
 * Each test method verifies a specific operation and prints the result to the console.
 */
public class GTUHashSetTest
{
    /**
     * Tests whether the add and contains methods work correctly.
     * Adds an element and checks if it exists in the set.
     */
    public void add_and_contains_test()
    {
        GTUHashSet<String> tmp = new GTUHashSet<>();
        tmp.add("key");
        checker(true,tmp.contains("key"),"add and contains");
    }

    /**
     * Tests whether the size method returns the correct number of elements in the set.
     */
    public void size_test()
    {
        GTUHashSet<String> tmp = new GTUHashSet<>();
        tmp.add("key");
        tmp.add("key2");
        checker(2,tmp.size(),"size");
    }

    /**
     * Tests if the remove method successfully deletes an element from the set.
     */
    public void remove_test()
    {
        GTUHashSet<String> tmp = new GTUHashSet<>();
        tmp.add("key");
        tmp.remove("key");
        checker(false,tmp.contains("key"),"remove");
    }

    /**
     * Tests whether the iterator correctly iterates over all elements in the set.
     */
    public void testIterator()
    {
        GTUHashSet<String> tmp = new GTUHashSet<>();
        tmp.add("key1");
        tmp.add("key2");
        int count = 0;
        for (String s : tmp)
            count++;
        checker(2, count,"iterater");
    }

    /**
     * Checks if the expected and actual values are equal and prints the result of the operation.
     *
     * @param expected the expected value
     * @param current the actual value
     * @param operationName the name of the operation being tested
     */
    private void checker(Object expected,Object current,String operationName)
    {
        if(expected == current)
            System.out.println("Your code is working for " + operationName);
        else
            System.out.println("Your code is not working for " + operationName);
    }
}
