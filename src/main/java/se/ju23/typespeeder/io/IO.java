package se.ju23.typespeeder.io;

public interface IO {
    boolean yesNo(String prompt);
    String getString();
    String getEmptyString();
    void addString(String s);
    void clear();
    void exit();
    void introText();
    int getValidIntegerInput(int minValue, int maxValue);
}
