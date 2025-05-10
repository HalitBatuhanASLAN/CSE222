package SpellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Structure.*;

public class SpellChecker
{
    public static void main(String[] args) throws IOException
    {
        GTUHashSet<String> dictionary = new GTUHashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
        String word;
        while((word = reader.readLine()) != null)
            dictionary.add(word.trim());
        reader.close();
        Scanner scanner = new Scanner(System.in);
        do
        {
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
                GTUArrayList<String> suggestions = new GTUArrayList<>();
                for(String variant : generateEditDistance1(input))
                {
                    if (dictionary.contains(variant))
                        suggestions.add(variant);
                }
                if(suggestions.size() == 0)
                    System.out.println("There is no any suggestion for that word. Please enter carefully");
                else
                    System.out.println(suggestions);
            }
            long endTime = System.nanoTime();
            System.out.printf("Lookup and suggestion took %.2f ms\n", (endTime - startTime) / 1e6);
        }while(true);
        System.out.println("Exitting program...");
    }

    private static GTUHashSet<String> generateEditDistance1(String input)
    {
        GTUHashSet<String> variants = new GTUHashSet<>();
        variant(input, variants,1);
        /*if(variants.size() >= 10000)
            return variants;*/
        generateEditDistance2(variants);
        /*if(variants.size() >= 10000)
            return variants;*/
        return variants;
    }

    private static void generateEditDistance2(GTUHashSet<String> variants)
    {
        GTUHashSet<String> previous = variants;
        for (String input : previous)
        {
            variant(input, variants,2);
            /*if(variants.size() >= 10000)
                return;*/
        }
    }

    private static void variant(String input, GTUHashSet<String> variants, int part)
    {
        addCharacterVariants(input, variants);
    
        deleteCharacterVariants(input, variants);
    
        changeCharacterVariants(input, variants);
    
        /*if(part == 1)
            swapCharacterPositions(input, variants);*/
        swapCharacterPositions(input, variants);
        
        /*if(variants.size() >= 10000)
            return;*/
    }
    
    private static void addCharacterVariants(String input, GTUHashSet<String> variants)
    {
        for (int i = 0; i <= input.length(); i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                String addedVariant = input.substring(0, i) + ch + input.substring(i);
                if (!variants.contains(addedVariant))
                    variants.add(addedVariant);
                /*if(variants.size() >= 10000)
                    return;*/
            }
        }
    }
    
    private static void deleteCharacterVariants(String input, GTUHashSet<String> variants)
    {
        for (int i = 0; i < input.length(); i++)
        {
            String deletedVariant = input.substring(0, i) + input.substring(i + 1);
            if (!variants.contains(deletedVariant))
                variants.add(deletedVariant);
            /*if(variants.size() >= 10000)
                return;*/
        }
    }

    private static void swapCharacterPositions(String input, GTUHashSet<String> variants)
    {
        for (int i = 0; i < input.length(); i++)
        {
            for (int j = i + 1; j < input.length(); j++)
            {
                char tmp = input.charAt(j);
                char tmp2 = input.charAt(i);
                String newVariant = input.substring(0, i) + tmp + input.substring(i + 1, j) + tmp2 + input.substring(j + 1);
                if (!variants.contains(newVariant))
                    variants.add(newVariant);
                /*if(variants.size() >= 10000)
                    return;*/
            }
        }
    }   
    private static void changeCharacterVariants(String input, GTUHashSet<String> variants)
    {
        for (int i = 0; i < input.length(); i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                String changedVariant = input.substring(0, i) + ch + input.substring(i + 1);
                if (!variants.contains(changedVariant))
                    variants.add(changedVariant);
            }
        }
    }

}


