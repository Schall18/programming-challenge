package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    @Test
    void test_valid_csv() {
        String filename = "src/test/resources/de/exxcellent/challenge/test.csv";
        CsvReader reader = new CsvReader(",");
        try {
            List<Map<String, String>> actual_content = reader.read_file(filename);
            List<Map<String, String>> expected_content = build_expected_csv_content();

            //expected and actual must have same size and each element must be equal
            assertSame(expected_content.size(), actual_content.size());
            for (int i = 0; i < expected_content.size(); i++) {
                Map<String, String> expected_row = expected_content.get(i);
                Map<String, String> actual_row = actual_content.get(i);
                assertEquals(expected_row, actual_row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

    /// builds a map, which we expect is build from the file test.csv
    private List<Map<String, String>> build_expected_csv_content() {
        List<Map<String, String>> expected_content = new ArrayList<>();

        //map for first row
        Map<String, String> first_row = new HashMap<>();
        first_row.put("c1", "1");
        first_row.put("c2", "Hallo");
        first_row.put("c3", "Welt");
        expected_content.add(first_row);

        //map for second row
        Map<String, String> second_row = new HashMap<>();
        second_row.put("c1", "2");
        second_row.put("c2", "Line");
        second_row.put("c3", "Two");
        expected_content.add(second_row);
        return expected_content;
    }

    @Test
    void test_missing_file() {
        CsvReader reader = new CsvReader(",");
        String filename = "not_existing_file.csv";
        Exception exception = assertThrows(IOException.class, () -> reader.read_file(filename));

        String exception_message = exception.getMessage();
        assert (exception_message.contains(filename));
    }

    @Test
    void empty_csv() throws FileNotFoundException, InvalidFileExeption {
        String filename = "src/test/resources/de/exxcellent/challenge/empty.csv";
        CsvReader reader = new CsvReader(",");

        List<Map<String, String>> actual = reader.read_file(filename);
        List<Map<String, String>> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    void csv_with_only_header() throws FileNotFoundException, InvalidFileExeption {
        String filename = "src/test/resources/de/exxcellent/challenge/one_line.csv";
        CsvReader reader = new CsvReader(",");

        List<Map<String, String>> actual = reader.read_file(filename);
        List<Map<String, String>> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }


    /// tests a CSV containing a row with more columns than the header line
    @Test
    void first_invalid_csv_test() {
        String filename = "src/test/resources/de/exxcellent/challenge/invalid1.csv";
        CsvReader reader = new CsvReader(",");

        Exception exception = assertThrows(InvalidFileExeption.class, () -> reader.read_file(filename));

        String exception_message = exception.getMessage();
        assert (exception_message.contains("The header row consists of 2 columns, but row 2 of 5 columns"));
    }

    /// tests a CSV containing a row with less columns than the header line
    @Test
    void second_invalid_csv_test() {
        String filename = "src/test/resources/de/exxcellent/challenge/invalid2.csv";
        CsvReader reader = new CsvReader(",");

        Exception exception = assertThrows(InvalidFileExeption.class, () -> reader.read_file(filename));

        String exception_message = exception.getMessage();
        assert (exception_message.contains("The header row consists of 4 columns, but row 1 of 3 columns"));
    }
}
