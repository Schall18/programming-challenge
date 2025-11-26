package de.exxcellent.challenge;

import java.util.List;
import java.util.Map;

public class WeatherDataset {

    private List<Map<String,String>> data;

    public WeatherDataset(List<Map<String,String>> data) {
        this.data = data;
    }

    public String get_day_with_smallest_temperature_diff() {
        //TODO
        return null;
    }

    /// searaches in data for entry with value at keys minuend-subtrahend minimal. result
    /// determines the key, whose value shall be returned of the found column
    private String get_smallest_diff_from_cols(String minuend, String subtrahend, String result) {
        return null;
    }
}
