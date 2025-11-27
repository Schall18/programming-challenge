package de.exxcellent.challenge;

/// Exception, which is thrown when a file does not match the format required by a FileReader
public class InvalidFileExeption extends Exception {
    public InvalidFileExeption(String message) {
        super(message);
    }
}
