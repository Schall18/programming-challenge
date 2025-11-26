package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CsvReaderTest {

    @Test
    void test_valid_csv() {
        String filename = "";
        CsvReader reader = new CsvReader();
        List<Map<String,String>> actual_content = reader.read_file(filename);

        List<Map<String,String>> expected_content = new ArrayList<>();
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

        assertSame(expected_content.size(),actual_content.size());
        for (int i=0; i<expected_content.size(); i++) {
            Map<String, String> expected_row = expected_content.get(i);
            Map<String,String> actual_row = actual_content.get(i);
            assertEquals(expected_row, actual_row);
        }
    }
}
