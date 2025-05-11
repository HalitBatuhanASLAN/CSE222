package TestClasses;

import java.lang.reflect.Method;

/**
 * TestRunner class to execute all test methods in the provided test classes using reflection.
 */
public class TestRunner
{
    /**
     * Main method that runs all tests in GTUHashMapTest and GTUHashSetTest.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args)
    {
        runTests(new GTUHashMapTest());
        runTests(new GTUHashSetTest());
    }

    /**
     * Runs all declared methods of a given test class instance.
     * Each method is invoked via reflection.
     *
     * @param testClassInstance The instance of the test class whose methods will be invoked.
     */
    private static void runTests(Object testClassInstance)
    {
        Class<?> clazz = testClassInstance.getClass();
        for (Method m : clazz.getDeclaredMethods())
        {
            try
            {
                m.invoke(testClassInstance);
            }catch(Exception e)
            {
                //ignored of exceptions
            }
        }
    }
}