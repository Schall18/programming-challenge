package de.exxcellent.challenge;

import java.util.List;
import java.util.Map;

public class CsvReader implements FileReader{
    @Override
    public List<Map<String, String>> read_file(String filename) {
        System.out.println("Reading currently not implemented");
        return List.of();
    }
}
