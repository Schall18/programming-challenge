package de.exxcellent.challenge;

import java.util.List;
import java.util.Map;

public class Dataset {
    private final List<Map<String, String>> data;

    public Dataset(List<Map<String, String>> data) {
        this.data = data;
    }


    /// searches in data for entry with value at keys minuend_key-subtrahend_key minimal. result_key
    /// determines the key, whose value shall be returned of the found column
    public String get_smallest_diff_from_cols(String minuend_key, String subtrahend_key, String result_key) throws MissingKeyExeption {
        int smallest_diff = Integer.MAX_VALUE;
        int smallest_diff_index = -1;

        //iterate over each element of data, compute |val(minuend-key) - val(subtrahend_key)| and update smallest_diff
        //and smallest_diff_index if a new smallest value is found
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> current_row = data.get(i);
            //check that row contains all necessary keys, i.e. minuend_key, subtrahend_key, result_key. Exeption is thrown if one key is missing
            if (!current_row.containsKey(minuend_key)) {
                throw new MissingKeyExeption("Error while computing difference: Key " + minuend_key + " is missing in Row " + (i + 1));
            }
            if (!current_row.containsKey(subtrahend_key)) {
                throw new MissingKeyExeption("Error while computing difference: Key " + subtrahend_key + " is missing in Row " + (i + 1));
            }
            if (!current_row.containsKey(result_key)) {
                throw new MissingKeyExeption("Error while computing difference: Key " + result_key + " is missing in Row " + (i + 1));
            }

            //parsing error must be handled by caller
            int current_diff_signed = Integer.parseInt(current_row.get(minuend_key)) -
                    Integer.parseInt(current_row.get(subtrahend_key));
            int current_diff = Math.abs(current_diff_signed);

            if (current_diff < smallest_diff) {
                smallest_diff_index = i;
                smallest_diff = current_diff;
            }
        }
        return data.get(smallest_diff_index).get(result_key);
    }
}
