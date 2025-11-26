package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvReader implements FileReader{
    @Override
    public List<Map<String, String>> read_file(String filename) throws FileNotFoundException, InvalidFileExeption {
        Scanner scanner = new Scanner(new File(filename));

        List<Map<String, String>> result = new ArrayList<>();

        List<String> firstLine;
        if(scanner.hasNextLine()) {
            String first_line_as_string = scanner.nextLine();
            firstLine = Arrays.asList(first_line_as_string.split(","));
        } else {
            return new ArrayList<>();
        }


        int row_counter = 1;
        while(scanner.hasNextLine()) {
            String line_as_string = scanner.nextLine();
            List<String> line = Arrays.asList(line_as_string.split(","));
            if(line.size() != firstLine.size()) {
                String message = "CSV file with invalid content: The header line consists of " +firstLine.size() + " columns, but row " + row_counter + " of " + line.size() + " columns";
                throw new InvalidFileExeption(message);
            }

            Map<String, String> next_row = new HashMap<>();
            for(int i=0; i<line.size(); i++) {
                next_row.put(firstLine.get(i), line.get(i));
            }
            result.add(next_row);
            row_counter++;
        }
        return result;
    }
}
