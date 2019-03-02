package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        InOutHelper.writeMessage("hello! Input your name");
        String text = InOutHelper.readString();
        InOutHelper.writeMessage(String.format("Good morning, %s!", text));
    }
}
