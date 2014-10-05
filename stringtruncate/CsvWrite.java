
package stringtruncate;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWrite {
    
    /**
     * This method writes the data from a 2D array into a csv file
     * 
     * @param table the input 2D array containing the desired data
     * @param file the name of the output csv file
     * @throws IOException 
     */
    public void write(String[][] table, String file) throws IOException {
        FileWriter writer = new FileWriter(file + ".csv");
        String cell;
        int max = maxLength(table);
        
        for (int row = 0; row < max; row++) {
            for (String[] column : table) {
                cell = column[row];
                writer.append(cell);
                writer.append(",");
            }
            writer.append("\n");
        }
        
        writer.flush();
        writer.close();
    }
    
    /**
     * This method takes a 2D array and returns the length of the longest 
     * second degree array
     * 
     * @param table the input 2D array
     * @return the int length of the longest second degree array
     */
    private int maxLength(String[][] table) {
        int count = 0;
        int currentMax = 0;
        while (count < table.length) {
            if (table[count].length > currentMax) {
                currentMax = table[count].length;
            }
            count++;
        }
        return currentMax;
    }
}
