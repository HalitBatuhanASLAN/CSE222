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
            System.out.print("Enter a word:(to exit enter EXIT) ");
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
                /*for (String variant : generateEditDistance1(input))
                {
                    if (dictionary.contains(variant))
                    {
                        suggestions.add(variant);
                    }
                }*/
                System.out.println(suggestions);
            }
            long endTime = System.nanoTime();
            System.out.printf("Lookup and suggestion took %.2f ms\n", (endTime - startTime) / 1e6);
        }while(true);
        System.out.println("Exitting program...");
    }
    private static void generateEditDistance1(String input)
    {
        
    }
}