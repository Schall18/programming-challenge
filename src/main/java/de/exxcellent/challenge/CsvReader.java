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

        //first line of CSV determines the column names, i.e. the Keys of our map
        List<String> firstLine;
        if (scanner.hasNextLine()) {
            String first_line_as_string = scanner.nextLine();
            firstLine = Arrays.asList(first_line_as_string.split(delimiter));
        } else {
            //in case of empty CSV, an empty list is returned
            return new ArrayList<>();
        }


        //iterate over each row of the CSV and append with values from the row to the result
        int row_counter = 1;
        while (scanner.hasNextLine()) {
            String row_as_string = scanner.nextLine();
            Map<String, String> next_row = parse_row_into_map(row_as_string, firstLine, row_counter);
            result.add(next_row);
            row_counter++;
        }
        return result;
    }


    /// method creates from headerline and the next line as String a map of the following form:
    /// {"header1": "value1", "header2":"value2",...}
    private Map<String, String> parse_row_into_map(String row_as_string, List<String> header_row, int row_counter) throws InvalidFileExeption {
        List<String> row = Arrays.asList(row_as_string.split(delimiter));
        //check that all row has same length as header. If a row of different length is detected, throw InvalidFileExeption
        if (row.size() != header_row.size()) {
            String message = "CSV file with invalid content: The header row consists of " + header_row.size() + " columns, but row " + row_counter + " of " + row.size() + " columns";
            throw new InvalidFileExeption(message);
        }

        //create new map
        Map<String, String> next_row = new HashMap<>();
        for (int i = 0; i < row.size(); i++) {
            next_row.put(header_row.get(i), row.get(i));
        }
        return next_row;
    }
}
