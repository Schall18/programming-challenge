package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

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
        CsvReader reader = new CsvReader();
        try {
            List<Map<String, String>> actual_content = reader.read_file(filename);

            List<Map<String, String>> expected_content = new ArrayList<>();
            Map<String, String> first_row = new HashMap<>();
            first_row.put("c1", "1");
            first_row.put("c2", "Hallo");
            first_row.put("c3", "Welt");
            expected_content.add(first_row);

            Map<String, String> second_row = new HashMap<>();
            second_row.put("c1", "2");
            second_row.put("c2", "Line");
            second_row.put("c3", "Two");
            expected_content.add(second_row);

            assertSame(expected_content.size(), actual_content.size());
            for (int i = 0; i < expected_content.size(); i++) {
                Map<String, String> expected_row = expected_content.get(i);
                Map<String, String> actual_row = actual_content.get(i);
                assertEquals(expected_row, actual_row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert(false);
        }
    }

    @Test
    void test_missing_file() {
        CsvReader reader = new CsvReader();
        String filename = "not_existing_file.csv";
        Exception exception = assertThrows(IOException.class, () -> reader.read_file(filename));

        String exception_message = exception.getMessage();
        assert(exception_message.contains(filename));
    }


    //TODO: was passiert bei einer leeren Testfile bzw. einer Testfile aus nur 1 Zeile?
    //TODO: was passiert bei einer Testfile mit zu vielen bzw. zu wenig Einträgen in einer Zeile?
}
