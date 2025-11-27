package de.exxcellent.challenge;

/// Exception which is thrown if a key we require is missing
public class MissingKeyExeption extends Exception {
    public MissingKeyExeption(String message) {
        super(message);
    }
}
