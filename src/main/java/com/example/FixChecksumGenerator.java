package com.example;

/**
 * A class than can be used to compute the checksum of an FIX protocol message.
 *
 * <p>Passing a {@code null} argument to a method in this class will cause
 * a {@link NullPointerException} to be thrown.</p>
 *
 * @author Michail Almyros
 */
public class FixChecksumGenerator {

    /**
     * Creates a new FixChecksumGenerator object.
     */
    public FixChecksumGenerator() {
    }

    /**
     * Computes the checksum for the FIX protocol message
     * by summing every byte of the message up to but not
     * including the checksum field itself, and then
     * performing a modulo 256 operation against the sum.
     *
     * @param message the FIX protocol message
     * @return returns the FIX's protocol message computed checksum
     */
    public int getChecksum(String message) {

        if (message == null) {
            throw new NullPointerException();
        }

        String strippedMessage = stripChecksumValidation(message);
        char[] charArray = strippedMessage.toCharArray();

        int sum = 0;
        for (char c : charArray) {
            sum += isSOH(c) ? 1 : (int) c;
        }

        return sum % 256;
    }

    /**
     * Removes the last 7 elements of the FIX protocol message
     * that correspond to the checksum.
     *
     * @param message the FIX protocol message
     * @return the message without the checksum
     */
    private static String stripChecksumValidation(String message) {

        int totalChecksumCharacters = 7;
        int startIndex = 0;
        int endIndex = message.length() - totalChecksumCharacters;
        return message.substring(startIndex, endIndex);
    }

    /**
     * Checks if the character is a start of heading
     * control character used in ASCII to indicate
     * the beginning of a heading or header in
     * a data stream.
     *
     * @param character the character to validate
     * @return whether the character is a SOH character.
     */
    private static boolean isSOH(char character) {
        return character == '|';
    }
}