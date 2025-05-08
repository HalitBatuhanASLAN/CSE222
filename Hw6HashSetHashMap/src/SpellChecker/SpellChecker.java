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
        while ((word = reader.readLine()) != null)
        {
            dictionary.add(word.trim());
        }
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
            if (dictionary.contains(input))
            {
                System.out.println("Correct.");
            }
            else 
            {
                System.out.println("Incorrect.");
                System.out.print("Suggestions: ");
                GTUArrayList<String> suggestions = new GTUArrayList<>();
                for (String variant : generateEditDistance1(input))
                {
                    if (dictionary.contains(variant))
                    {
                        suggestions.add(variant);
                    }
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

    private static GTUArrayList<String> generateEditDistance1(String input)
    {
        GTUArrayList<String> variants = new GTUArrayList<>();
        System.out.println("distance 1 variants;*********");
        variant(input, variants);
        System.out.println("distance 2 variants;*********");
        //generateEditDistance2(variants);
        return variants;
    }

    private static void generateEditDistance2(GTUArrayList<String> variants)
    {
        for(String input : variants)
            variant(input, variants);
    }


    private static void variant(String input,GTUArrayList<String> variants)
    {
        //for distance 1 character variants
        for(int i = 0;i<=input.length();i++)
        {
            //adding character
            for(char ch = 'a';ch <= 'z';ch++)
            {
                String addedVairant = input.substring(0,i) + ch + input.substring(i);
                //System.out.println(addedVairant);
                if(!containsChecker(addedVairant,variants))
                    variants.add(addedVairant);
            }
        }
        
        for(int i = 0;i<input.length();i++)
        {
            //deleting character
            String deletedVariant = input.substring(0,i) + input.substring(i+1);
            //System.out.println(deletedVariant);
            if(!containsChecker(deletedVariant,variants))
                variants.add(deletedVariant);

            //changing character
            for(char ch = 'a';ch <= 'z';ch++)
            {
                String changedVariant = input.substring(0,i) + ch + input.substring(i+1);
                //System.out.println(changedVariant);
                if(!containsChecker(changedVariant,variants))
                    variants.add(changedVariant);
            }

            //changing positions
            for(int j = i+1;j<input.length();j++)
            {
                char tmp = input.charAt(j);
                char tmp2 = input.charAt(i);
                String newVariant = input.substring(0, i) + tmp + input.substring(i+1, j) + tmp2 + input.substring(j+1);
                //System.out.println(newVariant);
                if(!containsChecker(newVariant,variants))
                    variants.add(newVariant);
            }
        }
    }

    private static boolean containsChecker(String input,GTUArrayList<String> variants)
    {
        for (String string : variants)
        {
            if(input.equals(string))
                return true;
        }
        return false;
    }
}











    
    
    /*
    private static GTUHashSet<String> generateEditDistance1(String input)
    {
        GTUHashSet<String> variants = new GTUHashSet<>();
        variant(input, variants);
        return variants;
    }

    private static GTUHashSet<String> generateEditDistance2(String input)
    {
        GTUHashSet<String> distance1Variants = generateEditDistance1(input);
        GTUHashSet<String> distance2Variants = new GTUHashSet<>();

        for (String variant : distance1Variants)
        {
            GTUHashSet<String> newVariants = generateEditDistance1(variant);
            distance2Variants.addAll(newVariants);

            // Performansı kontrol altında tutmak için bir sınır koyabilirsiniz
            if (distance2Variants.size() > 10000) {
                System.out.println("Too many variants, stopping further generation.");
                break;
            }
        }
        return distance2Variants;
    }

    private static void variant(String input,GTUHashSet<String> variants)
    {
        //for distance 1 character variants
        for(int i = 0;i<=input.length();i++)
        {
            //adding character
            for(char ch = 'a';ch <= 'z';ch++)
            {
                String addedVairant = input.substring(0,i) + ch + input.substring(i);
                //System.out.println(addedVairant);
                if(!containsChecker(addedVairant,variants))
                    variants.add(addedVairant);
            }
        }
        
        for(int i = 0;i<input.length();i++)
        {
            //deleting character
            String deletedVariant = input.substring(0,i) + input.substring(i+1);
            //System.out.println(deletedVariant);
            if(!containsChecker(deletedVariant,variants))
                variants.add(deletedVariant);

            //changing character
            for(char ch = 'a';ch <= 'z';ch++)
            {
                String changedVariant = input.substring(0,i) + ch + input.substring(i+1);
                //System.out.println(changedVariant);
                if(!containsChecker(changedVariant,variants))
                    variants.add(changedVariant);
            }

            //changing positions
            for(int j = i+1;j<input.length();j++)
            {
                char tmp = input.charAt(j);
                char tmp2 = input.charAt(i);
                String newVariant = input.substring(0, i) + tmp + input.substring(i+1, j) + tmp2 + input.substring(j+1);
                //System.out.println(newVariant);
                if(!containsChecker(newVariant,variants))
                    variants.add(newVariant);
            }
        }

    }
    private static boolean containsChecker(String input,GTUHashSet<String> variants)
    {
        for (int i = 0; i < variants.size(); i++) {
            if (input.equals(variants.get(i))) {
                return true;
            }
        }
        return false;
    }*/