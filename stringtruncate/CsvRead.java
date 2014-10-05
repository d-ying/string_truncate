
package stringtruncate;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvRead {
    
    /**
     * This method takes csv file and transfers the contents into a 2D array
     * 
     * @param filename the name of the file that you wish to transfer
     * @return a 2D array containing the data from the csv file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String[][] readFile(String filename) throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(new FileReader(filename));
        ArrayList<String> values = new ArrayList<>();
        String[] row;
        int columns = 0;
        int rows = 0;
        
        while ((row = reader.readNext()) != null) {
            columns = row.length;
            values.addAll(Arrays.asList(row));
            rows++;
        }
        
        String[][] data = new String[columns][rows];
        
        for (int x=0; x<rows; x++) {
            for (int y=0; y<columns; y++) { 
                data[y][x] = values.remove(0);
            }
        }
        return data;
    }    
}
