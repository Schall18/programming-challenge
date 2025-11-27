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
    public static final String FOOTBALL_FILE = "src/main/resources/de/exxcellent/challenge/football.csv";

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Weather challenge
        FileReader reader = new CsvReader(",");
        try {
            List<Map<String, String>> plain_weather_data = reader.read_file(WEATHER_FILE);
            Dataset weather_dataset = new Dataset(plain_weather_data);
            String day_with_smallest_temperature_diff =
                    weather_dataset.get_smallest_diff_from_cols("MxT", "MnT", "Day");
            System.out.printf("Day with smallest temperature spread : %s%n", day_with_smallest_temperature_diff);
        } catch (FileNotFoundException | InvalidFileExeption | MissingKeyExeption | NumberFormatException e) {
            //terminate program in the case we occured an exeption
            System.out.println(e.getMessage());
        }


        //Football challenge
        try {
            List<Map<String, String>> plain_football_data = reader.read_file(FOOTBALL_FILE);
            Dataset weather_dataset = new Dataset(plain_football_data);
            String team_with_smallest_goal_distance =
                    weather_dataset.get_smallest_diff_from_cols("Goals", "Goals Allowed", "Team");
            System.out.printf("Team with smallest goal spread       : %s%n", team_with_smallest_goal_distance);
        } catch (FileNotFoundException | InvalidFileExeption | MissingKeyExeption | NumberFormatException e) {
            //terminate program in the case we occured an exeption
            System.out.println(e.getMessage());
        }
    }
}
