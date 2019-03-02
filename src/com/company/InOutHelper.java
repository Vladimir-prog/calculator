package com.company;


import java.io.IOException;

public class InOutHelper {
    public static void writeMessage(String message) {
        ConsoleHelper.writeMessage(message);
    }

    public static String readString() throws IOException {
        String text = ConsoleHelper.readString();
        return text;
    }
}
