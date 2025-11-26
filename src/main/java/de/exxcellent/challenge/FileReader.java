package de.exxcellent.challenge;

import java.util.List;
import java.util.Map;

public interface FileReader {
    public List<Map<String, String>> read_file(String filename);
}
