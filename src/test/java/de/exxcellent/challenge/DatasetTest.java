package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatasetTest {

    @Test
    void valid_input() throws MissingKeyExeption {
        List<Map<String, String>> content = new ArrayList<>();

        //map for first row
        Map<String, String> first_row = new HashMap<>();
        first_row.put("Day", "1");
        first_row.put("MxT", "2");
        first_row.put("MnT", "0");
        content.add(first_row);

        //map for second row
        Map<String, String> second_row = new HashMap<>();
        second_row.put("Day", "2");
        second_row.put("MxT", "10");
        second_row.put("MnT", "3");
        content.add(second_row);

        Dataset dataset = new Dataset(content);
        String result = dataset.get_smallest_diff_from_cols("MxT","MnT", "Day");

        assertEquals("1", result);
    }

    @Test
    void missing_data_key() {
        List<Map<String, String>> content = new ArrayList<>();

        //map for first row
        Map<String, String> first_row = new HashMap<>();
        first_row.put("day","1");
        first_row.put("MnT", "0");
        content.add(first_row);

        //map for second row
        Map<String, String> second_row = new HashMap<>();
        second_row.put("day", "2");
        second_row.put("MnT", "3");
        content.add(second_row);

        Dataset dataset = new Dataset(content);
        Exception exception = assertThrows(MissingKeyExeption.class, () -> dataset.get_smallest_diff_from_cols("MxT","MnT", "Day"));

        String message = exception.getMessage();
        assert(message.contains("Key MxT is missing in Row 1"));
    }


    @Test
    void missing_result_key() {
        List<Map<String, String>> content = new ArrayList<>();

        //map for first row
        Map<String, String> first_row = new HashMap<>();
        first_row.put("Day","1");
        first_row.put("MxT", "1");
        first_row.put("MnT", "0");
        content.add(first_row);

        //map for second row
        Map<String, String> second_row = new HashMap<>();
        second_row.put("MxT", "7");
        second_row.put("MnT", "3");
        content.add(second_row);

        Dataset dataset = new Dataset(content);
        Exception exception = assertThrows(MissingKeyExeption.class, () -> dataset.get_smallest_diff_from_cols("MxT", "MnT", "Day"));

        String message = exception.getMessage();
        assert(message.contains("Key Day is missing in Row 2"));
    }


    @Test
    void non_integer_value() {
        List<Map<String, String>> content = new ArrayList<>();

        //map for first row
        Map<String, String> first_row = new HashMap<>();
        first_row.put("Day", "1");
        first_row.put("MxT", "2");
        first_row.put("MnT", "0");
        content.add(first_row);

        //map for second row
        Map<String, String> second_row = new HashMap<>();
        second_row.put("Day", "2");
        second_row.put("MxT", "NaN");
        second_row.put("MnT", "3");
        content.add(second_row);

        Dataset dataset = new Dataset(content);
        Exception exception = assertThrows(NumberFormatException.class, () -> dataset.get_smallest_diff_from_cols("MxT", "MnT", "Day"));

        String message = exception.getMessage();
        assert(message.contains("\"NaN\""));
    }
}
