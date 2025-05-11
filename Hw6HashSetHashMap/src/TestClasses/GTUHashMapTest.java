package TestClasses;

import Structure.*;

/**
 * Unit test class for testing the basic functionalities of the GTUHashMap class.
 * Each test method checks the correctness of a specific operation and prints the result to the console.
 */
public class GTUHashMapTest
{
    /**
     * Tests whether the put and get methods work correctly.
     * Adds a value with a key and checks if it can be retrieved properly.
     */
    public void put_and_get_test()
    {
        GTUHashMap<String,String> tmp = new GTUHashMap<>();
        tmp.put("key","value");
        checker("value",tmp.get("key"),"put and get");
    }

    /**
     * Tests if updating the value for an existing key works correctly.
     */
    public void replace_test()
    {
        GTUHashMap<String,String> tmp = new GTUHashMap<>();
        tmp.put("key","value");
        tmp.put("key","newValue");
        checker("newValue",tmp.get("key"),"replacing ");
    }

    /**
     * Tests whether the size method returns the correct number of entries in the map.
     */
    public void size_test()
    {
        GTUHashMap<String,String> tmp = new GTUHashMap<>();
        tmp.put("key","value");
        tmp.put("key2","newValue");
        checker("2",tmp.size(),"size ");
    }

    /**
     * Tests if the remove method successfully deletes a key-value pair from the map.
     */
    public void remove_test()
    {
        GTUHashMap<String,String> tmp = new GTUHashMap<>();
        tmp.put("key","value");
        tmp.remove("key");
        checker(null,tmp.get("key"),"removing ");
    }

    /**
     * Tests whether the containsKey method correctly identifies the presence of keys.
     */
    public void contains_test()
    {
        GTUHashMap<String,String> tmp = new GTUHashMap<>();
        tmp.put("key","value");
        checker(true,tmp.containsKey("key"),"contains ");
        checker(false,tmp.containsKey("key2"),"contains ");
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