/**
 * The IO interface defines methods for handling input and output operations.
 *
 * @Author: Robert Tronhage
 */

package se.ju23.typespeeder.io;

public interface IO {
    boolean yesNo(String prompt);

    String getString();

    String getAnyString();

    void addString(String s);

    void clear();

    void exit();

    void introText();

    int getValidIntegerInput(int minValue, int maxValue);

    long getValidLongInput(long minValue, long maxValue);
}
