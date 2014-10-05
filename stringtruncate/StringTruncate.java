
package stringtruncate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class StringTruncate {
    
    /**
     * This method takes a word or phrase and truncates it based on the
     * desired maximum length.
     * 
     * @param word the input word or phrase
     * @param maxLength the maximum desired length of the word/phrase
     * @return the truncated word or phrase
     */
    private static String wordTruncate(String word, int maxLength) { 
        if (word.length() <= maxLength) {
            return word;
        } else {
            String[] parts = word.split(" |-|,|_|\\.");
            String truncatedWord = "";
            if (parts[0].length() <= maxLength) {
                truncatedWord = parts[0];
                boolean eliminateSpace;
                if (word.length() > 2 * maxLength) {
                    eliminateSpace = true;
                } else {
                    eliminateSpace = false;
                }
                String[] newParts = Arrays.copyOfRange(parts, 1, parts.length-1);
                for (String part : newParts) {
                    String newWord = truncatedWord + " " + part;
                    String newWord2 = truncatedWord + part;
                    if (newWord.length() <= maxLength && eliminateSpace == false) {
                        truncatedWord += " " + part;
                    }
                    if (newWord2.length() <= maxLength) {
                        truncatedWord += part;
                    }
                }
            } else {
                truncatedWord = parts[0];
                if (truncatedWord.contains("ght")) {
                    truncatedWord = truncatedWord.replaceAll("ght", "te");
                }
                if (truncatedWord.length() > maxLength) {
                    truncatedWord = truncatedWord.replaceAll("a|e|i|o|u", "");
                }
                if (truncatedWord.length() > maxLength) {
                    truncatedWord = truncatedWord.substring(0, maxLength);
                }
            }
            return truncatedWord;
        }
    }
    
    /**
     * This method takes in 2D array of data and applies wordTruncate to each
     * item in the array. 
     * 
     * @param data the input 2D array that has its contents truncated
     * @param maxLength the maximum desired length for each word/phrase
     */
    private static void fileTruncate(String[][] data, int maxLength) {
        for (String[] column : data) {
            for (int x = 0; x < column.length; x++) {
                column[x] = wordTruncate(column[x], maxLength);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean complete = false;
        Scanner inputName = new Scanner(System.in);
        
        while (!complete) {
            try {
                CsvRead read = new CsvRead();
                CsvWrite write = new CsvWrite();
                
                System.out.println("***************************************");
                System.out.println("Please choose either single word or file");
                System.out.println("(Type 'word' for word, 'file' for file)");
                System.out.println("***************************************");
                
                String type = inputName.next();
                Scanner inputString = new Scanner(System.in);
                if (type.equals("word")) {
                    System.out.println("***************************************");
                    System.out.println("Please type in the word/phrase you wish");
                    System.out.println("to truncate");
                    System.out.println("***************************************");
                    
                    String word = inputString.next();
                    
                    Scanner inputInt = new Scanner(System.in);
                    System.out.println("***************************************");
                    System.out.println("Please type in the desired length");
                    System.out.println("restriction (numbers only please)");
                    System.out.println("***************************************");
                    String integer = inputInt.next();
                    int maxLength = Integer.parseInt(integer);
                    
                    String newString = wordTruncate(word, maxLength);
                    System.out.println("***************************************");
                    System.out.println("Please find the truncated word below:");
                    System.out.println(newString);
                    System.out.println("***************************************");
                    
                    complete = true;
                    
                } else if (type.equals("file")) {
                    System.out.println("***************************************");
                    System.out.println("Please type in the file name (no extension,");
                    System.out.println("this program will only accept .csv files)");
                    System.out.println("followed by the desired length restriction");
                    System.out.println("***************************************");
                    String file = inputString.next();
                    
                    Scanner inputInt = new Scanner(System.in);
                    System.out.println("***************************************");
                    System.out.println("Please type in the desired length");
                    System.out.println("restriction");
                    System.out.println("***************************************");
                    String integer = inputInt.next();
                    int maxLength = Integer.parseInt(integer);
                    
                    String[][] data = read.readFile(file + ".csv");
                    fileTruncate(data, maxLength);
                    write.write(data, file);
                    
                    complete = true;
                    
                } else {
                    System.out.println("***************************************");
                    System.out.println("Input not recognized");
                    System.out.println("Sorry, please try again");
                    System.out.println("***************************************");
                }
                
            } catch (IOException| NumberFormatException e) {
               
                System.out.println("***************************************");
                System.out.println("Sorry there appears to be an error");
                System.out.println("Please try again or ctrl-c to quit");
                System.out.println("***************************************");
            } 
        }
    }  
}
