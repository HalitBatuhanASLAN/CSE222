package SpellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Structure.*;
/**
 * A spell checker application that checks words against a dictionary and provides suggestions
 * for misspelled words using edit distance algorithms.
 * The program loads a dictionary from a file and allows users to check words interactively.
 */
public class SpellChecker
{
    /**
     * The main entry point for the spell checker application.
     * Loads a dictionary from a file, then allows users to check words interactively.
     * 
     * @param args Command line arguments (not used)
     * @throws IOException If there is an error reading the dictionary file
     */
    public static void main(String[] args) throws IOException
    {
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Initial memory usage: " + (startMemory / 1024 / 1024) + " MB");

        int collisions = 0;

        long beforeDictionaryLoad = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        GTUHashSet<String> dictionary = new GTUHashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
        String word;
        while((word = reader.readLine()) != null)
            dictionary.add(word.trim());
        reader.close();
        System.out.println("Collisions on dictionary is " + dictionary.getCollisions());

        long afterDictionaryLoad = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory used by dictionary: " + ((afterDictionaryLoad - beforeDictionaryLoad) / 1024 / 1024) + " MB");
        System.out.println("Total memory usage after dictionary load: " + (afterDictionaryLoad / 1024 / 1024) + " MB");
        
        Scanner scanner = new Scanner(System.in);
        do
        {
            collisions = 0;
            System.out.print("Enter a word(to exit enter EXIT):");
            String input = scanner.nextLine().trim();
            long startTime = System.nanoTime();
            if(input.toString().equals("EXIT"))
            {
                scanner.close();
                break;
            }
            if(dictionary.contains(input))
                System.out.println("Correct.");
            else
            {
                System.out.println("Incorrect.");
                System.out.print("Suggestions: ");
                GTUHashSet<String> suggestions = generateEditDistance1(input, dictionary,collisions);
                collisions = suggestions.getCollisions();
                if(suggestions.size() == 0)
                    System.out.println("There is no any suggestion for that word. Please enter carefully");
                else
                    System.out.println(suggestions);
            }
            long endTime = System.nanoTime();
            System.out.printf("Lookup and suggestion took %.2f ms\n", (endTime - startTime) / 1e6);
            
            long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("Memory usage: " + (memoryUsed / 1024 / 1024) + " MB");
            
            System.out.println("Total collisions is " + collisions);
        }while(true);
        System.out.println("Exitting program...");
    }
    
    /**
     * Generates words with an edit distance of 1 from the input word.
     * Also calls methods to generate words with edit distance of 2.
     * 
     * @param input The input word to generate variants from
     * @param dictionary The dictionary to check variants against
     * @param collisions The number of collisions in the hash set
     * @return A set of valid word suggestions from the dictionary
     */
    private static GTUHashSet<String> generateEditDistance1(String input,GTUHashSet<String> dictionary,int collisions)
    {
        GTUHashSet<String> variants = new GTUHashSet<>();
        variant(input, variants,dictionary);

        generateEditDistance2(variants,dictionary);

        return variants;
    }

    /**
     * Generates words with an edit distance of 2 from the words with edit distance 1.
     * 
     * @param variants The set containing words with edit distance 1, and will contain words with edit distance 2
     * @param dictionary The dictionary to check variants against
     */
    private static void generateEditDistance2(GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        GTUHashSet<String> previous = variants;
        for (String input : previous)
        {
            variant(input, variants,dictionary);
        }
    }

    /**
     * Generates all possible variants of a word using different edit operations.
     * 
     * @param input The input word to generate variants from
     * @param variants The set to store valid variants
     * @param part The edit distance level (1 or 2)
     * @param dictionary The dictionary to check variants against
     */
    private static void variant(String input, GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        addCharacterVariants(input, variants,dictionary);
    
        deleteCharacterVariants(input, variants,dictionary);
    
        changeCharacterVariants(input, variants,dictionary);

        swapCharacterPositions(input, variants,dictionary);
    }
    
    /**
     * Generates variants by adding a character at each position in the input word.
     * 
     * @param input The input word to generate variants from
     * @param variants The set to store valid variants
     * @param dictionary The dictionary to check variants against
     */
    private static void addCharacterVariants(String input, GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        for (int i = 0; i <= input.length(); i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                String addedVariant = input.substring(0, i) + ch + input.substring(i);
                if (dictionary.contains(addedVariant))
                    variants.add(addedVariant);
            }
        }
    }
    
    /**
     * Generates variants by deleting a character at each position in the input word.
     * 
     * @param input The input word to generate variants from
     * @param variants The set to store valid variants
     * @param dictionary The dictionary to check variants against
     */
    private static void deleteCharacterVariants(String input, GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        for (int i = 0; i < input.length(); i++)
        {
            String deletedVariant = input.substring(0, i) + input.substring(i + 1);
            if (dictionary.contains(deletedVariant))
                variants.add(deletedVariant);
        }
    }

    /**
     * Generates variants by swapping adjacent characters in the input word.
     * 
     * @param input The input word to generate variants from
     * @param variants The set to store valid variants
     * @param dictionary The dictionary to check variants against
     */
    private static void swapCharacterPositions(String input, GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        for (int i = 0; i < input.length(); i++)
        {
            for (int j = i + 1; j < input.length(); j++)
            {
                char tmp = input.charAt(j);
                char tmp2 = input.charAt(i);
                String newVariant = input.substring(0, i) + tmp + input.substring(i + 1, j) + tmp2 + input.substring(j + 1);
                if (dictionary.contains(newVariant))
                    variants.add(newVariant);
            }
        }
    }   

    /**
     * Generates variants by changing each character in the input word to another letter.
     * 
     * @param input The input word to generate variants from
     * @param variants The set to store valid variants
     * @param dictionary The dictionary to check variants against
     */
    private static void changeCharacterVariants(String input, GTUHashSet<String> variants,GTUHashSet<String> dictionary)
    {
        for (int i = 0; i < input.length(); i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                String changedVariant = input.substring(0, i) + ch + input.substring(i + 1);
                if (dictionary.contains(changedVariant))
                    variants.add(changedVariant);
            }
        }
    }

}