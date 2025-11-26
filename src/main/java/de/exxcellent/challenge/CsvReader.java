package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvReader implements FileReader{
    @Override
    public List<Map<String, String>> read_file(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        List<Map<String, String>> result = new ArrayList<>();

        List<String> firstLine;
        if(scanner.hasNextLine()) {
            String first_line_as_string = scanner.nextLine();
            firstLine = Arrays.asList(first_line_as_string.split(","));
        } else {
            //TODO: add handling of empty CSV, which we do not allow
            return null;
        }


        while(scanner.hasNextLine()) {
            String line_as_string = scanner.nextLine();
            List<String> line = Arrays.asList(line_as_string.split(","));
            if(line.size() != firstLine.size()) {
                //TODO: add handling of invalid CSV file because we met a line of size different from the header line
            }

            Map<String, String> next_row = new HashMap<>();
            for(int i=0; i<line.size(); i++) {
                next_row.put(firstLine.get(i), line.get(i));
            }
            result.add(next_row);

        }
        return result;
    }
}
