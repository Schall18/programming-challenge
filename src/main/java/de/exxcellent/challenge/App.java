package de.exxcellent.challenge;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static final String WEATHER_FILE = "src/main/resources/de/exxcellent/challenge/weather.csv";

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        FileReader reader = new CsvReader(",");
        try {
            List<Map<String, String>> plain_weather_data = reader.read_file(WEATHER_FILE);
            WeatherDataset weather_dataset = new WeatherDataset(plain_weather_data);
            String day_with_smallest_temperature_diff =
                    weather_dataset.get_day_with_smallest_temperature_diff();
            System.out.printf("Day with smallest temperature spread : %s%n", day_with_smallest_temperature_diff);
        } catch (FileNotFoundException | InvalidFileExeption | MissingKeyExeption e) {
            //terminate program in the case we occured an exeption
            System.out.println(e.getMessage());
            return;
        }
        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
