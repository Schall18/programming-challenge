package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvReader implements FileReader {

    String delimiter;

    public CsvReader(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public List<Map<String, String>> read_file(String filename) throws FileNotFoundException, InvalidFileExeption {
        Scanner scanner = new Scanner(new File(filename));

        List<Map<String, String>> result = new ArrayList<>();

        List<String> firstLine;
        if (scanner.hasNextLine()) {
            String first_line_as_string = scanner.nextLine();
            firstLine = Arrays.asList(first_line_as_string.split(delimiter));
        } else {
            //in case of empty CSV, an empty list is returned
            return new ArrayList<>();
        }


        int row_counter = 1;
        while (scanner.hasNextLine()) {
            String line_as_string = scanner.nextLine();
            Map<String, String> next_row = line_to_map(line_as_string, firstLine, row_counter);
            result.add(next_row);
            row_counter++;
        }
        return result;
    }


    /// method creates from headerline and the next line as String a map of the following form:
    /// {"header1": "value1", "header2":"value2",...}
    private Map<String, String> line_to_map(String line_as_string, List<String> header_line, int row_counter) throws InvalidFileExeption {
        List<String> line = Arrays.asList(line_as_string.split(delimiter));
        if (line.size() != header_line.size()) {
            String message = "CSV file with invalid content: The header line consists of " + header_line.size() + " columns, but row " + row_counter + " of " + line.size() + " columns";
            throw new InvalidFileExeption(message);
        }

        Map<String, String> next_row = new HashMap<>();
        for (int i = 0; i < line.size(); i++) {
            next_row.put(header_line.get(i), line.get(i));
        }
        return next_row;
    }
}
